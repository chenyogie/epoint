package com.epoint.service.impl;

import com.epoint.dao.StudentDao;
import com.epoint.dao.impl.StudentDaoImpl;
import com.epoint.po.Student;
import com.epoint.service.StudentService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Chenyogie
 * Date: 2018/1/9
 * Time: 14:20
 */
public class StudentServiceImpl implements StudentService{

	private StudentDao stuDao = new StudentDaoImpl();

	/**
	 *
	 * @return
	 */
	public int findStudentsCount() {

		return stuDao.findStudentsCount();
	}

	public List<Student> findAllStudent(String pageIndex, String pageSize, String key) {
		return stuDao.findAllStudnt(pageIndex,pageSize,key);
	}

	/**
	 *
	 * @return
	 */
	public String findLastRecordId() {

		return stuDao.findLastRecordId();
	}

	/**
	 *
	 * @param name
	 * @return
	 */
	public Student findStudentByName(String name) {

		return stuDao.findStudentByName(name);
	}

	/**
	 *
	 * @param student
	 */
	public void insertStudent(Student student) {
		stuDao.insertStudent(student);
	}
}
