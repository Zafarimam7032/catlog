package com.grwts.controller.intrfc;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.grwts.main.SubSubCatelogVo;



public interface SubSubCatelogController {
	public ResponseEntity<List<SubSubCatelogVo>> findAllSubSubCatelogEntity(int catelogId,int subcatelogId);
	public ResponseEntity<SubSubCatelogVo> findByIdSubSubCatelogEntity(int subSubCatelogId);
	public ResponseEntity<SubSubCatelogVo> updateSubSubCatelogEntity(int subSubCatelogId,String subSubCatelogName);
	public ResponseEntity<SubSubCatelogVo> insertSubSubCatelogEntity(int catelogId,int subcatelogId,SubSubCatelogVo subSubCatelogVo);
	public ResponseEntity<String> deleteAllSubSubCatelogVoEntity();
	public ResponseEntity<String> deleteByIdSubSubCatelogEntity(int catelogId);

}
