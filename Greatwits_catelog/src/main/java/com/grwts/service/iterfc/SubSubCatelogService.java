package com.grwts.service.iterfc;

import java.util.List;

import com.grwts.main.SubSubCatelogVo;



public interface SubSubCatelogService {
	public List<SubSubCatelogVo> allSubSubCatelogList(int catelogId,int subcatelogId);
	public SubSubCatelogVo subSubCatelogById(int subSubCatelogid);
	public SubSubCatelogVo insertSubSubCatelog(int catelogId,int subcatelogId,SubSubCatelogVo subSubCatelogVo); 
	public SubSubCatelogVo updateSubSubCatelog(int subcatelogId,String subSubCatelogName);
	public String deleteAllSubSubCatelog();
	public String deleteSubSubCatelogById(int subSubcatelogId);

}
