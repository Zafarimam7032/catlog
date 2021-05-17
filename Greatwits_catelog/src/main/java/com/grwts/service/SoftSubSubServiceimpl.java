package com.grwts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grwts.main.SoftSubSubCatelog;
import com.grwts.main.SoftSubSubRepository;
import com.grwts.service.iterfc.SoftSubSubService;
@Component
public class SoftSubSubServiceimpl implements SoftSubSubService {
	@Autowired
	private SoftSubSubRepository softSubSubRepository;

	@Override
	public boolean insertSubSubcatelog(SoftSubSubCatelog softSubSubCatelog) {
		boolean check=false;
		SoftSubSubCatelog saveSoftSubSubCatelog = softSubSubRepository.save(softSubSubCatelog);
		if(saveSoftSubSubCatelog!=null)
		{
			check=true;
			return check;
		}
		else
		{
			return check;
		}
	}

}
