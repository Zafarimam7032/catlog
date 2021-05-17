package com.grwt.dao.itfc;

import java.util.List;

import com.grwt.main.Category;
import com.grwt.main.SubCategory;
import com.grwt.main.SubSubCategory;

public interface CrudInterface {
public List<Category> selectAllCategory();
public Category selectByIdCategory(String ctname);
public Category insertCategory(Category category);
public Category updateCategory(String oldctname,String ctname);
public String deleteAllCategory();
public  String deleteByNameCategory(String  ctname);
public List<SubCategory> selectAllSubCategory(String ctname);
public SubCategory selectByIdSubCategory(String ctname,String subname);
public SubCategory insertSubCategory(SubCategory category);
public SubCategory updateSubCategory(String ctname,String oldctname,String subname);
public String deleteAllSubCatory(String ctname);
public  String deleteByNameSubCategory(String  ctname,String subame);
public List<SubSubCategory> selectAllSubSubCategory();
public SubSubCategory selectByIdSubSubCategory(String ctname);
public SubSubCategory insertSubSubCategory(String subname,SubCategory subCategory, SubSubCategory category);
public SubSubCategory updateSubSubCategory(String oldctname,String ctname);
public String deleteAllSubSubCategory();
public  String deleteByNameSubSubCategory(String  ctname);
public  SubCategory insertSubSubCategory(SubCategory subCategory);



}
