package com.epoint.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.epoint.domain.Music;
import com.epoint.utils.JDBCUtil;

public class MusicDao {

	public int queryCount() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder("SELECT COUNT(m.musicid) count FROM songinfo s,musicinfo m WHERE s.name=m.musicid ");
		int count = 0;
		try {
			conn = new JDBCUtil().getConnection();
			stmt = (PreparedStatement) conn.prepareStatement(sql.toString());
			rs = stmt.executeQuery();
			while(rs.next()){
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(rs, stmt, conn);
		}
		return count;
	}

	public List<Music> queryAll(int pageIndex, int pageSize) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT m.*,s.name FROM songinfo s,musicinfo m WHERE s.name=m.musicid order by m.sorder asc");
		sql.append(" limit ?,?");
		List<Music> list = new ArrayList<>();
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, pageIndex*pageSize);
			stmt.setInt(2, pageSize);
			rs = stmt.executeQuery();
			while(rs.next()){
				Music m = new Music();
				m.setSelectnumber(rs.getString("selectnumber"));
				m.setMusicid(rs.getString("musicid"));
				m.setSinger(rs.getString("singer"));
				m.setComeindate(rs.getDate("comeindate"));
				m.setTotalcost(rs.getInt("totalcost"));
				m.setOrder(rs.getInt("sorder"));
				m.setIsswitch(rs.getInt("isswitch"));
				m.setRemark(rs.getString("remark"));
				m.setSongname(rs.getString("name"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(rs, stmt, conn);
		}
		return list;
	}

	public boolean insertMusic(Music music) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into musicinfo(selectnumber,musicid,singer,comeindate,totalcost,sorder,isswitch,remark)values(?,?,?,?,?,?,?,?)";
		boolean flag = false;
		try {
			conn = new JDBCUtil().getConnection();
			//开启事务
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, UUID.randomUUID().toString());
			stmt.setString(2, music.getMusicid());
			stmt.setString(3, music.getSinger());

			//获取当前时间作为点歌时间(带有时分秒)
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = sdf.format(date);
			Date parse = sdf.parse(format);
			
			stmt.setTimestamp(4, new java.sql.Timestamp(parse.getTime()));
			stmt.setInt(5, music.getTotalcost());
			if(music.getOrder()==0 || music.getOrder()==null){
				music.setOrder(0);
			}
			stmt.setInt(6, music.getOrder());
			stmt.setInt(7, music.getIsswitch());
			stmt.setString(8, music.getRemark());
			flag = stmt.executeUpdate()>0?(true):(false);

			String sql1 = "update songinfo set hotlevel=hotlevel+3 where name=?";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, music.getMusicid());
			flag = flag && stmt.executeUpdate()>0?(true):(false);
			//提交事务
		    conn.commit();
		} catch (Exception e) {//这里必须设置成Exception
			//如果事务失败，则将事务回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				//设置事务自动提交
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			new JDBCUtil().close(null, stmt, conn);
		}
		return flag;
	}

	public int findLastRecord() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="select * from musicinfo order by musicinfo.sorder desc limit 1";
		int order = 0;
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()){
				order=rs.getInt("sorder");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(rs, stmt, conn);
		}
		return order;
	}

	public int deleteMusicByIds(String ids) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "delete from musicinfo where selectnumber in('" + ids + "')";
		int i = 0;
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql);
			i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(null, stmt, conn);
		}
		return i;
	}

}
