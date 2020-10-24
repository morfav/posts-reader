package com.vlad.postsreader.jsonplaceholder;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

@Service
public class DataProvider {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private final WebClient webClient;

    public DataProvider(WebClient.Builder webClientBuilder) {
        webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    public List<Post> getPosts() {
        return fetchPosts();
    }

    @Bean
    private List<Post> fetchPosts() {
        return webClient.get().uri("/posts").retrieve().toEntityList(Post.class).block().getBody();
    }

}
