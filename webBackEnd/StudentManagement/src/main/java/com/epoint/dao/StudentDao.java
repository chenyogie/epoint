package com.epoint.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.epoint.domain.Student;
import com.epoint.utils.JDBCUtil;
import com.sun.tools.javac.resources.javac;

public class StudentDao {
	
	/**
	 * 分页查询出所有用户
	 * @param @param pageIndex
	 * @param @param pageSize
	 * @param @param key
	 * @param @return
	 * @return List<Student>
	 * @throws
	 */
	public List<Student> queryAll(int pageIndex, int pageSize, String key) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		//SELECT * FROM studentinfo WHERE 1=1 AND stuName LIKE "%2%" LIMIT 0,20;
		sql.append("select * from studentinfo where 1=1 ");
		if(key!=null && key != ""){
			sql.append(" and stuName like '%"+key+"%'");
		}
		sql.append(" limit ?,?");
		List<Student> list = new ArrayList<>();
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, pageIndex*pageSize);
			stmt.setInt(2, pageSize);
			rs = stmt.executeQuery();
			while(rs.next()){
				Student s = new Student();
				s.setStuId(rs.getString("stuId"));
				s.setStuName(rs.getString("stuName"));
				s.setProfession(rs.getInt("profession"));
				s.setAddDate(rs.getString("addDate"));
				s.setSex(rs.getInt("sex"));
				s.setIdNum(rs.getString("idNum"));
				s.setTotalScore(rs.getInt("totalScore"));
				s.setNote(rs.getString("note"));				
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(rs, stmt, conn);
		}
		return list;
	}
	
	/**
	 * 查询数据的总数
	 * @param @param key
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int queryCount(String key) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from studentinfo where 1=1 ";
		if(key!=null && !("".equals(key))){
			sql+=" and stuName like '%"+key+"%'";
		}
		int count = 0;
		try {
			conn = new JDBCUtil().getConnection();
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				count = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(rs, stmt, conn);
		}
		return count;
	}
	/**
	 * 插入一个学生对象
	 * @param @param student
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int addStudent(Student student) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into studentinfo(stuId,stuName,profession,addDate,sex,idNum,totalScore,note)values(?,?,?,?,?,?,?,?)";
		int i = 0;
		try {
			conn = new JDBCUtil().getConnection();
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1, UUID.randomUUID().toString());
			stmt.setString(2, student.getStuName());
			stmt.setInt(3, student.getProfession());
			try {
				stmt.setDate(4,new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(student.getAddDate()).getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			stmt.setInt(5,student.getSex());
			stmt.setString(6, student.getIdNum());
			stmt.setInt(7, student.getTotalScore());
			stmt.setString(8, student.getNote());
			i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(null, stmt, conn);
		}
		return i;
	}

	/**
	 * 删除学生对象，同时删除此对象对应的成绩信息
	 * @param @param stuId
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int deleteStudent(String stuId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql1 = "delete from studentinfo where stuId=?";
		String sql2 = "delete from stumarkinfo where stuId=?";
		int i = 0;
		try {
			conn = new JDBCUtil().getConnection();
			//开启事务
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, stuId);
			i = stmt.executeUpdate();
			
			stmt = conn.prepareStatement(sql2);
			stmt.setString(1, stuId);
			stmt.executeUpdate();
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
		return i;
	}

	/**
	 * 根据id查询出学生对象
	 * @param @param stuId
	 * @param @return
	 * @return Student
	 * @throws
	 */
	public Student findStudentById(String stuId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Student s = new Student();
		String sql = "select * from studentinfo where stuId=?";
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, stuId);
			rs = stmt.executeQuery();
			while(rs.next()){
				s.setStuId(rs.getString("stuId"));
				s.setStuName(rs.getString("stuName"));
				s.setProfession(rs.getInt("profession"));
				s.setAddDate(rs.getString("addDate"));
				s.setSex(rs.getInt("sex"));
				s.setIdNum(rs.getString("idNum"));
				s.setTotalScore(rs.getInt("totalScore"));
				s.setNote(rs.getString("note"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(rs, stmt, conn);
		}
		return s;
	}

	/**
	 * 更新一个学生对象
	 * @param @param student
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int updateStudent(Student student) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "update studentinfo set stuName=?,profession=?,addDate=?,sex=?,idNum=?,totalScore=?,note=?  where stuId=?";
		int i = 0;
		try {
			conn = new JDBCUtil().getConnection();
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1, student.getStuName());
			stmt.setInt(2, student.getProfession());
			try {
				stmt.setDate(3,new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(student.getAddDate()).getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			stmt.setInt(4,student.getSex());
			stmt.setString(5, student.getIdNum());
			stmt.setInt(6, student.getTotalScore());
			stmt.setString(7, student.getNote());
			stmt.setString(8, student.getStuId());
			i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(null, stmt, conn);
		}
		return i;
	}

	/**
	 * 查询所有的的学生对象
	 * @param @return
	 * @return List<Student>
	 * @throws
	 */
	public List<Student> queryAll() {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from studentinfo";
		List<Student> list = new ArrayList<>();
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				Student s = new Student();
				s.setStuId(rs.getString("stuId"));
				s.setStuName(rs.getString("stuName"));
				s.setProfession(rs.getInt("profession"));
				s.setAddDate(rs.getString("addDate"));
				s.setSex(rs.getInt("sex"));
				s.setIdNum(rs.getString("idNum"));
				s.setTotalScore(rs.getInt("totalScore"));
				s.setNote(rs.getString("note"));				
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
