package com.grwt.main;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryCatelog extends JpaRepository<Category, Integer>  {
public Category findByctName(String ctname);
public void deleteByctName(String ctname);
}
