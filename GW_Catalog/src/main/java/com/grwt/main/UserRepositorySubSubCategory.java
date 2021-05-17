package com.grwt.main;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositorySubSubCategory extends JpaRepository<SubSubCategory, Integer>{
	public SubSubCategory findBysubsubName(String ctname);
	public void deleteBysubsubName(String ctname);
}
