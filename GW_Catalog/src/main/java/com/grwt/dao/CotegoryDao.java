package com.grwt.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.grwt.dao.itfc.CrudInterface;
import com.grwt.main.Category;
import com.grwt.main.SubCategory;
import com.grwt.main.SubSubCategory;
import com.grwt.main.UserRepositoryCatelog;
import com.grwt.main.UserRepositorySubCatelog;
import com.grwt.main.UserRepositorySubSubCategory;

public class CotegoryDao implements CrudInterface {
	@Autowired
	private UserRepositoryCatelog userRepositoryCatelog;
	@Autowired
	private UserRepositorySubCatelog userRepositorySubCatelog;
	@Autowired
	private UserRepositorySubSubCategory userRepositorySubSubCategory;

	@Override
	public List<Category> selectAllCategory() {
		List<Category> findAll = null;
		try {
			findAll = userRepositoryCatelog.findAll();
			return findAll;
		} catch (Exception e) {
			e.printStackTrace();
			return findAll;
		}

	}

	@Override
	public Category selectByIdCategory(String ctname) {
		Category category = null;
		try {
			category = userRepositoryCatelog.findByctName(ctname);
			return category;
		} catch (Exception e) {
			e.printStackTrace();
			return category;
		}
	}

	@Override
	public Category insertCategory(Category category) {
		Category category2 = null;
		try {

			category2 = userRepositoryCatelog.save(category);
			return category2;
		}

		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Category insertCategorySub(Category Category) {
		try {
			Category category2 = userRepositoryCatelog.save(Category);
			return category2;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Category updateCategory(String oldctname, String ctname) {

		Category save = null;
		try {
			Category findByCtName = userRepositoryCatelog.findByctName(oldctname);
			findByCtName.setCtName(ctname);
			save = userRepositoryCatelog.save(findByCtName);
			return save;
		} catch (Exception e) {
			e.printStackTrace();
			return save;
		}
	}

	@Override
	public String deleteAllCategory() {
		try {
			userRepositoryCatelog.deleteAll();
			return "deleted all category";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String deleteByNameCategory(String ctname) {
		try {
			Category deleCategory = userRepositoryCatelog.findByctName(ctname);
			userRepositoryCatelog.deleteById(deleCategory.getId());
			return "delted :" + ctname;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<SubCategory> selectAllSubCategory(String ctname) {

		try {
			Category category = this.selectByIdCategory(ctname);
			System.out.println(category);
			
				return category.getSubcategory();
		
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public SubCategory  selectByIdSubCategory(String ctname,String subname) {


		SubCategory subCategory2=null;
		try {
			Category category = this.selectByIdCategory(ctname);
			List<SubCategory> subcategory = category.getSubcategory();
			Iterator<SubCategory> iterator = subcategory.iterator();
			while(iterator.hasNext())
			{
				 subCategory2 = iterator.next();
				if(subCategory2.getSubName().equalsIgnoreCase(subname))
				{
					return subCategory2;
				}
				else
				{
					return null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return subCategory2;
		}
		return subCategory2;

	}

	@Override
	public SubCategory insertSubCategory(SubCategory category8) {
		SubCategory category2 = null;
		try {
			List<SubCategory> list = userRepositorySubCatelog.findAll();
			Iterator<SubCategory> iterator = list.iterator();
			if (iterator.hasNext()) {
				while (iterator.hasNext()) {
					SubCategory category = iterator.next();
					if (!(category.getSubName()).equalsIgnoreCase(category8.getSubName())) {

						SubCategory category5 = userRepositorySubCatelog.save(category8);
						category2 = category5;

					}
				}
			} else {
				SubCategory category5 = userRepositorySubCatelog.save(category8);
				category2 = category5;
			}

			return category2;
		} catch (Exception e) {
			e.printStackTrace();
			return category2;
		}
	}

	@Override
	public SubCategory updateSubCategory(String ctname, String oldctname, String subname) {
		SubCategory subCategory2 = null;
		try {
			Category category = this.selectByIdCategory(ctname);
			if (category != null) {
				List<SubCategory> subCategories = category.getSubcategory();
				Iterator<SubCategory> iterator = subCategories.iterator();
				while (iterator.hasNext()) {
					SubCategory subCategory = iterator.next();
					if (subCategory.getSubName().equalsIgnoreCase(oldctname)) {
						subCategory.setSubName(subname);
						//subCategory2 = userRepositorySubCatelog.save(subCategory);
						category.getSubcategory().add(subCategory);
						///code
						subCategory2 = this.selectByIdSubCategory(ctname, subname);
						return subCategory2;
					}
				}
			} else {
				return subCategory2;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return subCategory2;
		}
		return subCategory2;
	}

	@Override
	public String deleteAllSubCatory(String ctname) {
		try {
			List<SubCategory> categories = new ArrayList<SubCategory>();
			Category category = this.selectByIdCategory(ctname);
			if (category != null) {
				List<SubCategory> listsubcaCategories = category.getSubcategory();
				Iterator<SubCategory> iterator = listsubcaCategories.iterator();
				while (iterator.hasNext()) {
					SubCategory subCategory = iterator.next();
					subCategory = null;
					categories.add(subCategory);
				}
				category.setSubcategory(categories);
				this.insertCategory(category);
				return "AllDeleted";

			} else {
				return "notFoud";
			}

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String deleteByNameSubCategory(String ctname, String subname) {
		List<SubCategory> subcategories = new ArrayList<SubCategory>();
		try {
			Category category = this.selectByIdCategory(ctname);
			List<SubCategory> subcategory = category.getSubcategory();
			Iterator<SubCategory> iterator = subcategory.iterator();
			int flag = 0;
			while (iterator.hasNext()) {
				SubCategory subCategory2 = iterator.next();

				if (subCategory2.getSubName().equalsIgnoreCase(subname)) {
					flag++;
					subCategory2 = null;

					subcategories.add(subCategory2);

				} else {
					subcategories.add(subCategory2);

				}

			}
			category.setSubcategory(subcategories);
			this.insertCategory(category);
			if (flag != 0) {
				return "Subcotgree is deleted:" + subname;
			} else {
				return "Subcategories is not deleted";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<SubSubCategory> selectAllSubSubCategory() {

		List<SubSubCategory> findAll = null;
		try {
			findAll = userRepositorySubSubCategory.findAll();
			return findAll;
		} catch (Exception e) {
			e.printStackTrace();
			return findAll;
		}
	}

	@Override
	public SubSubCategory selectByIdSubSubCategory(String ctname) {
		SubSubCategory findByCtName = null;
		try {
			findByCtName = userRepositorySubSubCategory.findBysubsubName(ctname);
			return findByCtName;
		} catch (Exception e) {
			e.printStackTrace();
			return findByCtName;
		}
	}

	@Override
	public SubCategory insertSubSubCategory(SubCategory subCategory1) {
		SubCategory subCategory = null;
		try {

			subCategory = userRepositorySubCatelog.save(subCategory1);
			return subCategory;

		} catch (Exception e) {
			e.printStackTrace();
			return subCategory;
		}
	}

	@Override
	public SubSubCategory updateSubSubCategory(String oldctname, String ctname) {
		SubSubCategory subSubCategory2 = null;
		try {
			SubSubCategory subSubCategory = userRepositorySubSubCategory.findBysubsubName(oldctname);
			subSubCategory.setSubsubName(ctname);
			subSubCategory2 = userRepositorySubSubCategory.save(subSubCategory);
			return subSubCategory2;
		} catch (Exception e) {
			e.printStackTrace();
			return subSubCategory2;
		}
	}

	@Override
	public String deleteAllSubSubCategory() {
		try {
			userRepositorySubSubCategory.deleteAll();
			return "All SubSub Category";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String deleteByNameSubSubCategory(String ctname) {
		try {
			userRepositorySubSubCategory.deleteBysubsubName(ctname);
			return "SubSubCategory is  deleted";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public SubSubCategory insertSubSubCategory(String subname, SubCategory subCategory, SubSubCategory category) {
		// TODO Auto-generated method stub
		return null;
	}

	public SubSubCategory updateSubSubCategory(String ctname, String subname, String oldsubsubname,
			String newsubsubname) {
		Category category = this.selectByIdCategory(ctname);
		int flag = 0;
		SubSubCategory subSubCategory2 = null;
		SubCategory subCategory2 = null;
		SubSubCategory selectByIdSubSubCategory = null;
		try {
			if (category != null) {
				List<SubCategory> subcategory = category.getSubcategory();
				Iterator<SubCategory> iterator = subcategory.iterator();
				while (iterator.hasNext()) {
					subCategory2 = iterator.next();
					if (subCategory2.getSubName().equals(subname)) {
						List<SubSubCategory> subsubcategory = subCategory2.getSubsubcategory();
						Iterator<SubSubCategory> iterator2 = subsubcategory.iterator();
						while (iterator2.hasNext()) {
							subSubCategory2 = iterator2.next();
							if (subSubCategory2.getSubsubName().equalsIgnoreCase(newsubsubname)) {
								flag++;
							}
						}
					}
					if (flag == 0) {
						subSubCategory2.setSubsubName(newsubsubname);
						subCategory2.getSubsubcategory().add(subSubCategory2);
						category.getSubcategory().add(subCategory2);
						this.insertCategory(category);
						selectByIdSubSubCategory = this.selectByIdSubSubCategory(newsubsubname);
						return selectByIdSubSubCategory;

					}
				}
			} else {
				return selectByIdSubSubCategory;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return selectByIdSubSubCategory;
		}

		return selectByIdSubSubCategory;
	}

}
