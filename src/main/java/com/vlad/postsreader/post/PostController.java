package com.vlad.postsreader.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PostController {

    private final PostService postService;

    private final PagedResourcesAssembler<Post> pagedResourcesAssembler;

    public PostController(PostService postService, PagedResourcesAssembler<Post> pagedResourcesAssembler) {
        this.postService = postService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping("/posts")
    public PagedModel<EntityModel<Post>> all(
            @PageableDefault(size = 5) Pageable pageable) {

        Page<Post> posts = postService.getAllPosts(pageable);

        return pagedResourcesAssembler.toModel(posts);
    }
}
