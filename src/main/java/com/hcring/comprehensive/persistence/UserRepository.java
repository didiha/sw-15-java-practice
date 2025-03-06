package com.hcring.comprehensive.persistence;

import com.hcring.comprehensive.domain.User;

import java.util.ArrayList;
import java.util.List;

/* User 데이터 저장 및 조회 */
public class UserRepository {
    private final UserStorage userStorage;
    private final List<User> userList;

    public UserRepository(UserStorage userStorage) {
        this.userStorage = userStorage;
        this.userList = userStorage.loadUsers();
    }

    public List<User> selectAllUsers() {
        return new ArrayList<>(userList);
    }

    public User selectUserByNo(int userNo) {
        return userList.stream().filter(user -> user.getUserNo() == userNo).findFirst().orElse(null);
    }

    public void insertUser(User user) {
        userList.add(user);
        userStorage.saveUsers(userList);
    }

    public void deleteUser(int userNo) {
        userList.removeIf(user -> user.getUserNo() == userNo);
        userStorage.saveUsers(userList);
    }

    public void updateUser(User updatedUser) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserNo() == updatedUser.getUserNo()) {
                userList.set(i, updatedUser);
                userStorage.saveUsers(userList);
                break;
            }
        }
    }
}
