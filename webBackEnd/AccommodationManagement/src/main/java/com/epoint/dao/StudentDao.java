package com.epoint.dao;

import com.epoint.po.Student;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Chenyogie
 * Date: 2018/1/9
 * Time: 14:21
 */
public interface StudentDao {
	int findStudentsCount();

	List<Student> findAllStudnt(String pageIndex, String pageSize, String key);

	String findLastRecordId();

	Student findStudentByName(String name);

	void insertStudent(Student student);
}
