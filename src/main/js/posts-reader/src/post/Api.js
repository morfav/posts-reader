const baseUrl = 'http://localhost:8080/posts';

export const fetchFirstPageData = async () => {
    const data = await fetchData(baseUrl);
    return fetchData(data._links.first.href);
}

export const fetchData = async url => {
    const response = await fetch(url);
    return await response.json();
}