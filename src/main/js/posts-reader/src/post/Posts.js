import React, { useEffect, useState } from 'react'

import Post from './Post'
import {fetchFirstPageData} from "./Api";

const Posts = () => {
    const [posts, setPosts] = useState(undefined);

    useEffect(() => {
        fetchFirstPageData()
            .then(data => setPosts(data._embedded.posts));
    }, [])

    return (posts ? posts.map(post => <Post key={post.id} title={post.title}/>) : 'loading')
}

export default Posts;