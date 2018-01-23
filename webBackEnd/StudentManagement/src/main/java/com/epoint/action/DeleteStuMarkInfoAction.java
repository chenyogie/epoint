package com.epoint.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epoint.service.StuMarkInfoService;
import com.sun.tools.internal.ws.processor.model.Service;
/**
 * 删除成绩信息
 * @author Chenyogie
 * @date 2018年1月18日 上午9:20:57
 */
@WebServlet("/deletestumarkinfoaction")
public class DeleteStuMarkInfoAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteStuMarkInfoAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String markId = request.getParameter("id");
		StuMarkInfoService service = new StuMarkInfoService();
		String message = service.deleteStuMarkInfoById(markId);
		response.getWriter().write(message);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
