package com.vlad.postsreader;

import com.vlad.postsreader.jsonplaceholder.DataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PostsReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostsReaderApplication.class, args);
	}

}
