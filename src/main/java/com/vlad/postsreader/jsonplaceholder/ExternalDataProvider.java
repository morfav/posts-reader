package com.vlad.postsreader.jsonplaceholder;

import com.vlad.postsreader.data.DataProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.toList;


@Service
class ExternalDataProvider implements DataProvider {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private final WebClient webClient;

    public ExternalDataProvider(WebClient.Builder webClientBuilder) {
        webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    public List<com.vlad.postsreader.post.Post> getAllPosts() {
        return fetchPosts().stream()
                .sorted(comparingLong(Post::getId))
                .map(Post::toPost)
                .collect(toList());
    }

    private List<Post> fetchPosts() {
        return webClient
                .get()
                .uri("/posts")
                .retrieve()
                .toEntityList(Post.class)
                .block()
                .getBody();
    }

}
