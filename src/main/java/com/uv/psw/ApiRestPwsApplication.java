package com.uv.psw;

import com.uv.psw.utils.EnvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiRestPwsApplication {

	public static void main(String[] args) {
		EnvLoader.loadDotenv();
		SpringApplication.run(ApiRestPwsApplication.class, args);
	}

}
