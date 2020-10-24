package com.vlad.postsreader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PostsReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostsReaderApplication.class, args);
	}

}
