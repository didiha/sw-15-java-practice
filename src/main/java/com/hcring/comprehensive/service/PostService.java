package com.hcring.comprehensive.service;

import com.hcring.comprehensive.domain.Post;
import com.hcring.comprehensive.persistence.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<Post> findAllPosts() {
        return postRepository.selectAllPosts();
    }

    public List<Post> usersPosts(long userNo){
        List<Post> userPosts = findPostsByNo(userNo);

        if (userPosts.isEmpty()) {
            throw new IllegalArgumentException("작성한 게시물이 없습니다.");
        }

        return userPosts;
    }

    public List<Post> findPostsByNo(Long userNo) {
        return postRepository
                .selectAllPosts()
                .stream()
                .filter(post -> post.getAuthor().equals(userService.findUserByNo(userNo).getUserId()))
                .collect(Collectors.toList());
    }

    public Post findPostByNo(long postNo) {
        return postRepository.selectAllPosts()
                .stream()
                .filter(post -> post.getPostNo() == postNo)
                .findFirst()
                .orElse(null); // 게시물이 없을 경우 null 반환
    }

    public void addPost(Post post) {
        postRepository.inputPost(post);
    }

    public void isEmptyPost(String title, String content) {
        if(title == null || title.equals("") || content == null || content.equals("")) {
            throw new IllegalArgumentException("제목 또는 글에 내용이 없습니다.");
        }
    }

    public void modifyPost(Post modifyPost) {
        postRepository.updatePost(modifyPost);
    }

    public void removePost(long deletePostNo) {
        postRepository.deletePost(deletePostNo);
    }
}
