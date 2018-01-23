package com.epoint.servlet;

import com.epoint.po.Student;
import com.epoint.service.StudentService;
import com.epoint.service.impl.StudentServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Author: Chenyogie
 * Date: 2018/1/9
 * Time: 10:51
 */
public class VisitFirstPageServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setCharacterEncoding("utf-8");
		String pageIndex = req.getParameter("pageIndex");
		String pageSize = req.getParameter("pageSize");
		String key = req.getParameter("key");

		StudentService stuService = new StudentServiceImpl();
		int count = stuService.findStudentsCount();
		List<Student> list = stuService.findAllStudent(pageIndex,pageSize,key);
		Map map = new HashMap();
		map.put("data",list);
		map.put("total",count);

//		JSONArray jsonArray = JSONArray.fromObject(map);
//		System.out.println(jsonArray.toString());
//		resp.getWriter().write(jsonArray.toString().substring(1,jsonArray.toString().length()-1));

		JSONObject jsonObject = JSONObject.fromObject(map);
		resp.getWriter().write(jsonObject.toString());

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
