package com.epoint.service;

import java.util.Date;
import java.util.List;

import com.epoint.dao.StuMarkInfoDao;
import com.epoint.domain.StuMarkInfo;

public class StuMarkInfoService {

	private StuMarkInfoDao dao = new StuMarkInfoDao();
	
	public String addStuMarkInfo(StuMarkInfo sInfo) {
		int i = dao.addStuMarkInfo(sInfo);
		
		return i>0?"添加成功":"添加失败";
	}


	public int queryCount(Date kDate, String kName) {
		return dao.queryCount(kDate,kName);
	}


	public List<StuMarkInfo> queryAll(int pageIndex, int pageSize, Date kDate, String kName) {
		return dao.queryAll(pageIndex,pageSize,kDate,kName);
	}


	public String deleteStuMarkInfoById(String markId) {
		int i = dao.deleteStuMarkInfoById(markId);
		return i>0?"删除成功":"删除失败";
	}

}
