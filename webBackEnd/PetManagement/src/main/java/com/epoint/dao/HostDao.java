package com.epoint.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.epoint.domain.Host;
import com.epoint.utils.JDBCUtil;
import com.sun.tools.javac.resources.javac;

public class HostDao {

	public int queryCount(String kPhone, String kName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder("SELECT COUNT(h.vipno) count FROM petinfo p,hostinfo h WHERE p.id=h.petid ");
		if(kPhone!=null && !("".equals(kPhone))){
			sql.append(" AND h.telphone like '%" + kPhone +"%'");
		}
		if(kName!=null && !("".equals(kName))){
			sql.append(" AND h.name LIKE '%"+kName+"%'");
		}
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

	public List<Host> queryAll(int pageIndex, int pageSize, String kPhone, String kName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.nickname,h.* FROM petinfo p,hostinfo h WHERE p.id=h.petid ");
		if(kPhone!=null && !("".equals(kPhone))){
			sql.append(" AND h.telphone like '%" + kPhone +"%'");
		}
		if(kName!=null && !("".equals(kName))){
			sql.append(" AND h.name LIKE '%"+kName+"%'");
		}
		sql.append(" limit ?,?");
		List<Host> list = new ArrayList<>();
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, pageIndex*pageSize);
			stmt.setInt(2, pageSize);
			rs = stmt.executeQuery();
			while(rs.next()){
				Host h = new Host();
				h.setVipNo(rs.getString("vipno"));
				h.setNickname(rs.getString("nickname"));
				h.setPetId(rs.getString("petid"));
				h.setName(rs.getString("name"));
				h.setSex(rs.getInt("sex"));
				h.setBirth(rs.getDate("birth"));
				h.setIdentify(rs.getString("identify"));
				h.setAddress(rs.getString("address"));
				h.setTelPhone(rs.getString("telphone"));
				h.setEmail(rs.getString("email"));
				h.setNote(rs.getString("note"));
				list.add(h);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(rs, stmt, conn);
		}
		return list;
	}

	public int addHost(Host host) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into hostinfo(vipno,petid,name,sex,birth,identify,address,telphone,email,note)values(?,?,?,?,?,?,?,?,?,?)";
		int i = 0;
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, UUID.randomUUID().toString());
			stmt.setString(2, host.getPetId());
			stmt.setString(3, host.getName());
			stmt.setInt(4, host.getSex());
			stmt.setDate(5, new java.sql.Date(host.getBirth().getTime()));
			stmt.setString(6, host.getIdentify());
			stmt.setString(7, host.getAddress());
			stmt.setString(8, host.getTelPhone());
			stmt.setString(9, host.getEmail());
			stmt.setString(10, host.getNote());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(null, stmt, conn);
		}
		return i;
	}

	public int deleteHostByIds(String ids) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "delete from hostinfo where vipNo in('" + ids + "')";
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
