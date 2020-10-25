import React, {useEffect, useState} from 'react'
import Button from '@material-ui/core/Button';
import ButtonGroup from '@material-ui/core/ButtonGroup';
import {makeStyles} from '@material-ui/core/styles';
import CircularProgress from '@material-ui/core/CircularProgress';

import Post from './Post'
import {fetchFirstPageData, fetchData} from "./Api";

const Posts = () => {
    const [posts, setPosts] = useState(undefined);
    const [pageNumber, setPageNumber] = useState(0);
    const [firstLink, setFirstLink] = useState(undefined);
    const [prevLink, setPrevLink] = useState(undefined);
    const [nextLink, setNextLink] = useState(undefined);
    const [lastLink, setLastLink] = useState(undefined);

    const setPageData = data => {
        setPosts(data._embedded.posts);
        setPageNumber(data.page.number);
        const { _links } = data;
        setFirstLink(_links.first?.href);
        setPrevLink(_links.prev?.href);
        setNextLink(_links.next?.href);
        setLastLink(_links.last?.href);
    }

    useEffect(() => {
        fetchFirstPageData()
            .then(data => {
                setPageData(data);
            });
    }, [])

    const loading = () => <CircularProgress/>

    return posts ?
        <div>
            {posts.map(post => <Post key={post.id} title={post.title}/>)}
            <ButtonGroup variant="contained" color="primary" aria-label="contained primary button group">
                <Button onClick={async () => setPageData(await fetchData(firstLink))} disabled={!firstLink}>First</Button>
                <Button onClick={async () => setPageData(await fetchData(prevLink))} disabled={!prevLink}>Prev</Button>
            </ButtonGroup>
            <span style={{padding: '10px'}}>Page: {pageNumber + 1}</span>
            <ButtonGroup variant="contained" color="primary" aria-label="contained primary button group">
                <Button onClick={async () => setPageData(await fetchData(nextLink))} disabled={!nextLink}>Next</Button>
                <Button onClick={async () => setPageData(await fetchData(lastLink))} disabled={!lastLink}>Last</Button>
            </ButtonGroup>
        </div>
        : loading()
}

export default Posts;