package com.hcring.comprehensive.service;

import com.hcring.comprehensive.domain.Comment;
import com.hcring.comprehensive.domain.Post;
import com.hcring.comprehensive.persistence.PostRepository;

import java.util.ArrayList;
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
        Post returnPost = postRepository.selectAllPosts()
                .stream()
                .filter(post -> post.getPostNo() == postNo)
                .findFirst()
                .orElse(null);
        if(returnPost == null) {
            throw new IllegalArgumentException("찾으시는 게시물이 존재하지 않습니다.");
        }

         return returnPost;
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

    public List<Comment> findCommentsByAuthor(String userId) {
        List<Comment> userComments = new ArrayList<>();
        List<Post> allPosts = findAllPosts();

        for (Post post : allPosts) {
            for (Comment comment : post.getComments()) { // 각 게시물의 댓글을 반복
                if (comment.getAuthor().equals(userId)) { // 작성자가 일치하는지 확인
                    userComments.add(comment); // 일치하면 리스트에 추가
                }
            }
        }

        return userComments;
    }
}
