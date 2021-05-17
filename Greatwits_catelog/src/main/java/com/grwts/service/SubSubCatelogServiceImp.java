package com.grwts.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grwts.main.CatelogRepository;
import com.grwts.main.SubCatelogVo;
import com.grwts.main.SubRepository;
import com.grwts.main.SubSubCatelogVo;
import com.grwts.main.SubSubRepository;
import com.grwts.service.iterfc.SubSubCatelogService;

@Component
public class SubSubCatelogServiceImp implements SubSubCatelogService {
	@Autowired
	private SubSubRepository subSubRepository;
	@Autowired
	private SubRepository subRepository;
	@Autowired
	private CatelogRepository catelogRepository;

	@Override
	public List<SubSubCatelogVo> allSubSubCatelogList(int catelogId, int subcatelogId) {
		List<SubSubCatelogVo> subSubCatelogVos = new ArrayList<SubSubCatelogVo>();
		List<SubSubCatelogVo> listSubSubCatelogVos = subSubRepository.findAll();
		Iterator<SubSubCatelogVo> iterator = listSubSubCatelogVos.iterator();
		while (iterator.hasNext()) {
			SubSubCatelogVo subSubCatelogVo = iterator.next();
			if (subSubCatelogVo.getSubCatelogVo().getSubCatelogId() == subcatelogId) {
				if (subSubCatelogVo.getSubCatelogVo().getCatelogVo().getCatelogid() == catelogId) {
					subSubCatelogVos.add(subSubCatelogVo);
				}
			}
		}
		if (subSubCatelogVos.size() > 0) {
			return listSubSubCatelogVos;
		} else {
			return null;
		}
	}

	@Override
	public SubSubCatelogVo subSubCatelogById(int subSubCatelogid) {
		SubSubCatelogVo subSubCatelogVo = subSubRepository.findById(subSubCatelogid).orElse(null);
		return subSubCatelogVo;
	}

	@Override
	public SubSubCatelogVo insertSubSubCatelog(int catelogId, int subcatelogId, SubSubCatelogVo subSubCatelogVo) {
		List<SubSubCatelogVo> listSubSubCatelogVos = subSubRepository.findAll();
		Iterator<SubSubCatelogVo> iterator = listSubSubCatelogVos.iterator();
		int flag = 0;
		if (listSubSubCatelogVos.size() > 0) {
			if (iterator.hasNext()) {
				SubSubCatelogVo subSubCatelogVo2 = iterator.next();
				if(subSubCatelogVo2.getSubCatelogVo().getSubCatelogId()==subcatelogId)
				{
					if(subSubCatelogVo2.getSubSubName().equalsIgnoreCase(subSubCatelogVo.getSubSubName()))
					{
						flag++;
					}
				}

			}
		} else if (listSubSubCatelogVos.size() == 0) {
			flag++;
			SubCatelogVo findSubCatelogVo = subRepository.findById(subcatelogId).orElse(null);
			subSubCatelogVo.setSubCatelogVo(findSubCatelogVo);
			SubSubCatelogVo saveSubSubCatelogVo = subSubRepository.save(subSubCatelogVo);
			return saveSubSubCatelogVo;
		} else {
			return null;
		}
		if (flag == 0) {
			flag++;
			SubCatelogVo findSubCatelogVo = subRepository.findById(subcatelogId).orElse(null);
			subSubCatelogVo.setSubCatelogVo(findSubCatelogVo);

			SubSubCatelogVo saveSubSubCatelogVo = subSubRepository.save(subSubCatelogVo);
			return saveSubSubCatelogVo;
		} else {
			return null;
		}
	}

	@Override
	public SubSubCatelogVo updateSubSubCatelog(int subcatelogId, String subSubCatelogName) {
		SubSubCatelogVo subSubCatelogVo = subSubRepository.findById(subcatelogId).orElse(null);
		subSubCatelogVo.setSubSubName(subSubCatelogName);
		SubSubCatelogVo saveSubSubCatelogVo = subSubRepository.save(subSubCatelogVo);

		return saveSubSubCatelogVo;
	}

	@Override
	public String deleteAllSubSubCatelog() {
		subSubRepository.deleteAll();
		return "alldeleted";
	}

	@Override
	public String deleteSubSubCatelogById(int subSubcatelogId) {
		subSubRepository.deleteById(subSubcatelogId);
		return "deleted:" + subSubcatelogId;
	}

}
