package com.hcring.comprehensive.persistence;

import com.hcring.comprehensive.domain.Post;

import java.util.List;

public interface PostStorage {
    void savePosts(List<Post> post);
    List<Post> loadPosts();
}
