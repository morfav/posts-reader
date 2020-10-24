package com.vlad.postsreader.post;

import com.vlad.postsreader.data.DataProvider;
import com.vlad.postsreader.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;


@Service
public class PostService {

    private final DataProvider dataProvider;
    private final PostRepository postRepository;

    @Autowired
    public PostService(DataProvider dataProvider, PostRepository postRepository) {

        this.dataProvider = dataProvider;
        this.postRepository = postRepository;
    }

    @GetMapping
    public Page<Post> getAllPosts(Pageable pageable) {
        postRepository.deleteAll();
        postRepository.saveAll(dataProvider.getAllPosts());
        return postRepository.findAll(pageable);
    }
}
