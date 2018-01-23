package com.epoint.service;

import java.util.List;

import com.epoint.dao.MusicDao;
import com.epoint.domain.Music;

public class MusicService {

	private MusicDao dao = new MusicDao();
	
	public int queryCount() {
		return dao.queryCount();
	}

	public List<Music> queryAll(int pageIndex, int pageSize) {
		return dao.queryAll(pageIndex,pageSize);
	}

	public String insertMusic(Music music) {
		boolean flag = dao.insertMusic(music);
		return flag?"添加成功":"添加失败";
	}

	public int findLastRecord() {
		return dao.findLastRecord();
	}

	public String deleteMusicByIds(String ids) {
		int i = dao.deleteMusicByIds(ids);
		return i>0?"删除成功":"删除失败";
	}

}
