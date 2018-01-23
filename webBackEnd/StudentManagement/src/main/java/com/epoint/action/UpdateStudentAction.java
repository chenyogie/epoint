package com.epoint.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.epoint.domain.Student;
import com.epoint.service.StudentService;
/**
 * 更新学生对象
 * @author Chenyogie
 * @date 2018年1月18日 上午9:25:30
 */
@WebServlet("/updatestudentaction")
public class UpdateStudentAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UpdateStudentAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		Student student = JSONObject.parseObject(data,Student.class);
		StudentService service = new StudentService();
		String message = service.updateStudent(student);
		response.getWriter().write(message);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
