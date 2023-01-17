package com.walsin.coil;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@MapperScan(basePackages = "com.walsin.coil")
public class CoilApplication {
	public static void main(String[] args) {
		SpringApplication.run(CoilApplication.class, args);
	}
}

