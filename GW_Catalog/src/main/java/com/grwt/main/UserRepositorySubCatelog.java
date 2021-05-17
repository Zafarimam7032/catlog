package com.grwt.main;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositorySubCatelog  extends JpaRepository<SubCategory, Integer>{
	public SubCategory findBysubName(String ctname);
	public void deleteBysubName(String ctname);
}
