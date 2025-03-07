package com.hcring.comprehensive.persistence;

import com.hcring.comprehensive.domain.Comment;
import com.hcring.comprehensive.domain.Post;
import com.hcring.comprehensive.domain.User;
import com.hcring.comprehensive.service.PostService;

import java.util.Collections;

public class CommentRepository {
    private final PostService postService;
    private final PostStorage postStorage;
    private long nextCommentId = 1;

    public CommentRepository(PostService postService, PostStorage postStorage) {
        this.postService = postService;
        this.postStorage = postStorage;
    }

    public void createComment(Post selectPost, String commentContent, User exsistingUser) {
        Comment newComment = new Comment(generateNewCommentId(), commentContent, exsistingUser.getUserId());
        selectPost.getComments().add(newComment);

        postStorage.savePosts(Collections.singletonList(selectPost));
    }

    private long generateNewCommentId() {
        return nextCommentId++;
    }

    public void deleteComment(long commentId) {
        for (Post post : postService.findAllPosts()) {
            for (Comment comment : post.getComments()) {
                if (comment.getCommentId() == commentId) {

                    post.getComments().remove(comment);
                    postStorage.savePosts(Collections.singletonList(post));
                    return;
                }
            }
        }
    }
}
