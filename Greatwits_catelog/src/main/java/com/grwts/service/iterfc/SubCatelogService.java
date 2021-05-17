package com.grwts.service.iterfc;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.grwts.main.SubCatelogVo;



public interface SubCatelogService {
	public List<SubCatelogVo> allSubCatelogList(int catelogId);
	public SubCatelogVo subCatelogById(int subCatelogid);
	public SubCatelogVo insertSubCatelog(SubCatelogVo subCatelogVo,int catelogId); 
	public SubCatelogVo updateSubCatelog(int subcatelogId,String subCatelogName);
	public String deleteAllSubCatelog(int catelogId);
	public String deleteSubCatelogById(int catelogId,int subcatelogId);
	public String restore(int catelogId,int subcatelogId);
	public String restoreAll(int catelogId);
}
