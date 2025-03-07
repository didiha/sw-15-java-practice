package com.hcring.comprehensive.ui;

import com.hcring.comprehensive.domain.Comment;
import com.hcring.comprehensive.domain.MbtiType;
import com.hcring.comprehensive.domain.Post;
import com.hcring.comprehensive.domain.User;
import com.hcring.comprehensive.persistence.*;
import com.hcring.comprehensive.service.CommentService;
import com.hcring.comprehensive.service.PostService;
import com.hcring.comprehensive.service.UserService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/* 실행 및 UI */
public class Application {
    private final UserService userService;
    private final Scanner scanner;
    private final PostService postService;
    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final PostStorage postStorage;

    public Application() {
        UserRepository userRepository = new UserRepository(new FileUserStorage());
        PostRepository postRepository = new PostRepository(new FilePostStorage());
        this.scanner = new Scanner(System.in);
        this.userService = new UserService(userRepository, null);
        this.postService = new PostService(postRepository, userService);
        this.postStorage = new FilePostStorage();
        userService.setPostService(postService);
        this.commentRepository = new CommentRepository(postService, postStorage);
        this.commentService = new CommentService(commentRepository, postService);
    }

    public void run() {
        while (true) {
            System.out.println("\n===== 회원 관리 프로그램 =====");
            System.out.println("1. 모든 회원 조회\t" + "2. 회원 찾기\t\t" + "3. 회원 가입");
            System.out.println("4. 회원 정보 수정\t" + "5. 회원 삭제\t\t" + "6. 회원 활성화/비활성화");
            System.out.println("7. 게시글 작성\t" + "8. 게시글 조회\t" + "9. 게시글 수정\t\t" + "10. 게시물 삭제");
            System.out.println("11. 댓글 작성\t\t" + "12. 댓글 삭제 \t" + "13. 프로그램 종료");
            System.out.print("메뉴 선택: ");
            int choice;

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> showAllUsers();
                    case 2 -> findUserByNo();
                    case 3 -> registerUser();
                    case 4 -> modifyUser();
                    case 5 -> removeUser();
                    case 6 -> setUserActivateStatus();
                    case 7 -> posting();
                    case 8 -> showAllPosts();
                    case 9 -> modifyPost();
                    case 10 -> removePost();
                    case 11 -> commenting();
                    case 12 -> removeComment();
                    case 13 -> {
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    }
                }
            } catch(InputMismatchException e){
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                scanner.nextLine();
            }catch (Exception e) {
                System.out.println("오류: " + e.getMessage());
            }
        }
    }

    private void showAllUsers() {
        userService.findAllUsers().forEach(System.out::println);
    }

    private void findUserByNo() {
        System.out.print("조회할 회원 번호 입력: ");
        long userNo = scanner.nextInt();
        scanner.nextLine();

        System.out.println(userService.findUserByNo(userNo).toString());
    }

    private void registerUser() {
        try {
            System.out.print("아이디 입력: ");
            String userId = scanner.nextLine();

            System.out.print("비밀번호 입력: ");
            String pwd = scanner.nextLine();

            System.out.print("나이 입력: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            String[] hobbies = inputHobbies(null);

            System.out.print("Mbti 입력 (대문자로): ");
            MbtiType mbtiType = MbtiType.fromString(scanner.nextLine());

            long nextUserNo = userService.findAllUsers().size() + 1;
            User newUser = new User(nextUserNo, userId, pwd, age, hobbies, mbtiType, true);

            userService.registerUser(newUser);
            System.out.println("회원 가입 성공! 환영합니다, " + userId + "!");

        } catch (IllegalArgumentException e) {
            System.out.println("회원 가입 실패: " + e.getMessage());
        }
    }

    private void modifyUser() {
        try {
            System.out.print("수정할 회원 번호 입력: ");
            long userNo = scanner.nextInt();
            scanner.nextLine();

            User existingUser = userService.findUserByNo(userNo);

            if(!existingUser.isActivate()){
                System.out.println("활동 상태가 비활성화된 회원입니다. 활성화로 바꾼 뒤 회원 정보를 수정할 수 있습니다.");
                return;
            }

            System.out.print("비밀번호 확인: ");
            String currentPassword = scanner.nextLine();
            userService.validatePassword(userNo, currentPassword);

            System.out.println("수정할 정보를 입력하세요 (변경하지 않으려면 Enter 입력)");
            System.out.print("새로운 아이디 (" + existingUser.getUserId() + "): ");
            String userId = scanner.nextLine();
            if (userId.isEmpty()) userId = existingUser.getUserId();

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

            User updatedUser = new User(userNo, userId, newPassword, age, hobbies, mbtiType, true);
            userService.modifyUser(updatedUser);
            System.out.println("회원 정보 수정이 완료되었습니다.");

        } catch (IllegalArgumentException e) {
            System.out.println("회원 정보 수정 실패: " + e.getMessage());
        }
    }

    private void removeUser() {
        try {
            System.out.print("삭제할 회원 번호 입력: ");
            long deleteUserNo = scanner.nextInt();
            scanner.nextLine();

            userService.findUserByNo(deleteUserNo);

            System.out.print("비밀번호 확인: ");
            String currentPassword = scanner.nextLine();
            userService.validatePassword(deleteUserNo, currentPassword);

            userService.removeUser(deleteUserNo);
            System.out.println("회원 삭제 완료 (No: " + deleteUserNo + ")");

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
            long userNo = scanner.nextInt();
            scanner.nextLine();

            User existingUser = userService.findUserByNo(userNo);

            System.out.print("비밀번호 확인: ");
            String currentPassword = scanner.nextLine();
            userService.validatePassword(userNo, currentPassword);

            userService.toggleUserActivation(existingUser);
            System.out.println("회원 활동 상태가 변경되었습니다. 현재 상태: " + (existingUser.isActivate() ? "활성화" : "비활성화"));

        } catch (IllegalArgumentException e) {
            System.out.println("회원 활동 상태 전환 실패: " + e.getMessage());
        }
    }

    private void posting(){
        try{
            System.out.print("게시글을 작성할 회원 번호 입력: ");
            long userNo = scanner.nextInt();
            scanner.nextLine();

            User existingUser = userService.findUserByNo(userNo);

            if(!existingUser.isActivate()){
                System.out.println("활동 상태가 비활성화된 회원입니다. 활성화로 바꾼 뒤 게시글을 작성할 수 있습니다..");
                return;
            }

            System.out.print("비밀번호 확인: ");
            String currentPassword = scanner.nextLine();
            userService.validatePassword(userNo, currentPassword);

            System.out.print("제목을 입력하세요: ");
            String title = scanner.nextLine();

            System.out.print("글을 입력하세요: ");
            String content = scanner.nextLine();

            postService.isEmptyPost(title, content);

            long nextPostNo = postService.findAllPosts().size() + 1;

            Post post = new Post(nextPostNo, title, content, existingUser.getUserId(), new ArrayList<>());
            postService.addPost(post);

            System.out.println("게시글 작성이 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println("게시글 작성 실패: " + e.getMessage());
        }
    }

    private void showAllPosts() {postService.findAllPosts().forEach(System.out::println);}

    private void modifyPost(){
        try{
            System.out.print("게시물을 수정할 회원 번호를 입력하세요: ");
            long userNo = scanner.nextInt();
            scanner.nextLine();

            User existingUser = userService.findUserByNo(userNo);

            if(!existingUser.isActivate()){
                System.out.println("활동 상태가 비활성화된 회원입니다. 활성화로 바꾼 뒤 게시글을 수정할 수 있습니다.");
                return;
            }

            System.out.print("비밀번호 확인: ");
            String currentPassword = scanner.nextLine();
            userService.validatePassword(userNo, currentPassword);

            List<Post> postList = postService.usersPosts(userNo);

            System.out.println("작성한 게시글 목록: \n" + postList);
            System.out.print("수정할 게시글 번호 입력: ");

            long selectPostNo = scanner.nextInt();
            scanner.nextLine();

            Post selectPost = postService.findPostByNo(selectPostNo);

            System.out.println("수정할 정보를 입력하세요 (변경하지 않으려면 Enter 입력)");
            System.out.print("새로운 제목 입력: ");
            String title = scanner.nextLine();
            if (title.isEmpty()) title = selectPost.getTitle();

            System.out.print("새로운 글 입력: ");
            String content = scanner.nextLine();
            if (content.isEmpty()) content = selectPost.getContent();

            Post modifyPost = new Post(selectPostNo, title, content, selectPost.getAuthor(), selectPost.getComments());
            postService.modifyPost(modifyPost);

            System.out.println("게시글 수정이 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println("게시물 수정 실패: " + e.getMessage());
        }
    }

    private void removePost() {
        try {
            System.out.print("게시물을 삭제할 회원 번호를 입력하세요: ");
            long userNo = scanner.nextInt();
            scanner.nextLine();

            User existingUser = userService.findUserByNo(userNo);

            if(!existingUser.isActivate()){
                System.out.println("활동 상태가 비활성화된 회원입니다. 활성화로 바꾼 뒤 게시글을 삭제할 수 있습니다.");
                return;
            }

            System.out.print("비밀번호 확인: ");
            String currentPassword = scanner.nextLine();
            userService.validatePassword(userNo, currentPassword);

            List<Post> userPosts = postService.usersPosts(userNo);

            System.out.println("작성한 게시물 목록: \n" + userPosts);

            System.out.print("삭제할 게시물 번호 입력: ");
            long deletePostNo = scanner.nextInt();
            scanner.nextLine();

            postService.removePost(deletePostNo);
            System.out.println("게시물 삭제 완료 (No: " + deletePostNo + ")");

        } catch (IllegalArgumentException e) {
            System.out.println("게시물 삭제 실패: " + e.getMessage());
        }
    }

    private void commenting(){
        try {
            System.out.print("댓글을 작성할 회원 번호를 입력하세요: ");
            long userNo = scanner.nextInt();
            scanner.nextLine();

            User existingUser = userService.findUserByNo(userNo);

            if (!existingUser.isActivate()) {
                System.out.println("활동 상태가 비활성화된 회원입니다. 활성화로 바꾼 뒤 댓글을 작성할 수 있습니다.");
                return;
            }

            System.out.print("비밀번호 확인: ");
            String currentPassword = scanner.nextLine();
            userService.validatePassword(userNo, currentPassword);

            System.out.print("댓글을 작성할 게시물의 번호 입력: ");
            long selectPostNo = scanner.nextInt();
            scanner.nextLine();
            Post selectPost = postService.findPostByNo(selectPostNo);

            System.out.print("댓글 입력: ");
            String comment = scanner.nextLine();

            commentService.addComment(selectPost, comment, existingUser);

            System.out.println("댓글 작성이 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println("댓글 작성 실패: " + e.getMessage());
        }
    }

    private void removeComment() {
        try {
            System.out.print("댓글을 삭제할 회원 번호를 입력하세요: ");
            long userNo = scanner.nextInt();
            scanner.nextLine();

            User existingUser = userService.findUserByNo(userNo);

            if (!existingUser.isActivate()) {
                System.out.println("활동 상태가 비활성화된 회원입니다. 활성화로 바꾼 뒤 댓글을 삭제할 수 있습니다.");
                return;
            }

            System.out.print("비밀번호 확인: ");
            String currentPassword = scanner.nextLine();
            userService.validatePassword(userNo, currentPassword);

            System.out.println(existingUser.getUserId() + " 회원이 작성한 댓글 목록: ");
            for (Post post : postService.findAllPosts()) { // 모든 게시물 가져오기
                List<Comment> userComments = postService.findCommentsByAuthor(existingUser.getUserId());
                for (Comment comment : userComments) {
                    System.out.println(comment);
                }
            }

            System.out.print("삭제할 댓글 번호 입력: ");
            long deleteCommentNo = scanner.nextInt();
            scanner.nextLine();

            commentService.removeComment(existingUser, deleteCommentNo);
            System.out.println("댓글 삭제 완료(No: " + deleteCommentNo + ")");
        } catch (IllegalArgumentException e) {
            System.out.println("댓글 삭제 실패: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Application().run();
    }
}
