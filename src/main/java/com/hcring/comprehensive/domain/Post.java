package com.hcring.comprehensive.domain;

import java.io.Serializable;
import java.util.ArrayList;
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
        this.comments = new ArrayList<>();
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
        StringBuilder sb = new StringBuilder();
        sb.append("Post{")
                .append("postNo=").append(postNo)
                .append(", title='").append(title).append('\'')
                .append(", content='").append(content).append('\'')
                .append(", author='").append(author).append('\'')
                .append(", \n\tcomments=[");

        for (Comment comment : comments) {
            sb.append("\n\t").append(comment.toString()).append(",");
        }

        // 마지막 댓글의 쉼표 제거
        if (!comments.isEmpty()) {
            sb.setLength(sb.length() - 1);
        }
        sb.append("\n]}\n");

        return sb.toString();
    }
}
