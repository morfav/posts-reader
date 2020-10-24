package com.vlad.postsreader.repository;

import com.vlad.postsreader.post.Post;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
}
