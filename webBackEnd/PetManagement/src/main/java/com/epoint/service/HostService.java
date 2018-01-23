package com.epoint.service;

import java.util.List;

import com.epoint.dao.HostDao;
import com.epoint.domain.Host;

public class HostService {

	HostDao dao = new HostDao();
	
	public int queryCount(String kPhone, String kName) {
		return dao.queryCount(kPhone,kName);
	}

	public List<Host> queryAll(int pageIndex, int pageSize, String kPhone, String kName) {
		return dao.queryAll(pageIndex,pageSize,kPhone,kName);
	}

	public String addHost(Host host) {
		int i = dao.addHost(host);
		return i>0?"添加成功":"添加失败";
	}

	public String deleteHostByIds(String ids) {
		int i = dao.deleteHostByIds(ids);
		return i>0?"删除成功":"删除失败";
	}

}
