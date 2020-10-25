package com.vlad.postsreader.jsonplaceholder;

import com.vlad.postsreader.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;


@Service
class ExternalPostRepository implements PostRepository {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    public static final String POSTS_URI = "/posts";
    public static final String COMMENTS_URI = "/comments";

    private final WebClient webClient;

    public ExternalPostRepository(WebClient.Builder webClientBuilder) {
        webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    @Override
    public Page<com.vlad.postsreader.post.Post> findAll(Pageable pageable) {
        Map<Long, List<Comment>> postIdToComments = getPostIdToComments();

        List<com.vlad.postsreader.post.Post> posts = getConvertedPosts(postIdToComments);
        return new PageImpl<>(
                posts.subList(
                        (int) pageable.getOffset(),
                        (int) Math.min(pageable.getOffset() + pageable.getPageSize(), posts.size())),
                pageable,
                posts.size());
    }

    private Map<Long, List<Comment>> getPostIdToComments() {
        return fetchComments().stream()
                .collect(groupingBy(Comment::getPostId));
    }

    private List<com.vlad.postsreader.post.Post> getConvertedPosts(Map<Long, List<Comment>> postIdToComments) {
        return fetchPosts().stream()
                .sorted(comparingLong(Post::getId))
                .map(post -> DataAdapter.toPost(
                        post,
                        postIdToComments.getOrDefault(post.getId(), emptyList())
                ))
                .collect(toList());
    }

    private List<Post> fetchPosts() {
        return webClient
                .get()
                .uri(POSTS_URI)
                .retrieve()
                .toEntityList(Post.class)
                .block()
                .getBody();
    }

    private List<Comment> fetchComments() {
        return webClient
                .get()
                .uri(COMMENTS_URI)
                .retrieve()
                .toEntityList(Comment.class)
                .block()
                .getBody();
    }

}
