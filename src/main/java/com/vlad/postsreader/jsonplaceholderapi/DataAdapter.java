package com.vlad.postsreader.jsonplaceholderapi;

import com.vlad.postsreader.post.Comment;
import com.vlad.postsreader.post.Post;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class DataAdapter {

    public static Post toPost(PostExternalDto externalPost, List<CommentExternalDto> externalComments) {

        List<Comment> comments = toComments(externalComments);
        return convertPost(externalPost, comments);
    }

    private static List<Comment> toComments(List<CommentExternalDto> externalComments) {
        return externalComments.stream()
                .map(DataAdapter::toComment)
                .collect(toList());
    }

    private static Post convertPost(PostExternalDto externalPost, List<Comment> comments) {

        Post post = new Post();
        post.setTitle(externalPost.getTitle());
        post.setBody(externalPost.getBody());
        post.setId(externalPost.getId());
        post.setComments(comments);
        return post;
    }

    private static Comment toComment(CommentExternalDto externalComment) {
        Comment comment = new Comment();
        comment.setBody(externalComment.getBody());
        comment.setEmail(externalComment.getEmail());
        comment.setName(externalComment.getName());
        comment.setId(externalComment.getId());
        return comment;
    }
}
