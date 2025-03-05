package com.hcring.comprehensive.ui;

import com.hcring.comprehensive.domain.MbtiType;
import com.hcring.comprehensive.domain.Post;
import com.hcring.comprehensive.domain.User;
import com.hcring.comprehensive.persistence.FileUserStorage;
import com.hcring.comprehensive.persistence.PostRepository;
import com.hcring.comprehensive.persistence.UserRepository;
import com.hcring.comprehensive.service.PostService;
import com.hcring.comprehensive.service.UserService;

import java.util.ArrayList;
import java.util.Scanner;

/* 실행 및 UI */
public class Application {
    private final UserService userService;
    private final Scanner scanner;
    private PostService postService;

    public Application() {
        UserRepository userRepository = new UserRepository(new FileUserStorage());
        PostRepository postRepository = new PostRepository();
        this.userService = new UserService(userRepository);
        this.postService = new PostService(postRepository);
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("\n===== 회원 관리 프로그램 =====");
            System.out.println("1. 모든 회원 조회\t" + "2. 회원 찾기\t\t" + "3. 회원 가입");
            System.out.println("4. 회원 정보 수정\t" + "5. 회원 삭제\t\t" + "6. 회원 활성화/비활성화");
            System.out.println("7. 게시글 작성\t" + "8. 게시글 조회\t" + "9. 게시글 삭제");
            System.out.println("10. 댓글 작성\t\t" + "11. 댓글 삭제 \t" + "12. 프로그램 종료");
            System.out.print("메뉴 선택: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> showAllUsers();
                    case 2 -> findUserByNo();
                    case 3 -> registerUser();
                    case 4 -> modifyUser();
                    case 5 -> removeUser();
                    case 6 -> setUserActivateStatus();
                    case 7 -> posting();
                    case 8 -> showAllPosts();
                    case 12 -> {
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    }
                    default -> System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                }
            } catch (Exception e) {
                System.out.println("오류: " + e.getMessage());
            }
        }
    }

    private void showAllUsers() {
        userService.findAllUsers().forEach(System.out::println);
    }

    private void findUserByNo() {
        System.out.print("조회할 회원 번호 입력: ");
        int no = scanner.nextInt();
        scanner.nextLine();

        User user = userService.findUserByNo(no);
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("해당 번호의 회원을 찾을 수 없습니다.");
        }
    }

    private void registerUser() {
        try {
            System.out.print("아이디 입력: ");
            String id = scanner.nextLine();

            System.out.print("비밀번호 입력: ");
            String pwd = scanner.nextLine();

            System.out.print("나이 입력: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            String[] hobbies = inputHobbies(null);

            System.out.print("Mbti 입력 (대문자로): ");
            MbtiType mbtiType = MbtiType.fromString(scanner.nextLine());

            int nextUserNo = userService.findAllUsers().size() + 1;
            User newUser = new User(nextUserNo, id, pwd, age, hobbies, mbtiType, true);

            userService.registerUser(newUser);
            System.out.println("회원 가입 성공: " + id);

        } catch (IllegalArgumentException e) {
            System.out.println("회원 가입 실패: " + e.getMessage());
        }
    }

    private void modifyUser() {
        try {
            System.out.print("수정할 회원 번호 입력: ");
            int no = scanner.nextInt();
            scanner.nextLine();

            User existingUser = userService.findUserByNo(no);
            if (existingUser == null) {
                System.out.println("해당 번호의 회원을 찾을 수 없습니다.");
                return;
            }

            if(!existingUser.isActivate()){
                System.out.println("활동 상태가 비활성화된 회원입니다. 활성화로 바꾼 뒤 회원 정보를 수정할 수 있습니다.");
                return;
            }

            System.out.print("비밀번호 확인: ");
            String currentPassword = scanner.nextLine();

            System.out.println("수정할 정보를 입력하세요 (변경하지 않으려면 Enter 입력)");
            System.out.print("새로운 아이디 (" + existingUser.getId() + "): ");
            String id = scanner.nextLine();
            if (id.isEmpty()) id = existingUser.getId();

            System.out.print("새로운 비밀번호 입력: ");
            String newPassword = scanner.nextLine();
            if (newPassword.isEmpty()) newPassword = existingUser.getPwd();

            System.out.print("새로운 나이 (" + existingUser.getAge() + "): ");
            String ageInput = scanner.nextLine();
            int age = ageInput.isEmpty() ? existingUser.getAge() : Integer.parseInt(ageInput);

            String[] hobbies = inputHobbies(existingUser.getHobbies());

            System.out.print("새로운 Mbti (" + existingUser.getMbtiType() + "): ");
            String mbtiTypeInput = scanner.nextLine();
            MbtiType mbtiType = mbtiTypeInput.isEmpty() ? existingUser.getMbtiType() : MbtiType.fromString(mbtiTypeInput);

            User updatedUser = new User(no, id, newPassword, age, hobbies, mbtiType, true);
            userService.modifyUser(updatedUser, currentPassword);
            System.out.println("회원 정보 수정 완료: " + id);

        } catch (IllegalArgumentException e) {
            System.out.println("회원 정보 수정 실패: " + e.getMessage());
        }
    }

    private void removeUser() {
        try {
            System.out.print("삭제할 회원 번호 입력: ");
            int no = scanner.nextInt();
            scanner.nextLine();

            userService.removeUser(no);
            System.out.println("회원 삭제 완료 (ID: " + no + ")");

        } catch (IllegalArgumentException e) {
            System.out.println("회원 삭제 실패: " + e.getMessage());
        }
    }

    private String[] inputHobbies(String[] existingHobbies) {
        if (existingHobbies == null) {
            // 새로운 유저 등록 시
            System.out.print("취미 개수 입력: ");
            int hobbyCount = scanner.nextInt();
            scanner.nextLine();

            String[] hobbies = new String[hobbyCount];
            for (int i = 0; i < hobbyCount; i++) {
                System.out.print("취미 입력 (" + (i + 1) + "/" + hobbyCount + "): ");
                hobbies[i] = scanner.nextLine();
            }
            return hobbies;
        } else {
            // 기존 유저 수정 시
            System.out.print("취미 개수 입력 (기존 취미 수: " + existingHobbies.length + "): ");
            String hobbyCountInput = scanner.nextLine();

            if (hobbyCountInput.isEmpty()) {
                return existingHobbies;
            }

            int hobbyCount = Integer.parseInt(hobbyCountInput);
            String[] hobbies = new String[hobbyCount];
            for (int i = 0; i < hobbyCount; i++) {
                System.out.print("취미 입력 (" + (i + 1) + "/" + hobbyCount + "): ");
                String hobbyInput = scanner.nextLine();
                hobbies[i] = hobbyInput.isEmpty() ? existingHobbies[i] : hobbyInput; // 기존 취미 유지
            }
            return hobbies;
        }
    }


    private void setUserActivateStatus() {
        try {
            System.out.print("활동 상태를 변경할 회원 번호를 입력하세요: ");
            int userNum = scanner.nextInt();
            scanner.nextLine();

            User existingUser = userService.findUserByNo(userNum);
            if (existingUser == null) {
                System.out.println("해당 번호의 회원을 찾을 수 없습니다.");
                return;
            }

            System.out.print("비밀번호 확인: ");
            String currentPassword = scanner.nextLine();

            userService.toggleUserActivation(existingUser, currentPassword);
            System.out.println("회원 활동 상태가 변경되었습니다. 현재 상태: " + (existingUser.isActivate() ? "활성화" : "비활성화"));

        } catch (IllegalArgumentException e) {
            System.out.println("회원 활동 상태 전환 실패: " + e.getMessage());
        }
    }

    private void posting(){
        try{
            System.out.print("게시글 작성할 회원 번호 입력: ");
            int userNo = scanner.nextInt();
            scanner.nextLine();

            User existingUser = userService.findUserByNo(userNo);
            if (existingUser == null) {
                System.out.println("해당 번호의 회원을 찾을 수 없습니다.");
                return;
            }

            if(!existingUser.isActivate()){
                System.out.println("활동 상태가 비활성화된 회원입니다. 활성화로 바꾼 뒤 게시글을 작성할 수 있습니다..");
                return;
            }

            System.out.print("비밀번호 확인: ");
            String currentPassword = scanner.nextLine();

            System.out.print("제목을 입력하세요: ");
            String title = scanner.nextLine();

            System.out.print("글을 입력하세요: ");
            String content = scanner.nextLine();

            if (title == null || title.trim().isEmpty() || content == null || content.trim().isEmpty()) {
                throw new IllegalArgumentException("제목 또는 글에 내용이 없습니다.");
            }

            int nextPostNum = postService.findAllPosts().size() + 1;

            Post post = new Post(nextPostNum, title, content, existingUser.getId(), new ArrayList<>());

            postService.addPost(post, existingUser, currentPassword);
        } catch (IllegalArgumentException e) {
            System.out.println("게시글 작성 실패: " + e.getMessage());
        }
    }

    private void showAllPosts() {postService.findAllPosts().forEach(System.out::println);}

    public static void main(String[] args) {
        new Application().run();
    }
}
