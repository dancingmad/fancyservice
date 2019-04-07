package com.irongrp.minesweeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAutoConfiguration
public class MainApp {

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}

}
