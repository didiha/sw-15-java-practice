package com.hcring.comprehensive.service;

import com.hcring.comprehensive.domain.Post;
import com.hcring.comprehensive.domain.User;
import com.hcring.comprehensive.persistence.PostRepository;

import java.util.List;

public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAllPosts() {
        return postRepository.selectAllPosts();
    }

    public void addPost(Post post, User user, String currentPassword) {
        if (!user.getPwd().equals(currentPassword)) {
            throw new IllegalArgumentException("회원 정보 수정 실패: 비밀번호가 일치하지 않습니다.");
        }

        if(post.getTitle() == null || post.getTitle().length() == 0) {
            throw new IllegalArgumentException("제목이 없습니다.");
        }

        if(post.getContent() == null || post.getContent().length() == 0) {
            throw new IllegalArgumentException("내용이 없습니다.");
        }

        postRepository.inputPost(post);
    }
}
