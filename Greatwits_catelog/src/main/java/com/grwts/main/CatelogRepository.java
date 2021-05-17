package com.grwts.main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatelogRepository  extends JpaRepository<CatelogVo, Integer>{

}
