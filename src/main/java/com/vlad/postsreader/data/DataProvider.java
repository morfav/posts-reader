package com.vlad.postsreader.data;

import com.vlad.postsreader.post.Post;

import java.util.List;


public interface DataProvider {

    List<Post> getAllPosts();
}
