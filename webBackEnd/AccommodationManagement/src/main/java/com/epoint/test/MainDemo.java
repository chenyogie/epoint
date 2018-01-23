package com.epoint.test;


import com.epoint.po.Student;
import com.epoint.service.StudentService;
import com.epoint.service.impl.StudentServiceImpl;

/**
 * Created with IntelliJ IDEA.
 * Author: Chenyogie
 * Date: 2018/1/3
 * Time: 12:24
 */
public class MainDemo {

	private static StudentService stuService = new StudentServiceImpl();

	public static void main(String[] args) {
		Student student = stuService.findStudentByName("123");
		Student student1 = stuService.findStudentByName("张三_0001");
		System.out.println("123===="+student);
		System.out.println("张三_0001===="+student1);
	}

}
