package com.grwts.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grwts.main.CatelogRepository;
import com.grwts.main.CatelogVo;
import com.grwts.main.SoftCatelogVo;
import com.grwts.service.iterfc.CatelogService;

@Component
public class CatelogServiceImp implements CatelogService {
	@Autowired
	private CatelogRepository catelogRepository;
	@Autowired
	private SoftServiceImpl softServiceImpl;

	@Override
	public List<CatelogVo> allCatelogList() {

		List<CatelogVo> cateloglist = catelogRepository.findAll();
		if (cateloglist.size() > 0) {
			return cateloglist;
		} else {
			return null;
		}
	}

	@Override
	public CatelogVo CatelogById(int catelogid) {
		CatelogVo catelogVo = catelogRepository.findById(catelogid).orElse(null);
		return catelogVo;
	}

	@Override
	public CatelogVo insertCatelog(CatelogVo catelogVo) {
		List<CatelogVo> listCatelogVos = catelogRepository.findAll();
		
		
		Iterator<CatelogVo> iterator = listCatelogVos.iterator();
		int flag = 0;
		if (listCatelogVos.size() > 0 ) {
			while (iterator.hasNext()) {
				CatelogVo catelogVo2 = iterator.next();
				if ((catelogVo2.getCatelogid() == catelogVo.getCatelogid())) {
					flag++;
				}
				if (catelogVo2.getCatelogName().equalsIgnoreCase(catelogVo.getCatelogName())) {
					flag++;
				}
			}
			List<SoftCatelogVo> listSoftCatelogVos = softServiceImpl.selectAll();
			Iterator<SoftCatelogVo> iterator2 = listSoftCatelogVos.iterator();
			while(iterator2.hasNext())
			{
				SoftCatelogVo softCatelogVo = iterator2.next();
				if(softCatelogVo.getCatelogid()==catelogVo.getCatelogid())
				{
					flag++;
				}
				if(softCatelogVo.getCatelogName().equalsIgnoreCase(catelogVo.getCatelogName()))
				{
					flag++;
				}
			}
		} else if (listCatelogVos.size() == 0) {
			flag++;
			CatelogVo catelogVo2 = catelogRepository.save(catelogVo);
			return catelogVo2;
		} else {
			return null;
		}
		if (flag == 0) {
			flag++;
			CatelogVo catelogVo2 = catelogRepository.save(catelogVo);
			return catelogVo2;
		} else {
			return null;
		}

	}

	@Override
	public CatelogVo updateCatelog(int catelogId, String catelogName) {
		CatelogVo catelogVo = catelogRepository.findById(catelogId).orElse(null);
		catelogVo.setCatelogName(catelogName);
		CatelogVo saveCatelogVo = catelogRepository.save(catelogVo);
		return saveCatelogVo;
	}

	@Override
	public String deleteAllCatelog() {
		boolean insertCatelog = false;
		List<CatelogVo> listCatelogVos = this.allCatelogList();
		if (listCatelogVos.size() > 0) {
			Iterator<CatelogVo> iterator = listCatelogVos.iterator();
			while (iterator.hasNext()) {
				CatelogVo catelogVo = iterator.next();
				SoftCatelogVo softCatelogVo = new SoftCatelogVo();
				softCatelogVo.setCatelogid(catelogVo.getCatelogid());
				softCatelogVo.setCatelogName(catelogVo.getCatelogName());
				softCatelogVo.setUserName(catelogVo.getUserName());
				softCatelogVo.setCatelogDate(catelogVo.getCatelogDate());
				System.out.println(softCatelogVo);
				insertCatelog = softServiceImpl.insertCatelog(softCatelogVo);
			}
			insertCatelog=true;
		}
		if (insertCatelog==true) {
			catelogRepository.deleteAll();
			return "alldeleted";
		} else {
			return null;
		}
	}

	@Override
	public String deleteCatelogById(int catelogId) {
		boolean insertCatelog = false;
		CatelogVo catelogVo = catelogRepository.findById(catelogId).orElse(null);
		if (catelogVo != null) {
			SoftCatelogVo softCatelogVo = new SoftCatelogVo();
			softCatelogVo.setCatelogid(catelogVo.getCatelogid());
			softCatelogVo.setCatelogName(catelogVo.getCatelogName());
			softCatelogVo.setCatelogDate(catelogVo.getCatelogDate());
			softCatelogVo.setUserName(catelogVo.getUserName());
			insertCatelog = softServiceImpl.insertCatelog(softCatelogVo);

		} else {
			return null;
		}
		if(insertCatelog)
		{
		catelogRepository.deleteById(catelogId);
		return "deleted :" + catelogId;
		}
		else {
			return null;
		}
	}

	@Override
	public String restore(int catelogId) {
		
		SoftCatelogVo softCatelogVo = softServiceImpl.selectById(catelogId);
		CatelogVo catelogVo=new CatelogVo();
		catelogVo.setCatelogid(softCatelogVo.getCatelogid());
		catelogVo.setCatelogName(softCatelogVo.getCatelogName());
		catelogVo.setCatelogDate(softCatelogVo.getCatelogDate());
		catelogVo.setUserName(softCatelogVo.getUserName());
		CatelogVo catelogVo2 = catelogRepository.save(catelogVo);
		if(catelogVo2!=null)
		{
		return "restored";
		
		}
		else
		{
			return "not restored";
		}
	}
	public String restoreAll()
	{
		List<SoftCatelogVo> list = softServiceImpl.selectAll();
		Iterator<SoftCatelogVo> iterator = list.iterator();
		while(iterator.hasNext())
		{
			SoftCatelogVo softCatelogVo = iterator.next();
			CatelogVo catelogVo=new CatelogVo();
			catelogVo.setCatelogid(softCatelogVo.getCatelogid());
			catelogVo.setCatelogName(softCatelogVo.getCatelogName());
			catelogVo.setCatelogDate(softCatelogVo.getCatelogDate());
			catelogVo.setUserName(softCatelogVo.getUserName());
			catelogRepository.save(catelogVo);
		}
		return "all Restored";
	}
	

}
