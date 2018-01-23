package com.epoint.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epoint.service.StudentService;
/**
 * 删除多个学生对象及其相关的成绩信息
 * @author Chenyogie
 * @date 2018年1月18日 上午9:20:02
 */
@WebServlet("/deletestudentsaction")
public class DeleteStudentsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DeleteStudentsAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("removeStudents".equals(method)){
			String ids = request.getParameter("ids");
			String[] strs = ids.split(",");
			for (String stuId : strs) {
				removeStudents(stuId);
			}
			response.getWriter().write("删除成功");
		}
	}

	private void removeStudents(String stuId) {
		StudentService service = new StudentService();
		service.deleteStudent(stuId);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
