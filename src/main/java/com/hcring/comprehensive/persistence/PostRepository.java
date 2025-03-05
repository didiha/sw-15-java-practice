package com.hcring.comprehensive.persistence;

import com.hcring.comprehensive.domain.Post;
import com.hcring.comprehensive.domain.User;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    private List<Post> postList;

    public PostRepository() {
        this.postList = new ArrayList<>();
    }

    public List<Post> selectAllPosts() {
        return new ArrayList<>(postList);
    }

    public void inputPost(Post post) {
        postList.add(post);
    }
}
