package com.vlad.postsreader.jsonplaceholder;

import com.vlad.postsreader.data.DataProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;


@Service
class ExternalDataProvider implements DataProvider {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    public static final String POSTS_URI = "/posts";
    public static final String COMMENTS_URI = "/comments";

    private final WebClient webClient;

    public ExternalDataProvider(WebClient.Builder webClientBuilder) {
        webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    public List<com.vlad.postsreader.post.Post> getAllPosts() {
        Map<Long, List<Comment>> postIdToComments = getPostIdToComments();

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

    private Map<Long, List<Comment>> getPostIdToComments() {
        return fetchComments().stream()
                .collect(groupingBy(Comment::getPostId));
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
