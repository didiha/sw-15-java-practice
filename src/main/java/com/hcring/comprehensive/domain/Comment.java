package com.hcring.comprehensive.domain;

import java.io.Serializable;

public class Comment implements Serializable {
    private long commentId;
    private String commentContent;
    private String author;

    public Comment(long commentId, String commentContent, String author) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.author = author;
    }

    public long getCommentId() {return commentId;}
    public String getCommentContent() {return commentContent;}
    public String getAuthor() {return author;}

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentContent='" + commentContent + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
