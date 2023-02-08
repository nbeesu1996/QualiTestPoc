package com.qualitest.poc;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SpringRoleJwtApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpringRoleJwtApplication.class);
		app.setDefaultProperties(Collections
		          .singletonMap("server.port", "8083"));
		        app.run(args);
	}

}
