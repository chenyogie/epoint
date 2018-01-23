package com.epoint.dao;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epoint.domain.Doctor;
import com.epoint.utils.JDBCUtil;

public class DoctorDao {

	public String findLastRecordId() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="select * from doctorinfo order by id desc limit 1";
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

	public List<Doctor> queryAll(int pageIndex, int pageSize, String ktype, String kname) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select * from doctorinfo where 1=1 ");
		if(ktype!=null && !("".equals(ktype))){
			sql.append(" and type= "+ktype);
		}
		if(kname!=null && kname != ""){
			sql.append(" and name like '%"+kname+"%'");
		}
		sql.append(" limit ?,?");
		List<Doctor> list = new ArrayList<>();
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, pageIndex*pageSize);
			stmt.setInt(2, pageSize);
			rs = stmt.executeQuery();
			while(rs.next()){
				Doctor d = new Doctor();
				d.setId(rs.getString("id"));
				d.setName(rs.getString("name"));
				d.setSex(rs.getInt("sex"));
				d.setBirth(rs.getDate("birth"));
				d.setDocNative(rs.getString("docnative"));
				d.setPolitics(rs.getInt("politics"));
				d.setDepartments(rs.getString("departments"));
				d.setPosition(rs.getString("position"));
				d.setTelephone(rs.getString("telephone"));
				d.setCount(rs.getInt("count"));
				d.setType(rs.getInt("type"));
				d.setOthers(rs.getString("others"));
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
		StringBuilder sql = new StringBuilder("select count(*) from doctorinfo where 1=1 ");
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

	public int addDoctor(Doctor doctor) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into doctorinfo(id,name,sex,birth,docnative,politics,departments,position,telephone,count,type,others)values(?,?,?,?,?,?,?,?,?,?,?,?)";
		int i = 0;
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, doctor.getId());
			stmt.setString(2, doctor.getName());
			stmt.setInt(3, doctor.getSex());
			stmt.setDate(4, new java.sql.Date(doctor.getBirth().getTime()));
			stmt.setString(5, doctor.getDocNative());
			stmt.setInt(6, doctor.getPolitics());
			stmt.setString(7, doctor.getDepartments());
			stmt.setString(8, doctor.getPosition());
			stmt.setString(9, doctor.getTelephone());
			stmt.setInt(10, doctor.getCount());
			stmt.setInt(11, doctor.getType());
			stmt.setString(12, doctor.getOthers());
			i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(null, stmt, conn);
		}
		return i;
	}

	public boolean deleteDoctorByIds(String ids) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql1 = "delete from doctorinfo where id in('"+ids+"')";
		String sql2 = "delete from patientinfo where doctorid in('"+ids+"')";
		String hasDoctorIdSql = "select * from patientinfo where doctorid in('"+ids+"')";
		boolean flag = false;
		try {
			conn = new JDBCUtil().getConnection();
			//开启事务
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql1);
			flag = stmt.executeUpdate()>0?(true):(false);
			
			/**
			 * 查询子表中是否有相关联的数据
			 */
			stmt = conn.prepareStatement(hasDoctorIdSql);
			rs = stmt.executeQuery();
			if(rs.next()){
				stmt = conn.prepareStatement(sql2);
				flag = flag && stmt.executeUpdate()>0?(true):(false);
			}
			//提交事务
			conn.commit();
		} catch (Exception e) {
			//事务回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			new JDBCUtil().close(rs, stmt, conn);
		}
		return flag;
	}

	public Doctor findDoctorById(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from doctorinfo where id=?";
		Doctor d = new Doctor();
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			while(rs.next()){
				d.setId(rs.getString("id"));
				d.setName(rs.getString("name"));
				d.setSex(rs.getInt("sex"));
				d.setBirth(rs.getDate("birth"));
				d.setDocNative(rs.getString("docnative"));
				d.setPolitics(rs.getInt("politics"));
				d.setDepartments(rs.getString("departments"));
				d.setPosition(rs.getString("position"));
				d.setTelephone(rs.getString("telephone"));
				d.setCount(rs.getInt("count"));
				d.setType(rs.getInt("type"));
				d.setOthers(rs.getString("others"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(rs, stmt, conn);
		}
		return d;
	}

	public int updateDoctorById(Doctor doctor) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "update doctorinfo set name=?,sex=?,birth=?,docnative=?,politics=?,departments=?,position=?,telephone=?,count=?,type=?,others=? where id=?";
		int i = 0;
		try {
			conn = new JDBCUtil().getConnection();
			System.out.println(doctor.toString());
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,doctor.getName());
			stmt.setInt(2, doctor.getSex());
			stmt.setDate(3, new java.sql.Date(doctor.getBirth().getTime()));
			stmt.setString(4, doctor.getDocNative());
			stmt.setInt(5, doctor.getPolitics());
			stmt.setString(6, doctor.getDepartments());
			stmt.setString(7, doctor.getPosition());
			stmt.setString(8, doctor.getTelephone());
			stmt.setInt(9, doctor.getCount());
			stmt.setInt(10, doctor.getType());
			stmt.setString(11, doctor.getOthers());
			stmt.setString(12, doctor.getId());
			i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(null, stmt, conn);
		}
		return i;
	}

}
