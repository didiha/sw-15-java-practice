package com.hcring.comprehensive.domain;

import javax.xml.stream.events.Comment;
import java.io.Serializable;
import java.util.List;

public class Post implements Serializable {
    private long postNo;
    private String title;
    private String content;
    private String author;
    private List<Comment> comments;

    public Post(long postNo, String title, String content, String author, List<Comment> comments) {
        this.postNo = postNo;
        this.title = title;
        this.content = content;
        this.author = author;
        this.comments = comments;
    }

    public long getPostNo() {return postNo;}
    public String getTitle() {return title;}
    public String getContent() {return content;}
    public String getAuthor() {return author;}
    public List<Comment> getComments() {return comments;}

    public void setPostNo(long postNo) {
        this.postNo = postNo;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postNo=" + postNo +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", comments=" + comments +
                '}';
    }
}
