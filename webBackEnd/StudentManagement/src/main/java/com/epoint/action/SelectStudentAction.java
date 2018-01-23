package com.epoint.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.epoint.domain.Student;
import com.epoint.service.StudentService;
/**
 * 根据stuId查询出单个学生对象
 * @author Chenyogie
 * @date 2018年1月18日 上午9:24:39
 */
@WebServlet("/selectstudentaction")
public class SelectStudentAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SelectStudentAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stuId = request.getParameter("stuId");
		//System.out.println("stuId===="+stuId);
		StudentService service = new StudentService();
		Student s = service.findStudentById(stuId);
		String json = JSONObject.toJSONStringWithDateFormat(s, "yyyy-MM-dd");
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
