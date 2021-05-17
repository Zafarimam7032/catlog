package com.grwt.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grwt.dao.CotegoryDao;
import com.grwt.dao.itfc.CrudInterfaceForController;
import com.grwt.main.Category;
import com.grwt.main.SubCategory;
import com.grwt.main.SubSubCategory;

@RestController
@RequestMapping(path = "/home")
public class CategoryController implements CrudInterfaceForController {
	/*
	 * @Autowired private Category category;
	 * 
	 * @Autowired private SubCategory subCategory;
	 * 
	 * @Autowired private SubSubCategory subSubCategory;
	 * 
	 * @Autowired
	 */
	@Autowired
	private CotegoryDao catdao;
	// @Autowired
	List<SubCategory> subCategories = new ArrayList<>();
	// @Autowired
	List<SubSubCategory> subSubCategories = new ArrayList<SubSubCategory>();

	@GetMapping(path = "/catelog/all")
	@Override
	public ResponseEntity<List<Category>> selectAllCategory() {
		List<Category> selectAllCategory = catdao.selectAllCategory();
		if (selectAllCategory.size() == 0) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.of(Optional.of(selectAllCategory));
	}

	@GetMapping(path = "/catelog/{ctname}")
	@Override
	public ResponseEntity<Category> selectByIdCategory(@PathVariable("ctname") String ctname) {
		Category category2 = catdao.selectByIdCategory(ctname);
		if (category2 == null) {
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.of(Optional.of(category2));
	}

	@PostMapping(path = "catelog/insert")
	@Override
	public ResponseEntity<Category> insertCategory(@RequestBody Category category) {
		List<Category> selectAllCategory = catdao.selectAllCategory();
		Iterator<Category> iterator = selectAllCategory.iterator();
		int flag = 0;
		if ((selectAllCategory.size() > 0))

		{
			while (iterator.hasNext()) {
				Category category3 = iterator.next();
				String ctName = category3.getCtName();
				String ctName2 = category.getCtName();
				if ((ctName.equalsIgnoreCase(ctName2))) {
					flag++;

				}
			}
		} else if (selectAllCategory.size() == 0) {

			try {
				flag++;
				Category category2 = catdao.insertCategory(category);
				return ResponseEntity.of(Optional.of(category2));
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		if (flag == 0) {
			try {
				Category category2 = catdao.insertCategory(category);
				return ResponseEntity.of(Optional.of(category2));

			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} else {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@PutMapping(path = "catelog/update/{oldctname}/{ctname}")
	@Override
	public ResponseEntity<Category> updateCategory(@PathVariable("oldctname") String oldctname,
			@PathVariable("ctname") String ctname) {
		try {
			Category updateCategory = catdao.updateCategory(oldctname, ctname);
			return ResponseEntity.of(Optional.of(updateCategory));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping(path = "catelog/delete/all")
	@Override
	public ResponseEntity<String> deleteAllCategory() {
		try {
			String deleteAllCategory = catdao.deleteAllCategory();
			return ResponseEntity.of(Optional.of(deleteAllCategory));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@DeleteMapping(path = "catelog/delete/{ctname}")
	@Override
	public ResponseEntity<String> deleteByNameCategory(@PathVariable("ctname") String ctname) {
		try {
			String deleteByNameCategory = catdao.deleteByNameCategory(ctname);
			return ResponseEntity.of(Optional.of(deleteByNameCategory));

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping(path = "subcatelog/all/{ctname}")
	@Override
	public ResponseEntity<List<SubCategory>> selectAllSubCategory(@PathVariable("ctname") String ctname) {
		List<SubCategory> selectAllSubCategory = catdao.selectAllSubCategory(ctname);
		System.out.println(selectAllSubCategory.size());
		if (selectAllSubCategory.size() > 0) {
			return ResponseEntity.of(Optional.of(selectAllSubCategory));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

	}

	@GetMapping(path = "subcatelog/all/{ctname}/{subname}")
	@Override
	public ResponseEntity<SubCategory> selectByIdSubCategory(@PathVariable("ctname") String ctname,
			@PathVariable("subname") String subname) {

		try {

			SubCategory subCategory = catdao.selectByIdSubCategory(ctname, subname);
			if (subCategory != null) {
				return ResponseEntity.of(Optional.of(subCategory));
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}

	}

	@PostMapping(path = "subcatelog/insert/{ctname}")
	@Override
	public ResponseEntity<SubCategory> insertSubCategory(@PathVariable("ctname") String ctname,
			@RequestBody SubCategory subcategory) {

		Category category = catdao.selectByIdCategory(ctname);
		List<SubCategory> listSubCategories = category.getSubcategory();
		Iterator<SubCategory> iterator = listSubCategories.iterator();
		int flag = 0;
		if (listSubCategories.size() > 0) {
			while (iterator.hasNext()) {
				SubCategory subCategory2 = iterator.next();
				String string = subcategory.getSubName();

				String subName = subCategory2.getSubName();

				if (subName.equalsIgnoreCase(string)) {
					flag++;
				}
			}

		} else if (listSubCategories.size() == 0) {
			try {
				flag++;
				System.out.println("inside else if");
				Category category2 = catdao.selectByIdCategory(ctname);
				category2.getSubcategory().add(subcategory);
				System.out.println(category2);
				catdao.insertCategory(category2);
				List<Category> selectAllCategory = catdao.selectAllCategory();
				Iterator<Category> iterator2 = selectAllCategory.iterator();
				while (iterator2.hasNext()) {
					Category category3 = iterator2.next();
					List<SubCategory> subcategory2 = category3.getSubcategory();
					Iterator<SubCategory> iterator3 = subcategory2.iterator();
					while (iterator3.hasNext()) {
						SubCategory subCategory3 = iterator3.next();
						if (subCategory3.getSubName().equalsIgnoreCase(subcategory.getSubName())) {
							return ResponseEntity.of(Optional.of(subCategory3));
						}
					}
				}
			}

			catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} else

		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		if (flag == 0) {
			try {
				flag++;
				Category category2 = catdao.selectByIdCategory(ctname);
				category2.getSubcategory().add(subcategory);
				catdao.insertCategory(category2);
				List<Category> selectAllCategory = catdao.selectAllCategory();
				Iterator<Category> iterator2 = selectAllCategory.iterator();
				while (iterator2.hasNext()) {
					Category category3 = iterator2.next();
					List<SubCategory> subcategory2 = category3.getSubcategory();
					Iterator<SubCategory> iterator3 = subcategory2.iterator();
					while (iterator3.hasNext()) {
						SubCategory subCategory3 = iterator3.next();
						if (subCategory3.getSubName().equalsIgnoreCase(subcategory.getSubName())) {
							return ResponseEntity.of(Optional.of(subCategory3));
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return null;
	}

	@PutMapping(path = "subcategory/update/{ctname}/{oldsubname}/{newsubname}")
	@Override
	public ResponseEntity<SubCategory> updateSubCategory(@PathVariable("ctname") String ctname,
			@PathVariable("oldsubname") String oldctname, @PathVariable("newsubname") String subname) {
		try {
			SubCategory updateSubCategory = catdao.updateSubCategory(ctname, oldctname, subname);
			return ResponseEntity.of(Optional.of(updateSubCategory));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping(path = "subcatelog/delete/all/{ctname}")
	@Override
	public ResponseEntity<String> deleteAllSubCatory(@PathVariable("ctname") String ctname) {
		try {
			String deleteAllSubCatory = catdao.deleteAllSubCatory(ctname);
			return ResponseEntity.of(Optional.of(deleteAllSubCatory));
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping(path = "subcatelog/delete/{ctname}/{subname}")
	@Override
	public ResponseEntity<String> deleteByNameSubCategory(@PathVariable("ctname") String ctname,
			@PathVariable("subname") String subname) {
		try {
			String deleteByNameSubCategory = catdao.deleteByNameSubCategory(ctname, subname);
			return ResponseEntity.of(Optional.of(deleteByNameSubCategory));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping(path = "subsubcatelog/all")
	@Override
	public ResponseEntity<List<SubSubCategory>> selectAllSubSubCategory() {

		List<SubSubCategory> selectAllSubSubCategory = catdao.selectAllSubSubCategory();
		if (selectAllSubSubCategory.size() == 0) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.of(Optional.of(selectAllSubSubCategory));

	}

	@GetMapping(path = "subsubcatelog/{ctname}")
	@Override
	public ResponseEntity<SubSubCategory> selectByIdSubSubCategory(@PathVariable("ctname") String ctname) {
		SubSubCategory selectByIdSubSubCategory = catdao.selectByIdSubSubCategory(ctname);
		if (selectByIdSubSubCategory == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.of(Optional.of(selectByIdSubSubCategory));
	}

	@PostMapping(path = "subsubcatelog/insert/{ctname}/{subName}")
	@Override
	public ResponseEntity<SubSubCategory> insertSubSubCategory(@PathVariable("ctname") String ctname,
			@PathVariable("subName") String subName, @RequestBody SubSubCategory subsubcategory) {
		int flag = 0;
		try {
			Category Category = catdao.selectByIdCategory(ctname);
			List<SubCategory> subcategory = Category.getSubcategory();
			Iterator<SubCategory> iterator = subcategory.iterator();
			while (iterator.hasNext()) {
				SubCategory subCategory2 = iterator.next();
				if (subCategory2.getSubName().equalsIgnoreCase(subName)) {
					List<SubSubCategory> subsubcategory2 = subCategory2.getSubsubcategory();
					Iterator<SubSubCategory> iterator2 = subsubcategory2.iterator();
					if (subsubcategory2.size() > 0) {
						while (iterator2.hasNext()) {
							SubSubCategory subSubCategory3 = iterator2.next();
							if (subSubCategory3.getSubsubName().equalsIgnoreCase(subsubcategory.getSubsubName())) {
								flag++;
							}

						}

					} else if (subsubcategory2.size() == 0) {
						flag++;
						subCategory2.getSubsubcategory().add(subsubcategory);
						catdao.insertSubCategory(subCategory2);
						Category displayCategory = catdao.selectByIdCategory(ctname);
						List<SubCategory> subcategory3 = displayCategory.getSubcategory();
						Iterator<SubCategory> iterator3 = subcategory3.iterator();
						while (iterator3.hasNext()) {
							SubCategory subCategory4 = iterator3.next();
							List<SubSubCategory> subsubcategory3 = subCategory4.getSubsubcategory();
							Iterator<SubSubCategory> iterator4 = subsubcategory3.iterator();
							while (iterator4.hasNext()) {
								SubSubCategory subSubCategory4 = iterator4.next();
								if (subSubCategory4.getSubsubName().equalsIgnoreCase(subsubcategory.getSubsubName())) {
									return ResponseEntity.of(Optional.of(subSubCategory4));
								}
							}
						}

					} else {
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
					}

					if (flag == 0) {
						flag++;
						subCategory2.getSubsubcategory().add(subsubcategory);
						catdao.insertSubCategory(subCategory2);
						Category displayCategory = catdao.selectByIdCategory(ctname);
						List<SubCategory> subcategory3 = displayCategory.getSubcategory();
						Iterator<SubCategory> iterator3 = subcategory3.iterator();
						while (iterator3.hasNext()) {
							SubCategory subCategory4 = iterator3.next();
							List<SubSubCategory> subsubcategory3 = subCategory4.getSubsubcategory();
							Iterator<SubSubCategory> iterator4 = subsubcategory3.iterator();
							while (iterator4.hasNext()) {
								SubSubCategory subSubCategory4 = iterator4.next();
								if (subSubCategory4.getSubsubName().equalsIgnoreCase(subsubcategory.getSubsubName())) {
									return ResponseEntity.of(Optional.of(subSubCategory4));
								}
							}
						}
					} else {
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
					}

				}
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return null;

	}

	@PutMapping(path = "subsubcatelog/update/{ctname}/{subname}/{oldsubsubname}/{newsubsubname}")
	@Override
	public ResponseEntity<SubSubCategory> updateSubSubCategory(@PathVariable("ctname") String ctname,
			@PathVariable("subname") String subname, @PathVariable("oldsubsubname") String oldsubsubname,
			@PathVariable("newsubsubname") String newsubsubname) {
		try {
			SubSubCategory subSubCategory2 = catdao.updateSubSubCategory(ctname, subname, oldsubsubname, newsubsubname);
			return ResponseEntity.of(Optional.of(subSubCategory2));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping(path = "subsubcatelog/delete/all/{ctname}/{subname}/{subsubname}")
	@Override
	public ResponseEntity<String> deleteAllSubSubCategory(String ctname, String subname, String subsubname) {
		try {
			String deleteAllSubSubCategory = catdao.deleteAllSubSubCategory();
			return ResponseEntity.of(Optional.of(deleteAllSubSubCategory));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping(path = "subsubcatelog/delete/{ctname}")
	@Override
	public ResponseEntity<String> deleteByNameSubSubCategory(@PathVariable("ctname") String ctname) {
		try {
			String deleteByNameSubSubCategory = catdao.deleteByNameSubSubCategory(ctname);
			return ResponseEntity.of(Optional.of(deleteByNameSubSubCategory));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
