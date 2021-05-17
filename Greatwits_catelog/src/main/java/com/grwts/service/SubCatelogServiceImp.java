package com.grwts.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grwts.main.CatelogRepository;
import com.grwts.main.CatelogVo;
import com.grwts.main.SoftCatelogVo;
import com.grwts.main.SoftSubCatelogVo;
import com.grwts.main.SubCatelogVo;
import com.grwts.main.SubRepository;
import com.grwts.service.iterfc.SubCatelogService;

@Component
public class SubCatelogServiceImp implements SubCatelogService {
	@Autowired
	private SubRepository subRepository;
	@Autowired
	private CatelogRepository catelogRepository;
	@Autowired
	private SoftSubServiceimpl softSubServiceimpl;
//	@Autowired
//	private SoftServiceImpl softServiceImpl;

	@Override
	public List<SubCatelogVo> allSubCatelogList(int catelogId) {
		List<SubCatelogVo> subList = new ArrayList<>();
		List<SubCatelogVo> listSubCatelogVos = subRepository.findAll();
		Iterator<SubCatelogVo> iterator = listSubCatelogVos.iterator();
		while (iterator.hasNext()) {
			SubCatelogVo subCatelogVo = iterator.next();
			if (subCatelogVo.getCatelogVo().getCatelogid() == catelogId) {
				subList.add(subCatelogVo);
			}
		}
		if (subList.size() > 0) {
			return subList;
		} else {
			return null;
		}
	}

	@Override
	public SubCatelogVo subCatelogById(int subCatelogid) {
		SubCatelogVo subCatelogVo = subRepository.findById(subCatelogid).orElse(null);
		return subCatelogVo;
	}

	@Override
	public SubCatelogVo insertSubCatelog(SubCatelogVo subCatelogVo, int catelogId) {
		CatelogVo catelogVo = catelogRepository.findById(catelogId).orElse(null);
		int flag = 0;
		if (catelogVo != null) {
			List<SubCatelogVo> listSubCatelogVos = subRepository.findAll();
			Iterator<SubCatelogVo> iterator = listSubCatelogVos.iterator();
			if (listSubCatelogVos.size() > 0) {
				while (iterator.hasNext()) {
					SubCatelogVo subCatelogVo2 = iterator.next();
					if (subCatelogVo2.getCatelogVo().getCatelogid() == catelogId) {
						if(subCatelogVo2.getSubCatelogId()==subCatelogVo.getSubCatelogId())
						{
							flag++;
						}
						if (subCatelogVo2.getSubCatelogName().equalsIgnoreCase(subCatelogVo.getSubCatelogName())) {
							flag++;
						}
					}
				}
				List<SoftSubCatelogVo> listSoftSubCatelogVos = softSubServiceimpl.selectAll();
				Iterator<SoftSubCatelogVo> iterator2 = listSoftSubCatelogVos.iterator();
				while (iterator2.hasNext()) {
					SoftSubCatelogVo softSubCatelogVo = iterator2.next();
					if (softSubCatelogVo.getSubCatelogId() == subCatelogVo.getSubCatelogId()) {
						flag++;
					}
					if (softSubCatelogVo.getSubCatelogName().equalsIgnoreCase(subCatelogVo.getSubCatelogName())) {
						flag++;
					}
				}

			} else if (listSubCatelogVos.size() == 0) {
				flag++;
				subCatelogVo.setCatelogVo(catelogVo);
				SubCatelogVo saveSubCatelogVo = subRepository.save(subCatelogVo);
				return saveSubCatelogVo;
			} else {
				return null;
			}
		} else {
			return null;
		}
		if (flag == 0) {
			flag++;
			subCatelogVo.setCatelogVo(catelogVo);
			SubCatelogVo saveSubCatelogVo = subRepository.save(subCatelogVo);
			return saveSubCatelogVo;

		} else {
			return null;
		}
	}

	@Override
	public SubCatelogVo updateSubCatelog(int subcatelogId, String subCatelogName) {
		SubCatelogVo subCatelogVo = subRepository.findById(subcatelogId).orElse(null);
		subCatelogVo.setSubCatelogName(subCatelogName);
		SubCatelogVo saveSubCatelogVo = subRepository.save(subCatelogVo);
		return saveSubCatelogVo;
	}

	@Override
	public String deleteAllSubCatelog(int catelogId) {
		boolean insertSoftSubCatelog = false;
		CatelogVo catelogVo = catelogRepository.findById(catelogId).orElse(null);
		List<SubCatelogVo> allSubCatelogList = this.allSubCatelogList(catelogId);
		Iterator<SubCatelogVo> iterator = allSubCatelogList.iterator();
		while (iterator.hasNext()) {
			SubCatelogVo subCatelogVo = iterator.next();
			// subCatelogVo.setCatelogVo(catelogVo);
			SoftSubCatelogVo softSubCatelogVo = new SoftSubCatelogVo();
			softSubCatelogVo.setSubCatelogId(subCatelogVo.getSubCatelogId());
			softSubCatelogVo.setSubCatelogName(subCatelogVo.getSubCatelogName());
			softSubCatelogVo.setSubCatelogDate(subCatelogVo.getSubCatelogDate());
			softSubCatelogVo.setUserName(subCatelogVo.getUserName());
			SoftCatelogVo catelogVo2 = new SoftCatelogVo();
			catelogVo2.setCatelogid(catelogVo.getCatelogid());
			catelogVo2.setCatelogName(catelogVo.getCatelogName());
			catelogVo2.setUserName(catelogVo.getUserName());
			catelogVo2.setCatelogDate(catelogVo.getCatelogDate());
			softSubCatelogVo.setSoftcatelogVo(catelogVo2);
			insertSoftSubCatelog = softSubServiceimpl.insertSoftSubCatelog(softSubCatelogVo);
		}
		if (insertSoftSubCatelog) {
			while (iterator.hasNext()) {
				SubCatelogVo subCatelogVo = iterator.next();
				if (subCatelogVo.getCatelogVo().getCatelogid() == catelogId) {
					subRepository.deleteById(subCatelogVo.getSubCatelogId());
				}
			}

			return "all deleted";
		} else {
			return null;
		}
	}

	@Override
	public String deleteSubCatelogById(int catelogId, int subcatelogId) {
		boolean insertSoftSubCatelog = false;
		CatelogVo catelogVo = catelogRepository.findById(catelogId).orElse(null);
		SubCatelogVo subCatelogVo = this.subCatelogById(subcatelogId);
		if (subCatelogVo != null) {
			SoftSubCatelogVo softSubCatelogVo = new SoftSubCatelogVo();
			softSubCatelogVo.setSubCatelogId(subCatelogVo.getSubCatelogId());
			softSubCatelogVo.setSubCatelogName(subCatelogVo.getSubCatelogName());
			softSubCatelogVo.setSubCatelogDate(subCatelogVo.getSubCatelogDate());
			softSubCatelogVo.setUserName(subCatelogVo.getUserName());
			SoftCatelogVo catelogVo2 = new SoftCatelogVo();
			catelogVo2.setCatelogid(catelogVo.getCatelogid());
			catelogVo2.setCatelogName(catelogVo.getCatelogName());
			catelogVo2.setUserName(catelogVo.getUserName());
			catelogVo2.setCatelogDate(catelogVo.getCatelogDate());
			softSubCatelogVo.setSoftcatelogVo(catelogVo2);
			insertSoftSubCatelog = softSubServiceimpl.insertSoftSubCatelog(softSubCatelogVo);
		} else {
			return null;
		}
		if (insertSoftSubCatelog) {
			subRepository.deleteById(subcatelogId);
			return "deleted :" + subcatelogId;
		} else {
			return null;
		}

	}

	@Override
	public String restore(int catelogId, int subcatelogId) {
		SoftSubCatelogVo softSubCatelogVo = softSubServiceimpl.selectById(subcatelogId);
		SubCatelogVo subCatelogVo = new SubCatelogVo();
		subCatelogVo.setSubCatelogId(softSubCatelogVo.getSubCatelogId());
		subCatelogVo.setSubCatelogName(softSubCatelogVo.getSubCatelogName());
		subCatelogVo.setSubCatelogDate(softSubCatelogVo.getSubCatelogDate());
		subCatelogVo.setUserName(softSubCatelogVo.getUserName());
		CatelogVo catelogVo = new CatelogVo();
		if (softSubCatelogVo.getSoftcatelogVo().getCatelogid() == catelogId) {
			catelogVo.setCatelogid(softSubCatelogVo.getSoftcatelogVo().getCatelogid());
			catelogVo.setCatelogName(softSubCatelogVo.getSoftcatelogVo().getCatelogName());
			catelogVo.setCatelogDate(softSubCatelogVo.getSoftcatelogVo().getCatelogDate());
			catelogVo.setUserName(softSubCatelogVo.getSoftcatelogVo().getUserName());
			subCatelogVo.setCatelogVo(catelogVo);

		} else {
			return null;
		}
		SubCatelogVo subCatelogVo2 = subRepository.save(subCatelogVo);
		if (subCatelogVo2 != null) {
			return "restored";
		} else {
			return null;
		}

	}

	@Override
	public String restoreAll(int catelogId) {
		SubCatelogVo subCatelogVo2 = null;
		List<SoftSubCatelogVo> listSoftSubCatelogVos = softSubServiceimpl.selectAll();
		Iterator<SoftSubCatelogVo> iterator = listSoftSubCatelogVos.iterator();
		while (iterator.hasNext()) {
			SoftSubCatelogVo softSubCatelogVo = iterator.next();
			if (softSubCatelogVo.getSoftcatelogVo().getCatelogid() == catelogId) {
				SubCatelogVo subCatelogVo = new SubCatelogVo();
				subCatelogVo.setSubCatelogId(softSubCatelogVo.getSubCatelogId());
				subCatelogVo.setSubCatelogName(softSubCatelogVo.getSubCatelogName());
				subCatelogVo.setSubCatelogDate(softSubCatelogVo.getSubCatelogDate());
				subCatelogVo.setUserName(softSubCatelogVo.getUserName());
				CatelogVo catelogVo = new CatelogVo();
				catelogVo.setCatelogid(softSubCatelogVo.getSoftcatelogVo().getCatelogid());
				catelogVo.setCatelogName(softSubCatelogVo.getSoftcatelogVo().getCatelogName());
				catelogVo.setCatelogDate(softSubCatelogVo.getSoftcatelogVo().getCatelogDate());
				catelogVo.setUserName(softSubCatelogVo.getSoftcatelogVo().getUserName());
				subCatelogVo.setCatelogVo(catelogVo);
				subCatelogVo2 = subRepository.save(subCatelogVo);
			}
		}
		if (subCatelogVo2 != null) {
			return "restored";
		} else {
			return null;
		}
	}

}
