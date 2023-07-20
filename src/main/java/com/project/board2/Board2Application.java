package com.project.board2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.project.board2.**.mappers"})
public class Board2Application {

	public static void main(String[] args) {
		SpringApplication.run(Board2Application.class, args);
	}

}
