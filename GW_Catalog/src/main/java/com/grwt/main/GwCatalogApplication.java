package com.grwt.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "com.grwt.config,com.grwt.dao,com.grwt.controller" })
@EnableJpaRepositories

public class GwCatalogApplication {
//	@Autowired
//	private Category category;
//	@Autowired
//	private SubCategory categorySubCategory;
//	@Autowired
//	private SubSubCategory subSubCategory;

	public static void main(String[] args) {
		SpringApplication.run(GwCatalogApplication.class, args);
	}
	
}
