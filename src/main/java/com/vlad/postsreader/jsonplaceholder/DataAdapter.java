package com.vlad.postsreader.jsonplaceholder;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class DataAdapter {

    public static com.vlad.postsreader.post.Post toPost(Post externalPost, List<Comment> externalComments) {

        List<com.vlad.postsreader.post.Comment> comments = convertComments(externalComments);
        return convertPost(externalPost, comments);
    }

    private static List<com.vlad.postsreader.post.Comment> convertComments(List<Comment> externalComments) {
        return externalComments.stream()
                .map(DataAdapter::toComment)
                .collect(toList());
    }

    private static com.vlad.postsreader.post.Post convertPost(
            Post externalPost, List<com.vlad.postsreader.post.Comment> comments) {

        com.vlad.postsreader.post.Post post = new com.vlad.postsreader.post.Post();
        post.setTitle(externalPost.getTitle());
        post.setBody(externalPost.getBody());
        post.setId(externalPost.getId());
        post.setComments(comments);
        return post;
    }

    private static com.vlad.postsreader.post.Comment toComment(Comment externalComment) {
        com.vlad.postsreader.post.Comment comment = new com.vlad.postsreader.post.Comment();
        comment.setBody(externalComment.getBody());
        comment.setEmail(externalComment.getEmail());
        comment.setName(externalComment.getName());
        comment.setId(externalComment.getId());
        return comment;
    }
}
