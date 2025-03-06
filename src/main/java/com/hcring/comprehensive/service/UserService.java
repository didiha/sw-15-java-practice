package com.hcring.comprehensive.service;

import com.hcring.comprehensive.domain.User;
import com.hcring.comprehensive.persistence.UserRepository;

import java.util.List;
import java.util.regex.Pattern;

/* Service : 비즈니스 로직 */
public class UserService {
    private final UserRepository userRepository;
    private static final int MIN_AGE = 14;  // 회원가입 최소 나이
    private static final int MIN_PASSWORD_LENGTH = 6;  // 최소 비밀번호 길이
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(".*[!@#$%^&*(),.?\":{}|<>].*"); // 특수문자 포함

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.selectAllUsers();
    }

    public User findUserByNo(int userNo) {
        User existingUser = userRepository.selectUserByNo(userNo);
        if (existingUser == null) {
            throw new IllegalArgumentException("해당 회원을 찾을 수 없습니다.");
        }
        return existingUser;
    }

    public void registerUser(User user) {
        if (isDuplicateUserId(user.getId())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        if (!isValidPassword(user.getPwd())) {
            throw new IllegalArgumentException("비밀번호는 최소 " + MIN_PASSWORD_LENGTH + "자 이상이어야 하고 특수문자를 포함해야 합니다.");
        }

        if (user.getAge() < MIN_AGE) {
            throw new IllegalArgumentException("나이는 최소 " + MIN_AGE + "세 이상이어야 합니다.");
        }

        userRepository.insertUser(user);
    }

    public void removeUser(int userNo) {
        userRepository.deleteUser(userNo);
    }

    public void modifyUser(User updatedUser) {
        userRepository.updateUser(updatedUser);
    }

    public void toggleUserActivation(User user) {
        User existingUser = findUserByNo(user.getUserNo());

        existingUser.setActivate(!existingUser.isActivate());

        userRepository.updateUser(existingUser);
    }

    public void validatePassword(int userNo, String password) {
        if(findUserByNo(userNo).getPwd().equals(password)) {
            throw new IllegalArgumentException("비민번호가 일치하지 않습니다.");
        }
    }

    private boolean isDuplicateUserId(String userId) {
        return userRepository.selectAllUsers()
                .stream()
                .anyMatch(user -> user.getId().equals(userId));
    }

    private boolean isValidPassword(String password) {
        return password.length() >= MIN_PASSWORD_LENGTH && PASSWORD_PATTERN.matcher(password).matches();
    }
}
