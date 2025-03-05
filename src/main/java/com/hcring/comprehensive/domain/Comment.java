package com.hcring.comprehensive.domain;

public class Comment {
    private long commentId;
    private String text;
    private String author;

    public Comment(long commentId, String text, String author) {
        this.commentId = commentId;
        this.text = text;
        this.author = author;
    }

    public long getCommentId() {return commentId;}
    public String getText() {return text;}
    public String getAuthor() {return author;}

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", text='" + text + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
