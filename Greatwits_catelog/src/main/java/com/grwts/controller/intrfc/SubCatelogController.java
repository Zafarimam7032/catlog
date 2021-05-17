package com.grwts.controller.intrfc;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.grwts.main.SubCatelogVo;



public interface SubCatelogController {
	public ResponseEntity<List<SubCatelogVo>> findAllSubCatelogEntity(int catelogId);
	public ResponseEntity<SubCatelogVo> findByIdSubCatelogEntity(int subCatelogId);
	public ResponseEntity<SubCatelogVo> updateSubCatelogEntity(int subCatelogId,String subCatelogName);
	public ResponseEntity<SubCatelogVo> insertSubCatelogEntity(SubCatelogVo subCatelogVo,int catelogId);
	public ResponseEntity<String> deleteAllSubCatelogVoEntity(int catelogId);
	public ResponseEntity<String> deleteByIdSubCatelogEntity(int catelogId,int subcatelogId);
	public ResponseEntity<String> restore(int catelogId,int subcatelogId);
	public ResponseEntity<String> restoreAll(int catelogId);

}
