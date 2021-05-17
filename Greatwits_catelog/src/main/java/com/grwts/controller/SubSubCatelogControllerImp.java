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

import com.grwts.controller.intrfc.SubSubCatelogController;
import com.grwts.main.SubSubCatelogVo;
import com.grwts.service.SubSubCatelogServiceImp;



@RestController
@RequestMapping(path = "/subsubcatelog")
public class SubSubCatelogControllerImp implements SubSubCatelogController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SubSubCatelogControllerImp.class);
	@Autowired
	private SubSubCatelogServiceImp subSubCatelogService;

	@GetMapping("/all/{catelogId}/{subcatelogId}")
	@Override
	public ResponseEntity<List<SubSubCatelogVo>> findAllSubSubCatelogEntity(@PathVariable("catelogId")  int catelogId,@PathVariable("subcatelogId") int subcatelogId) {
		try {
			List<SubSubCatelogVo> listSubSubCatelogVos = subSubCatelogService.allSubSubCatelogList(catelogId, subcatelogId);
			return ResponseEntity.of(Optional.of(listSubSubCatelogVos));
		} catch (NullPointerException e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	@GetMapping(path = "/{subSubCatelogId}")
	@Override
	public ResponseEntity<SubSubCatelogVo> findByIdSubSubCatelogEntity(
			@PathVariable("subSubCatelogId") int subSubCatelogId) {
		try {
			SubSubCatelogVo subSubCatelogById = subSubCatelogService.subSubCatelogById(subSubCatelogId);
			return ResponseEntity.of(Optional.of(subSubCatelogById));
		} catch (NullPointerException e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PutMapping(path = "update/{subSubCatelogId}/{subSubCatelogName}")
	@Override
	public ResponseEntity<SubSubCatelogVo> updateSubSubCatelogEntity(int subSubCatelogId, String subSubCatelogName) {
		try {
			SubSubCatelogVo updateSubSubCatelog = subSubCatelogService.updateSubSubCatelog(subSubCatelogId, subSubCatelogName);
			return ResponseEntity.of(Optional.of(updateSubSubCatelog));
		} catch (NullPointerException e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PostMapping(path = "/insert")
	@Override
	public ResponseEntity<SubSubCatelogVo> insertSubSubCatelogEntity(int catelogId,int subcatelogId,@RequestBody SubSubCatelogVo subSubCatelogVo) {
		try {

			SubSubCatelogVo insertSubSubCatelog = subSubCatelogService.insertSubSubCatelog(catelogId, subcatelogId, subSubCatelogVo);
			return ResponseEntity.of(Optional.of(insertSubSubCatelog));
		} catch (NullPointerException e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@DeleteMapping("/delete")
	@Override
	public ResponseEntity<String> deleteAllSubSubCatelogVoEntity() {
		try {

			String deleteAllSubSubCatelog = subSubCatelogService.deleteAllSubSubCatelog();
			return ResponseEntity.of(Optional.of(deleteAllSubSubCatelog));
		} catch (NullPointerException e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@DeleteMapping(path = "/delete/{catelogId}")
	@Override
	public ResponseEntity<String> deleteByIdSubSubCatelogEntity(@PathVariable("catelogId") int catelogId) {
		try {

			String deleteSubSubCatelog = subSubCatelogService.deleteSubSubCatelogById(catelogId);
			return ResponseEntity.of(Optional.of(deleteSubSubCatelog));
		} catch (NullPointerException e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
