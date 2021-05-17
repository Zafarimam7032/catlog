package com.grwts.controller.intrfc;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.grwts.main.CatelogVo;



public interface CatelogController {
	public ResponseEntity<List<CatelogVo>> findAllCatelogEntity();
	public ResponseEntity<CatelogVo> findByIdCatelogEntity(int catelogId);
	public ResponseEntity<CatelogVo> updateCatelogEntity(int catelogId,String catelogName);
	public ResponseEntity<CatelogVo> insertCatelog(CatelogVo catelogVo);
	public ResponseEntity<String> deleteAllEntity();
	public ResponseEntity<String> deleteByIdCatelogEntity(int catelogId);
	public ResponseEntity<String> restore(int catelogId);
	public ResponseEntity<String> restoreAll();

}
