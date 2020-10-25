To launch the back-end service, execute:

### `mvnw spring-boot:run`

To launch the client, navigate to ./src/main/js/posts-reader and run:

### `npm install`
then
### `npm start`

The back-end was developed using Java 11.

I considered the following improvements however did not implement them
due to time constraints:

Caching should really be done for the big API calls, rather than the paginated service response

Add script/mvn plugin which will build the react project and place it in a suitable location
so it can be served up by the spring boot container as a static resource

Front end routing