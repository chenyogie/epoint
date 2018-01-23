package com.epoint.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.epoint.domain.StuMarkInfo;
import com.epoint.domain.Student;
import com.epoint.utils.JDBCUtil;
import com.sun.tools.javac.resources.javac;

public class StuMarkInfoDao {
	
	/**
	 * 添加一条成绩信息，同时更新学生的学分字段
	 * @param @param sInfo
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int addStuMarkInfo(StuMarkInfo sInfo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into stumarkinfo(markId,stuId,courseName,baseScore,testScore,finalScore,addDate,note)values(?,?,?,?,?,?,?,?)";
		int i = 0;
		try {
			conn = new JDBCUtil().getConnection();
			//开启事务
			conn.setAutoCommit(false);
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1, UUID.randomUUID().toString());
			stmt.setString(2, sInfo.getStuId());
			stmt.setString(3, sInfo.getCourseName());
			stmt.setDouble(4, sInfo.getBaseScore());
			stmt.setDouble(5, sInfo.getTestScore());
			double finalscore = sInfo.getBaseScore()*0.4 + sInfo.getTestScore()*0.6;
			stmt.setDouble(6, finalscore);
			stmt.setDate(7, new java.sql.Date(sInfo.getAddDate().getTime()));
			stmt.setString(8, sInfo.getNote());
			i = stmt.executeUpdate();
			
			int totalscore = (int) Math.floor(finalscore/10.0);
			String sql1 = "update studentinfo set totalScore=totalScore+? where stuId=?";
			stmt = conn.prepareStatement(sql1);
			stmt.setInt(1, totalscore);
			stmt.setString(2, sInfo.getStuId());
			stmt.executeUpdate();
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
		return i;
	}
	/**
	 * 查询记录数
	 * 包含查询所有和模糊查询
	 * @param @param kDate
	 * @param @param kName
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int queryCount(Date kDate, String kName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//SELECT s.stuName,m.* FROM studentinfo s,stumarkinfo m WHERE s.stuId=m.stuId 
		//AND s.stuName LIKE '%1%' AND m.addDate LIKE '%2018-01-09%' LIMIT 0,10;

		String sql = "SELECT COUNT(m.stuId) count FROM studentinfo s,stumarkinfo m WHERE s.stuId=m.stuId ";
		if(kDate!=null){
			sql+=" AND m.addDate like '%" + kDate +"%'";
		}
		if(kName!=null && !("".equals(kName))){
			sql+=" AND s.stuName LIKE '%"+kName+"%'";
		}
		int count = 0;
		try {
			conn = new JDBCUtil().getConnection();
			stmt = (PreparedStatement) conn.prepareStatement(sql);
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

	/**
	 * 查询所有的成绩对象
	 * 包含分页查询、模糊查询
	 * @param @param pageIndex
	 * @param @param pageSize
	 * @param @param kDate
	 * @param @param kName
	 * @param @return
	 * @return List<StuMarkInfo>
	 * @throws
	 */
	public List<StuMarkInfo> queryAll(int pageIndex, int pageSize, Date kDate, String kName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		//SELECT s.stuName,m.* FROM studentinfo s,stumarkinfo m WHERE s.stuId=m.stuId 
		//AND s.stuName LIKE '%2%' AND m.addDate like '%2018-01-09%' LIMIT 0,10;
		sql.append("SELECT s.stuName,m.* FROM studentinfo s,stumarkinfo m WHERE s.stuId=m.stuId ");
		if(kName!=null && !("".equals(kName))){
			sql.append(" AND s.stuName LIKE '%"+kName+"%' ");
		}
		if(kDate!=null){
			java.sql.Date date = new java.sql.Date(kDate.getTime());
			sql.append(" AND m.addDate like '%"+date+"%' ");
		}
		sql.append(" limit ?,?");
		List<StuMarkInfo> list = new ArrayList<>();
		try {
			conn = new JDBCUtil().getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, pageIndex*pageSize);
			stmt.setInt(2, pageSize);
			rs = stmt.executeQuery();
			while(rs.next()){
				StuMarkInfo s = new StuMarkInfo();
				s.setMarkId(rs.getString("markId"));
				s.setStuId(rs.getString("stuId"));
				s.setStuName(rs.getString("stuName"));
				s.setCourseName(rs.getString("courseName"));
				s.setBaseScore(rs.getDouble("baseScore"));
				s.setTestScore(rs.getDouble("testScore"));
				s.setFinalScore(rs.getDouble("finalScore"));
				s.setAddDate(rs.getDate("addDate"));
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
	 * 根据id查询成绩信息
	 * @param @param markId
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int deleteStuMarkInfoById(String markId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "delete from stumarkinfo where markId=?";
		int i = 0;
		try {
			conn = new JDBCUtil().getConnection();
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1, markId);
			i = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			new JDBCUtil().close(null, stmt, conn);
		}
		return i;
	}
}
