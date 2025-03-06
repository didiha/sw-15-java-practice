package com.hcring.comprehensive.persistence;

import com.hcring.comprehensive.domain.Post;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    private List<Post> postList;
    private final PostsStorage postsStorage;

    public PostRepository(PostsStorage postsStorage) {
        this.postsStorage = postsStorage;
        this.postList = postsStorage.loadPosts();
    }

    public List<Post> selectAllPosts() {
        return new ArrayList<>(postList);
    }

    public void inputPost(Post post) {
        postList.add(post);
        postsStorage.savePosts(postList);
    }

    public void deletePost(int postNo) {
        postList.remove(postNo);
        postsStorage.savePosts(postList);
    }
}
