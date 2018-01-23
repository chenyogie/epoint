package com.epoint.service;

import java.util.List;

import com.epoint.dao.StudentDao;
import com.epoint.domain.Student;
import com.sun.tools.doclets.formats.html.resources.standard;

public class StudentService {
	
	private StudentDao stuDao = new StudentDao();

	public List<Student> queryAll(int pageIndex, int pageSize, String key) {
		return stuDao.queryAll(pageIndex,pageSize,key);
	}

	public int queryCount(String key) {
		return stuDao.queryCount(key);
	}

	public String addStudent(Student student) {
		
		int i = stuDao.addStudent(student);
		
		return i>0?"添加成功":"添加失败";
	}

	public String deleteStudent(String stuId) {
		int i = stuDao.deleteStudent(stuId);
		
		return i>0?"删除成功":"删除失败";
	}

	public Student findStudentById(String stuId) {
		return stuDao.findStudentById(stuId);
	}

	public String updateStudent(Student student) {
		int i = stuDao.updateStudent(student);
		return i>0?"修改成功":"修改失败";
	}

	public List<Student> queryAll() {
		return stuDao.queryAll();
	}


}
