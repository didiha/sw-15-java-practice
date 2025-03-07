package com.hcring.comprehensive.persistence;

import com.hcring.comprehensive.domain.Post;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    private List<Post> postList;
    private final PostStorage postStorage;

    public PostRepository(PostStorage postStorage) {
        this.postStorage = postStorage;
        this.postList = postStorage.loadPosts();
    }

    public List<Post> selectAllPosts() {
        return new ArrayList<>(postList);
    }

    public void inputPost(Post post) {
        long newPostNo = generateNewPostNo();
        post.setPostNo(newPostNo);
        postList.add(post);
        postStorage.savePosts(postList);
    }

    private long generateNewPostNo() {
        return postList.stream()
                .mapToLong(Post::getPostNo)
                .max()
                .orElse(0) + 1;
    }

    public void updatePost(Post modifyPostNo) {
        if (!postList.isEmpty()) {
            postList.set(0, modifyPostNo);
            postStorage.savePosts(postList);
        }
    }

    public void deletePost(long postNo) {
        postList.removeIf(post -> post.getPostNo() == postNo);
        postStorage.savePosts(postList);
    }
}
