package com.grwts.service.iterfc;

import java.util.List;

import com.grwts.main.SoftCatelogVo;

public interface SoftCatelogService {
	public boolean insertCatelog(SoftCatelogVo softCatelogVo);
	public  List<SoftCatelogVo> selectAll();
	public  SoftCatelogVo selectById(int id);
	

}
