package com.grwts.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.grwts.controller,com.grwts.service"})
public class GreatWitsCatelogListApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreatWitsCatelogListApplication.class, args);
	}

}
