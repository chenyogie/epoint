package com.epoint.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.epoint.domain.Pet;
import com.epoint.utils.JDBCUtil;
import com.mysql.jdbc.util.ResultSetUtil;

public class PetDao {

	public List<Pet> queryAll(int pageIndex, int pageSize, String ktype, String kname) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select * from petinfo where 1=1 ");
		if(ktype!=null && !("".equals(ktype))){
			sql.append(" and type= "+ktype);
		}
		if(kname!=null && kname != ""){
			sql.append(" and nickname like '%"+kname+"%'");
		}
		sql.append(" limit ?,?");
		List<Pet> list = new ArrayList<>();
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, pageIndex*pageSize);
			stmt.setInt(2, pageSize);
			rs = stmt.executeQuery();
			while(rs.next()){
				Pet p = new Pet();
				p.setId(rs.getString("id"));
				p.setNickname(rs.getString("nickname"));
				p.setSex(rs.getInt("sex"));
				p.setAge(rs.getInt("age"));
				p.setType(rs.getInt("type"));
				p.setWeight(rs.getDouble("weight"));
				p.setIsvaccinated(rs.getInt("isvaccinated"));
				p.setPhoto(rs.getString("photo"));
				p.setRemark(rs.getString("remark"));
				list.add(p);
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
		StringBuilder sql = new StringBuilder("select count(*) from petinfo where 1=1 ");
		if(ktype!=null && !("".equals(ktype))){
			sql.append(" and type="+ktype);
		}
		if (kname != null && !("".equals(kname))) {
			sql.append(" and nickname like '%" + kname + "%'");
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

	public boolean deletePetByIds(String ids) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql1 = "delete from petinfo where id in('"+ids+"')";
		String sql2 = "delete from hostinfo where petid in('"+ids+"')";
		boolean flag = false;
		try {
			conn = new JDBCUtil().getConnection();
			//开启事务
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql1);
			flag = stmt.executeUpdate()>0?(true):(false);
			
			stmt = conn.prepareStatement(sql2);
			flag = flag && stmt.executeUpdate()>0?(true):(false);
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
			new JDBCUtil().close(null, stmt, conn);
		}
		return flag;
	}


	public String findLastRecordId() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="select * from petinfo order by id desc limit 1";
		String petId = null;
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()){
				petId=rs.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(rs, stmt, conn);
		}
		System.out.println("dao"+petId);
		return petId;
	}

	public int insertPet(Pet pet) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into petinfo(id,nickname,sex,age,type,weight,isvaccinated,photo,remark)values(?,?,?,?,?,?,?,?,?)";
		int i = 0;
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pet.getId());
			stmt.setString(2, pet.getNickname());
			stmt.setInt(3, pet.getSex());
			stmt.setInt(4, pet.getAge());
			stmt.setInt(5, pet.getType());
			stmt.setDouble(6, pet.getWeight());
			stmt.setInt(7, pet.getIsvaccinated());
			stmt.setString(8, pet.getPhoto());
			stmt.setString(9, pet.getRemark());
			i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(null, stmt, conn);
		}
		return i;
	}

	public Pet findPetById(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from petinfo where id=?";
		Pet p = new Pet();
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			while(rs.next()){
				p.setId(rs.getString("id"));
				p.setNickname(rs.getString("nickname"));
				p.setSex(rs.getInt("sex"));
				p.setAge(rs.getInt("age"));
				p.setType(rs.getInt("type"));
				p.setWeight(rs.getDouble("weight"));
				p.setIsvaccinated(rs.getInt("isvaccinated"));
				p.setPhoto(rs.getString("photo"));
				p.setRemark(rs.getString("remark"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(rs, stmt, conn);
		}
		return p;
	}

	public int updatePetById(Pet pet) {
		Connection conn = null;
		PreparedStatement stmt = null;
		//不再设置photo字段
		String sql = "update petinfo set nickname=?,sex=?,age=?,type=?,weight=?,isvaccinated=?,remark=? where id=?";
		int i = 0;
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pet.getNickname());
			stmt.setInt(2, pet.getSex());
			stmt.setInt(3, pet.getAge());
			stmt.setInt(4, pet.getType());
			stmt.setDouble(5, pet.getWeight());
			stmt.setInt(6, pet.getIsvaccinated());
			stmt.setString(7, pet.getRemark());
			stmt.setString(8, pet.getId());
			i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(null, stmt, conn);
		}
		return i;
	}

}
