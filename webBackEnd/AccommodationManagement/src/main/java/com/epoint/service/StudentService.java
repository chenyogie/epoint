package com.epoint.service;

import com.epoint.po.Student;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Chenyogie
 * Date: 2018/1/9
 * Time: 14:20
 */
public interface StudentService {
	int findStudentsCount();

	List<Student> findAllStudent(String pageIndex, String pageSize, String key);

	String findLastRecordId();

	Student findStudentByName(String name);

	void insertStudent(Student student);

}
