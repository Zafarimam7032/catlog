package com.grwts.service.iterfc;

import java.util.List;
import com.grwts.main.CatelogVo;
public interface CatelogService {
	public List<CatelogVo> allCatelogList();
	public CatelogVo CatelogById(int catelogid);
	public CatelogVo insertCatelog(CatelogVo catelogVo); 
	public CatelogVo updateCatelog(int catelogId,String catelogName);
	public String deleteAllCatelog();
	public String deleteCatelogById(int catelogId);
	public String restore(int catelogId);
	public String restoreAll();
	
	
}
