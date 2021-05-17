package com.grwt.dao.itfc;


	import java.util.List;

import org.springframework.http.ResponseEntity;

import com.grwt.main.Category;
	import com.grwt.main.SubCategory;
	import com.grwt.main.SubSubCategory;

	public interface CrudInterfaceForController {	
	public ResponseEntity<List<Category>> selectAllCategory();
	public ResponseEntity<Category> selectByIdCategory(String ctname);
	public ResponseEntity<Category> insertCategory(Category category);
	public ResponseEntity<Category> updateCategory(String oldctname,String ctname);
	public ResponseEntity<String> deleteAllCategory();
	public  ResponseEntity<String> deleteByNameCategory(String  ctname);
	public ResponseEntity<List<SubCategory>> selectAllSubCategory(String ctname);
	public ResponseEntity<SubCategory> selectByIdSubCategory(String ctname,String subname);
	public ResponseEntity<SubCategory> insertSubCategory(String ctname, SubCategory category);
	public ResponseEntity<SubCategory> updateSubCategory(String ctname,String oldctname,String subname);
	public ResponseEntity<String> deleteAllSubCatory(String ctname);
	public  ResponseEntity<String> deleteByNameSubCategory(String  ctname,String subnames);
	public ResponseEntity<List<SubSubCategory>> selectAllSubSubCategory();
	public ResponseEntity<SubSubCategory> selectByIdSubSubCategory(String ctname);
	public ResponseEntity<SubSubCategory> insertSubSubCategory(String ctname , String subName,SubSubCategory category);
	public ResponseEntity<String> deleteAllSubSubCategory(String ctname,String subname ,String subsubname);
	public  ResponseEntity<String> deleteByNameSubSubCategory(String  ctname);
	ResponseEntity<SubSubCategory> updateSubSubCategory(String ctname, String subname, String oldsubsubname,
			String newsubsubname);



	}


