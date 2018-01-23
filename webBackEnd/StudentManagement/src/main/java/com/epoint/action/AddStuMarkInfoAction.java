package com.epoint.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.epoint.domain.StuMarkInfo;
import com.epoint.service.StuMarkInfoService;
/**
 * 添加课程成绩信息的servlet
 * @author Chenyogie
 * @date 2018年1月18日 上午9:18:59
 */
@WebServlet("/addstumarkinfoaction")
public class AddStuMarkInfoAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddStuMarkInfoAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		StuMarkInfo sInfo = JSONObject.parseObject(data,StuMarkInfo.class);
		System.out.println(sInfo);
		StuMarkInfoService service = new StuMarkInfoService();
		String message = service.addStuMarkInfo(sInfo);
		response.getWriter().write(message);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
