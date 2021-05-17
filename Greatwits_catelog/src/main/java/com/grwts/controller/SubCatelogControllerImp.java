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

import com.grwts.controller.intrfc.SubCatelogController;
import com.grwts.main.SoftSubCatelogVo;
import com.grwts.main.SubCatelogVo;
import com.grwts.service.SoftSubServiceimpl;
import com.grwts.service.SubCatelogServiceImp;

@RestController
@RequestMapping(path = "/subcatelog")
public class SubCatelogControllerImp implements SubCatelogController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SubCatelogControllerImp.class);
	@Autowired
	private SubCatelogServiceImp subCatelogService;
	@Autowired
	private SoftSubServiceimpl softSubServiceimpl;

	@GetMapping(path = "/all/{catelogId}")
	@Override
	public ResponseEntity<List<SubCatelogVo>> findAllSubCatelogEntity(@PathVariable("catelogId") int catelogId) {
		try {
			List<SubCatelogVo> allSubCatelogList = subCatelogService.allSubCatelogList(catelogId);
			return ResponseEntity.of(Optional.of(allSubCatelogList));

		} catch (NullPointerException e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	@GetMapping(path = "/{subCatelogId}")
	@Override
	public ResponseEntity<SubCatelogVo> findByIdSubCatelogEntity(@PathVariable("subCatelogId") int subCatelogId) {
		try {
			SubCatelogVo subCatelogById = subCatelogService.subCatelogById(subCatelogId);
			return ResponseEntity.of(Optional.of(subCatelogById));

		} catch (NullPointerException e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	@PutMapping(path = "/update/{subCatelogId}/{subCatelogName}")
	@Override
	public ResponseEntity<SubCatelogVo> updateSubCatelogEntity(@PathVariable("subCatelogId") int subCatelogId,
			@PathVariable("subCatelogName") String subCatelogName) {
		try {
			SubCatelogVo updateSubCatelog = subCatelogService.updateSubCatelog(subCatelogId, subCatelogName);
			return ResponseEntity.of(Optional.of(updateSubCatelog));

		} catch (NullPointerException e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	@PostMapping("/insert/{catelogId}")
	@Override
	public ResponseEntity<SubCatelogVo> insertSubCatelogEntity(@RequestBody SubCatelogVo subCatelogVo,
			@PathVariable("catelogId") int catelogId) {
		try {

			SubCatelogVo insertSubCatelog = subCatelogService.insertSubCatelog(subCatelogVo, catelogId);
			return ResponseEntity.of(Optional.of(insertSubCatelog));
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
	public ResponseEntity<String> deleteAllSubCatelogVoEntity(@PathVariable("catelogId") int catelogId) {
		try {
			String deleteAllSubCatelog = subCatelogService.deleteAllSubCatelog(catelogId);
			return ResponseEntity.ok(deleteAllSubCatelog);
		} catch (NullPointerException e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@DeleteMapping(path = "/delete/{catelogId}/{subcatelogId}")
	@Override
	public ResponseEntity<String> deleteByIdSubCatelogEntity(@PathVariable("catelogId") int catelogId,
			@PathVariable("subcatelogId") int subcatelogId) {
		try {
			String deleteSubCatelogById = subCatelogService.deleteSubCatelogById(catelogId, subcatelogId);
			return ResponseEntity.ok(deleteSubCatelogById);
		} catch (NullPointerException e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping(path = "/restore/{catelogId}/{subcatelogId}")
	@Override
	public ResponseEntity<String> restore(@PathVariable("catelogId") int catelogId,
			@PathVariable("subcatelogId") int subcatelogId) {
		try {
			String restore = subCatelogService.restore(catelogId, subcatelogId);
			return ResponseEntity.of(Optional.of(restore));
		} catch (NullPointerException e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	@GetMapping(path = "restoreAll/{catelogId}")
	@Override
	public ResponseEntity<String> restoreAll(@PathVariable("catelogId") int catelogId) {
		try {
			String restore = subCatelogService.restoreAll(catelogId);
			return ResponseEntity.of(Optional.of(restore));
		} catch (NullPointerException e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
