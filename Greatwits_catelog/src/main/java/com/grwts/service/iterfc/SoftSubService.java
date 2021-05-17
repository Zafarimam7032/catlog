package com.grwts.service.iterfc;



import java.util.List;

import com.grwts.main.SoftSubCatelogVo;

public interface SoftSubService {
	public boolean insertSoftSubCatelog(SoftSubCatelogVo softSubCatelogVo);
	public  List<SoftSubCatelogVo> selectAll();
	public  SoftSubCatelogVo selectById(int id);

}
