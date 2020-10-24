package com.vlad.postsreader.repository;

import com.vlad.postsreader.post.Post;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;


public interface PostRepository {

    Page<Post> findAll(Pageable pageable);
}
