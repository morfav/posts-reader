package com.vlad.postsreader.jsonplaceholder;

class Post {

    private long id;
    private String title;
    private String body;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public com.vlad.postsreader.post.Post toPost() {
        com.vlad.postsreader.post.Post post = new com.vlad.postsreader.post.Post();
        post.setTitle(title);
        post.setBody(body);
        post.setId(id);
        return post;
    }
}
