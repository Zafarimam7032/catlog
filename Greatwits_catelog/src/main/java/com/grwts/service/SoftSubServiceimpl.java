package com.grwts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grwts.main.SoftSubCatelogVo;
import com.grwts.main.SoftSubRepository;

@Component
public class SoftSubServiceimpl implements com.grwts.service.iterfc.SoftSubService {
	@Autowired
	private SoftSubRepository softSubRepository;

	@Override
	public boolean insertSoftSubCatelog(SoftSubCatelogVo softSubCatelogVo) {
		boolean check = false;
		SoftSubCatelogVo saveSoftSubCatelogVo = softSubRepository.save(softSubCatelogVo);
		if (saveSoftSubCatelogVo != null) {
			check = true;
			return check;
		} else {
			return check;
		}

	}

	@Override
	public List<SoftSubCatelogVo> selectAll() {
		List<SoftSubCatelogVo> listSoftSubCatelogVos = softSubRepository.findAll();
		return listSoftSubCatelogVos;
	}

	@Override
	public SoftSubCatelogVo selectById(int id) {
		 SoftSubCatelogVo softSubCatelogVo = softSubRepository.findById(id).orElse(null);
		return softSubCatelogVo;
	}

}
