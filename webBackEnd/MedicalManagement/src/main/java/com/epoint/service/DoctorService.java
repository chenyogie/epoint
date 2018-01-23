package com.epoint.service;

import java.util.List;

import com.epoint.dao.DoctorDao;
import com.epoint.domain.Doctor;

public class DoctorService {

	private DoctorDao dao = new DoctorDao();
	
	public String findLastRecordId() {
		return dao.findLastRecordId();
	}

	public List<Doctor> queryAll(int pageIndex, int pageSize, String ktype, String kname) {
		return dao.queryAll(pageIndex,pageSize,ktype,kname);
	}

	public int queryCount(String ktype, String kname) {
		return dao.queryCount(ktype,kname);
	}

	public String addDoctor(Doctor doctor) {
		int i = dao.addDoctor(doctor);
		return i>0?"添加成功":"添加失败";
	}

	public String deleteDoctorByIds(String ids) {
		return dao.deleteDoctorByIds(ids)?"删除成功":"删除失败";
	}

	public Doctor findDoctorById(String id) {
		return dao.findDoctorById(id);
	}

	public String updateDoctorById(Doctor doctor) {
		int i = dao.updateDoctorById(doctor);
		return i>0?"修改成功":"修改失败";
	}

}
