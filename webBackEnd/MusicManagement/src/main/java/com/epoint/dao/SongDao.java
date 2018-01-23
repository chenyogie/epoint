package com.epoint.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.TrueFileFilter;

import com.epoint.domain.Song;
import com.epoint.utils.JDBCUtil;

public class SongDao {

	public List<Song> queryAll(int pageIndex, int pageSize, String ktype, String kname) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select * from songinfo where 1=1 ");
		if(ktype!=null && !("".equals(ktype))){
			sql.append(" and type= "+ktype);
		}
		if(kname!=null && kname != ""){
			sql.append(" and name like '%"+kname+"%'");
		}
		sql.append(" limit ?,?");
		List<Song> list = new ArrayList<>();
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, pageIndex*pageSize);
			stmt.setInt(2, pageSize);
			rs = stmt.executeQuery();
			while(rs.next()){
				Song d = new Song();
				d.setId(rs.getString("id"));
				d.setName(rs.getString("name"));
				d.setType(rs.getInt("type"));
				d.setLyrics(rs.getString("lyrics"));
				d.setCompose(rs.getString("compose"));
				d.setSinger(rs.getString("singer"));
				d.setDegree(rs.getInt("degree"));
				d.setLangues(rs.getString("langues"));
				d.setDuration(rs.getInt("duration"));
				d.setHotlevel(rs.getInt("hotlevel"));
				list.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(rs, stmt, conn);
		}
		return list;
	}

	public int queryCount(String ktype, String kname) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder("select count(*) from songinfo where 1=1 ");
		if(ktype!=null && !("".equals(ktype))){
			sql.append(" and type="+ktype);
		}
		if (kname != null && !("".equals(kname))) {
			sql.append(" and name like '%" + kname + "%'");
		}
		int count = 0;
		try {
			conn = new JDBCUtil().getConnection();
			stmt = (PreparedStatement) conn.prepareStatement(sql.toString());
			rs = stmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			new JDBCUtil().close(rs, stmt, conn);
		}
		return count;
	}

	public Song findSongByName(String name) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from songinfo where name=?";
		Song d = new Song();
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			while(rs.next()){
				d.setId(rs.getString("id"));
				d.setName(rs.getString("name"));
				d.setType(rs.getInt("type"));
				d.setLyrics(rs.getString("lyrics"));
				d.setCompose(rs.getString("compose"));
				d.setSinger(rs.getString("singer"));
				d.setDegree(rs.getInt("degree"));
				d.setLangues(rs.getString("langues"));
				d.setDuration(rs.getInt("duration"));
				d.setHotlevel(rs.getInt("hotlevel"));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(rs, stmt, conn);
		}
		return d;
	}

	public int addSong(Song song) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into songinfo(id,name,type,lyrics,compose,singer,degree,langues,duration,hotlevel)values(?,?,?,?,?,?,?,?,?,?)";
		int i = 0;
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, song.getId());
			stmt.setString(2, song.getName());
			stmt.setInt(3, song.getType());
			stmt.setString(4, song.getLyrics());
			stmt.setString(5, song.getCompose());
			stmt.setString(6, song.getSinger());
			stmt.setInt(7, song.getDegree());
			stmt.setString(8, song.getLangues());
			stmt.setInt(9, song.getDuration());
			stmt.setInt(10, song.getHotlevel());
			i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(null, stmt, conn);
		}
		return i;
	}

	public String findLastRecordId() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="select * from songinfo order by id desc limit 1";
		String id = null;
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()){
				id=rs.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(rs, stmt, conn);
		}
		return id;
	}

	public Song findSongById(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from songinfo where id=?";
		Song d = new Song();
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			while(rs.next()){
				d.setId(rs.getString("id"));
				d.setName(rs.getString("name"));
				d.setType(rs.getInt("type"));
				d.setLyrics(rs.getString("lyrics"));
				d.setCompose(rs.getString("compose"));
				d.setSinger(rs.getString("singer"));
				d.setDegree(rs.getInt("degree"));
				d.setLangues(rs.getString("langues"));
				d.setDuration(rs.getInt("duration"));
				d.setHotlevel(rs.getInt("hotlevel"));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(rs, stmt, conn);
		}
		return d;
	}

	public int updateSongById(Song song) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "update songinfo set type=?,degree=? where id=?";
		int i = 0;
		try {
			conn = new JDBCUtil().getConnection();
			//System.out.println(song.toString());
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, song.getType());
			stmt.setInt(2, song.getDegree());
			stmt.setString(3, song.getId());
			i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(null, stmt, conn);
		}
		return i;
	}

	public boolean deleteSongByIds(String ids) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "delete from songinfo where id in('"+ids+"')";
		boolean flag = false;
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql);
			flag = stmt.executeUpdate()>0?(true):(false);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(null, stmt, conn);
		}
		return flag;
	}

	public List<Song> queryAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from songinfo";
		List<Song> list = new ArrayList<>();
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				Song s = new Song();
				s.setId(rs.getString("id"));
				s.setName(rs.getString("name"));
				s.setType(rs.getInt("type"));
				s.setLyrics(rs.getString("lyrics"));
				s.setCompose(rs.getString("compose"));
				s.setSinger(rs.getString("singer"));
				s.setDegree(rs.getInt("degree"));
				s.setLangues(rs.getString("langues"));
				s.setDuration(rs.getInt("duration"));
				s.setHotlevel(rs.getInt("hotlevel"));
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(rs, stmt, conn);
		}
		return list;
	}

}
