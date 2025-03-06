package com.hcring.comprehensive.domain;

import javax.xml.stream.events.Comment;
import java.io.Serializable;
import java.util.List;

public class Post implements Serializable {
    private long postId;
    private String title;
    private String content;
    private String author;
    private List<Comment> comments;

    public Post(long postId, String title, String content, String author, List<Comment> comments) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.comments = comments;
    }

    public long getPostId() {return postId;}
    public String getTitle() {return title;}
    public String getContent() {return content;}
    public String getAuthor() {return author;}
    public List<Comment> getComments() {return getComments();}

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", comments=" + comments +
                '}';
    }
}
