package com.hcring.comprehensive.service;

import com.hcring.comprehensive.domain.Comment;
import com.hcring.comprehensive.domain.Post;
import com.hcring.comprehensive.domain.User;
import com.hcring.comprehensive.persistence.CommentRepository;

import java.util.ArrayList;
import java.util.List;

public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, PostService postService){
        this.commentRepository = commentRepository;
        this.postService = postService;
    }

    public void addComment(Post selectPost, String comment, User exsistingUser) {
        commentRepository.createComment(selectPost, comment, exsistingUser);
    }

    public void removeComment(User existingUser, long deleteCommentNo) {
        List<Comment> userComments = new ArrayList<>();

        for (Post post : postService.findAllPosts()) {
            for (Comment comment : post.getComments()) {
                if (comment.getAuthor().equals(existingUser.getUserId())) {
                    userComments.add(comment);
                }
            }
        }

        Comment commentToDelete = null;
        for (Comment comment : userComments) {
            if (comment.getCommentId() == deleteCommentNo) {
                commentToDelete = comment;
                break;
            }
        }

        if (commentToDelete == null) {
            throw new IllegalArgumentException("삭제할 댓글이 존재하지 않습니다.");
        }

        commentRepository.deleteComment(deleteCommentNo);
    }
}
