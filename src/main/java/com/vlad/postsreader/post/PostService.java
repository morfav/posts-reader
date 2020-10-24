package com.vlad.postsreader.post;

import com.vlad.postsreader.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {

        this.postRepository = postRepository;
    }

    @Cacheable("posts")
    public Page<Post> getAllPosts(Pageable pageable) {

        return postRepository.findAll(pageable);
    }
}
