package com.epoint.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epoint.service.StudentService;
/**
 * 删除学生对象及其相关的成绩信息
 * @author Chenyogie
 * @date 2018年1月18日 上午9:19:19
 */
@WebServlet("/deletestudentaction")
public class DeleteStudentAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteStudentAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stuId = request.getParameter("id");
		StudentService service = new StudentService();
		String str = service.deleteStudent(stuId);
		response.getWriter().write(str);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
