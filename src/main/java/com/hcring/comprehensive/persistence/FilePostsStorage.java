package com.hcring.comprehensive.persistence;

import com.hcring.comprehensive.domain.Post;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilePostsStorage implements PostsStorage {
    private final String POSTS_FILE_PATH = "src/main/java/com/hcring/comprehensive/db/posts.dat";

    @Override
    public void savePosts(List<Post> posts){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(POSTS_FILE_PATH))) {
            oos.writeObject(posts);
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 중 오류 발생", e);
        }
    }

    @Override
    public List<Post> loadPosts(){
        File file = new File(POSTS_FILE_PATH);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Post>) ois.readObject();
        } catch (EOFException e) {
            System.out.println("회원 정보를 모두 로딩하였습니다.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("파일 로딩 중 오류 발생", e);
        }
    }
}
