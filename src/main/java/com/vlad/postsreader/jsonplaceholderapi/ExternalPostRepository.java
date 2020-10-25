package com.vlad.postsreader.jsonplaceholderapi;

import com.vlad.postsreader.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.*;


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
        Map<Long, List<CommentExternalDto>> postIdToComments = getPostIdToComments();

        List<com.vlad.postsreader.post.Post> posts = getConvertedPosts(postIdToComments);
        return new PageImpl<>(
                posts.subList(
                        (int) pageable.getOffset(),
                        (int) Math.min(pageable.getOffset() + pageable.getPageSize(), posts.size())),
                pageable,
                posts.size());
    }

    private Map<Long, List<CommentExternalDto>> getPostIdToComments() {
        return fetchComments().stream()
                .collect(groupingBy(CommentExternalDto::getPostId));
    }

    private List<com.vlad.postsreader.post.Post> getConvertedPosts(Map<Long, List<CommentExternalDto>> postIdToComments) {
        return fetchPosts().stream()
                .sorted(comparingLong(PostExternalDto::getId))
                .map(postExternalDto -> DataAdapter.toPost(
                        postExternalDto,
                        postIdToComments.getOrDefault(postExternalDto.getId(), emptyList())
                ))
                .collect(toList());
    }

    private List<PostExternalDto> fetchPosts() {
        return fetchList(POSTS_URI, PostExternalDto.class);
    }

    private List<CommentExternalDto> fetchComments() {
        return fetchList(COMMENTS_URI, CommentExternalDto.class);
    }

    private <T> List<T> fetchList(String uri, Class<T> objectClass) {
        ResponseEntity<List<T>> response = webClient
                .get()
                .uri(uri)
                .retrieve()
                .toEntityList(objectClass)
                .block();

        if (response != null) {
            return response.getBody();
        }
        return emptyList();
    }

}
