package com.epoint.dao.impl;

import com.epoint.dao.StudentDao;
import com.epoint.po.Student;
import com.epoint.utils.JdbcUtil;
import com.mysql.jdbc.JDBC4Connection;
import jdk.nashorn.internal.scripts.JD;
import org.apache.commons.io.input.CountingInputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Chenyogie
 * Date: 2018/1/9
 * Time: 14:21
 */
public class StudentDaoImpl implements StudentDao {

	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	/**
	 * 获取总记录数
	 * @return
	 */
	public int findStudentsCount() {
		int count = 0;
		String sql = "select count(*) from studentinfo";
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			rs.next();
			count = rs.getInt("count(*)");
			//System.out.println("总记录数："+count);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn,stmt,rs);
		}
		return count;
	}

	/**
	 * 分页查询
	 * @param pageIndex
	 * @param pageSize
	 * @param key
	 * @return
	 */
	public List<Student> findAllStudnt(String pageIndex, String pageSize, String key) {
		List<Student> list = new ArrayList<Student>();
		String sql = "select * from studentinfo";
		int pIndex = Integer.parseInt(pageIndex);
		int pSize = Integer.parseInt(pageSize);
		String pageSql = " LIMIT " + pIndex * pSize + "," + pSize;
		if(key!=null){
			sql = sql + " where name like '%"+key+"%'"+pageSql;
		}else{
			sql = sql + pageSql;
		}
		//System.out.println(sql);
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()){
				Student s = new Student();
				s.setStuId(rs.getString("stuId"));
				s.setName(rs.getString("name"));
				s.setSex(rs.getInt("sex"));
				s.setAge(rs.getInt("age"));
				s.setStuNative(rs.getString("native"));
				s.setPhone(rs.getString("phone"));
				s.setRoomInfo(rs.getString("roomInfo"));
				s.setDetail(rs.getString("detail"));
				list.add(s);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn,stmt,rs);
		}
		return null;
	}

	/**
	 *
	 * @return
	 */
	public String findLastRecordId() {
		String stuId = null;
		String sql ="select * from studentinfo order by stuId desc limit 1";
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()){
				stuId=rs.getString("stuId");
			}
			return stuId;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn,stmt,rs);
		}
		return null;
	}

	/**
	 *
	 * @return
	 */
	public Student findStudentByName(String name) {
		Student s = new Student();
		try {
			String sql = "select * from studentinfo where name=?";
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,name);
			rs = stmt.executeQuery();
			boolean flag = rs.next();
			if(flag==true){
				s.setStuId(rs.getString("stuId"));
				s.setName(rs.getString("name"));
				s.setSex(rs.getInt("sex"));
				s.setAge(rs.getInt("age"));
				s.setStuNative(rs.getString("native"));
				s.setPhone(rs.getString("phone"));
				s.setRoomInfo(rs.getString("roomInfo"));
				s.setDetail(rs.getString("detail"));
			}else{
				s = null;
			}
			return s;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn,stmt,rs);
		}
		return null;
	}

	/**
	 *
	 * @param student
	 */
	public void insertStudent(Student student) {
		String sql ="insert into studentinfo() values(?,?,?,?,?,?,?,?)";
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,student.getStuId());
			stmt.setString(2,student.getName());
			stmt.setInt(3,student.getSex());
			stmt.setInt(4,student.getAge());
			stmt.setString(5,student.getStuNative());
			stmt.setString(6,student.getPhone());
			stmt.setString(7,student.getRoomInfo());
			stmt.setString(8,student.getDetail());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn,stmt,rs);
		}
	}
}
