package com.hcring.comprehensive.service;

import com.hcring.comprehensive.domain.Post;
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

    public void addPost(Post post) {
        postRepository.inputPost(post);
    }

    public void isEmptyPost(String title, String content) {
        if(title == null || title.equals("") || content == null || content.equals("")) {
            throw new IllegalArgumentException("제목 또는 글에 내용이 없습니다.");
        }
    }

    public void removePost(int postNo) {
        postRepository.deletePost(postNo);
    }
}
