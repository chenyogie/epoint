package com.epoint.service;

import java.util.List;

import com.epoint.dao.PetDao;
import com.epoint.domain.Pet;

public class PetService {

	private PetDao dao = new PetDao();
	
	public List<Pet> queryAll(int pageIndex, int pageSize, String ktype, String kname) {
		return dao.queryAll(pageIndex,pageSize,ktype,kname);
	}

	public int queryCount(String ktype, String kname) {
		return dao.queryCount(ktype,kname);
	}

	public String deletePetByIds(String ids) {
		return dao.deletePetByIds(ids)?"删除成功":"删除失败";
	}

	public String insertPet(Pet pet) {
		int i = dao.insertPet(pet);
		return i>0?"添加成功":"添加失败";
	}

	public String findLastRecordId() {
		return dao.findLastRecordId();
	}

	public Pet findPetById(String id) {
		return dao.findPetById(id);
	}

	public String updatePetById(Pet pet) {
		int i = dao.updatePetById(pet);
		return i>0?"修改成功":"修改失败";
	}

}
