package com.epoint.service;

import java.util.List;

import com.epoint.dao.SongDao;
import com.epoint.domain.Song;

public class SongService {

	private SongDao dao = new SongDao();
	
	public List<Song> queryAll(int pageIndex, int pageSize, String ktype, String kname) {
		return dao.queryAll(pageIndex,pageSize,ktype,kname);
	}

	public int queryCount(String ktype, String kname) {
		return dao.queryCount(ktype,kname);
	}

	public Song findSongByName(String name) {
		Song s = dao.findSongByName(name);
		if(s==null){
			return null;
		}else{
			return s;
		}
	}

	public String addSong(Song song) {
		int i = dao.addSong(song);
		return i>0?"添加成功":"添加失败";
	}

	public String findLastRecordId() {
		return dao.findLastRecordId();
	}

	public Song findSongById(String id) {
		return dao.findSongById(id);
	}

	public String updateSongById(Song song) {
		int i = dao.updateSongById(song);
		return i>0?"修改成功":"修改失败";
	}

	public String deleteSongByIds(String ids) {
		return dao.deleteSongByIds(ids)?"删除成功":"删除失败";
	}

	public List<Song> queryAll() {
		return dao.queryAll();
	}

}
