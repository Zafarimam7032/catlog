package com.grwt.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;

import com.grwt.dao.CotegoryDao;
import com.grwt.main.Category;
import com.grwt.main.SubCategory;
import com.grwt.main.SubSubCategory;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@org.springframework.context.annotation.Configuration
@EnableSwagger2
public class Configuration {
	
	@Bean
	public Category getCategory()
	{
		return new Category();
	}
	@Bean
	public SubCategory getSubCategory()
	{
		return new SubCategory();
	}
	@Bean
	public SubSubCategory getSubSubCategory()
	{
		return  new SubSubCategory();
	}
	@Bean
	public CotegoryDao getCotegoryDao()
	{
		return new CotegoryDao();
	}
	@Bean
	public List<SubCategory> getsubCategories()
	{
		return new ArrayList<>();
	}
	@Bean
	public List<SubSubCategory> getSubSubCategories()
	{
		return new ArrayList<>();
	}

}
