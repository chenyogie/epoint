package com.epoint.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.epoint.domain.Student;
import com.epoint.service.StudentService;

/**
 * 查询学生信息
 * 模糊查询、分页查询
 * @author Chenyogie
 * @date 2018年1月18日 上午9:21:20
 */
@WebServlet("/liststudentaction")
public class ListStudentAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		String key = request.getParameter("key");
		
		StudentService stuService = new StudentService();
		List<Student> list = stuService.queryAll(pageIndex,pageSize,key);
		int count = stuService.queryCount(key);
		HashMap<String, Object> map = new HashMap<>();
		map.put("total", count);
		map.put("data", list);
		response.getWriter().write(JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd"));	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
