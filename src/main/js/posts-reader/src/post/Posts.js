import React, { useEffect, useState } from 'react'

import Post from './Post'

const Posts = () => {
    const [posts, setPosts] = useState(undefined);

    useEffect(() => {
        const fetchData = async () => {
            const apiUrl = 'http://localhost:8080/posts';
            const response = await fetch(apiUrl);
            const data = await response.json();
            setPosts(data._embedded.posts);
        }
        fetchData();
    }, [])

    return (posts ? posts.map(post => <Post key={post.id} title={post.title}/>) : 'loading')
}

export default Posts;