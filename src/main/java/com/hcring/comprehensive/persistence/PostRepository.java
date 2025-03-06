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
        long newPostNo = generateNewPostId();
        post.setPostNo(newPostNo);
        postList.add(post);
        postStorage.savePosts(postList);
    }

    private long generateNewPostId() {
        return postList.stream()
                .mapToLong(Post::getPostNo)
                .max()
                .orElse(0) + 1;
    }

    public void updatePost(Post modifyPostNo) {
        for (int i = 0; i < postList.size(); i++) {
            if (postList.get(i).getPostNo() == modifyPostNo.getPostNo()) {
                postList.set(i, modifyPostNo);
                postStorage.savePosts(postList);
                break;
            }
        }
    }

    public void deletePost(long postNo) {
        postList.removeIf(post -> post.getPostNo() == postNo);
        postStorage.savePosts(postList);
    }
}
