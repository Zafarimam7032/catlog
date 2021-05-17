package com.grwts.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grwts.controller.intrfc.CatelogController;
import com.grwts.main.CatelogVo;
import com.grwts.service.CatelogServiceImp;


@RestController
@RequestMapping(path = "/catelog")
public class CatelogControllerImp implements CatelogController{
	@Autowired
	private CatelogServiceImp catelogService;
	private static final Logger LOGGER = LoggerFactory
            .getLogger(CatelogControllerImp.class);
	@GetMapping(path = "/all")
	@Override
	public ResponseEntity<List<CatelogVo>> findAllCatelogEntity() {
		try {
			List<CatelogVo> allCatelogList = catelogService.allCatelogList();
			return ResponseEntity.of(Optional.of(allCatelogList));
		} catch (NullPointerException e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
@GetMapping(path = "/{catelogId}")
	@Override
	public ResponseEntity<CatelogVo> findByIdCatelogEntity(@PathVariable("catelogId") int catelogId) {
		try {
			CatelogVo catelogVo = catelogService.CatelogById(catelogId);
			return ResponseEntity.of(Optional.of(catelogVo));
		}
		catch (NullPointerException e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
@PutMapping(path = "/update/{catelogId}/{catelogName}")
	@Override
	public ResponseEntity<CatelogVo> updateCatelogEntity(@PathVariable("catelogId") int catelogId,@PathVariable("catelogName") String catelogName) {
		try {
			CatelogVo updateCatelog = catelogService.updateCatelog(catelogId, catelogName);
			return ResponseEntity.of(Optional.of(updateCatelog));
		}
		catch (NullPointerException e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	
	}
@DeleteMapping(path = "/delete")
	@Override
	public ResponseEntity<String> deleteAllEntity() {
	try {
		String deleteAllCatelog = catelogService.deleteAllCatelog();
		return ResponseEntity.of(Optional.of(deleteAllCatelog));
	}
	catch (NullPointerException e) {
		LOGGER.error(e.getLocalizedMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	catch (Exception e) {
		LOGGER.error(e.getLocalizedMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	}
@DeleteMapping(path = "/delete/{catelogId}")
	@Override
	public ResponseEntity<String> deleteByIdCatelogEntity(@PathVariable("catelogId") int catelogId) {
		try {
			String deleteCatelogById = catelogService.deleteCatelogById(catelogId);
			return ResponseEntity.of(Optional.of(deleteCatelogById));
		}
		catch (NullPointerException e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
@PostMapping(path = "/insert")
	@Override
	public ResponseEntity<CatelogVo> insertCatelog(@RequestBody CatelogVo catelogVo) {
		try {
		CatelogVo insertCatelog = catelogService.insertCatelog(catelogVo);
		return ResponseEntity.of(Optional.of(insertCatelog));
		
		}
		catch (NullPointerException e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
@GetMapping(path = "/restore/{catelogId}")
@Override
public ResponseEntity<String> restore(@PathVariable("catelogId") int catelogId) {
	try {
	String restore = catelogService.restore(catelogId);
	return ResponseEntity.of(Optional.of(restore));
	}
	catch (NullPointerException e) {
		LOGGER.error(e.getLocalizedMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	catch (Exception e) {
		LOGGER.error(e.getLocalizedMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}
@GetMapping(path = "/resotoreAll")
@Override
public ResponseEntity<String> restoreAll() {
	try {
		String restore = catelogService.restoreAll();
		return ResponseEntity.of(Optional.of(restore));
		}
		catch (NullPointerException e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
}

	
}
