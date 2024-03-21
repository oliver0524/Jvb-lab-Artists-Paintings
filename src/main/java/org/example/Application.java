package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The @SpringBootApplication annotation enables automatic configuration of a Spring App.
 */
@SpringBootApplication
public class Application {

	public static Logger log = LoggerFactory.getLogger(Application.class);

	/**
	 * Automatically configure & run the Spring ArtApplication Context, start Controllers.
	 * You can manually test this API using an API testing tool (thunder client, postman, curl.)
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

}