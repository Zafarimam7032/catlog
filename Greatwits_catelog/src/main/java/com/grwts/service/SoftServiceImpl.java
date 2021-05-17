package com.grwts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grwts.main.SoftCatelogVo;
import com.grwts.main.SoftRepository;
import com.grwts.service.iterfc.SoftCatelogService;
@Component
public class SoftServiceImpl implements SoftCatelogService {
	@Autowired
	private SoftRepository softrepository;

	@Override
	public boolean insertCatelog(SoftCatelogVo softCatelogVo) {
		boolean  check=false;
		SoftCatelogVo softCatelogVo2 = softrepository.save(softCatelogVo);
		if(softCatelogVo2!=null)
		{
			check=true;
			return check;
			
		}
		else
		{
			return	check;
		}
		 
	}

	@Override
	public List<SoftCatelogVo> selectAll() {
		List<SoftCatelogVo> listSoftCatelogVos = softrepository.findAll();
		return listSoftCatelogVos;
	}

	@Override
	public SoftCatelogVo selectById(int id) {
		SoftCatelogVo softCatelogVo= softrepository.findById(id).orElse(null);
		return softCatelogVo;
	}

}
