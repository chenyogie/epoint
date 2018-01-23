package com.epoint.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.epoint.domain.Host;
import com.epoint.service.HostService;

@WebServlet("/addhostaction")
public class AddHostAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddHostAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		Host host = JSONObject.parseObject(data,Host.class);
		System.out.println(host);
		HostService service = new HostService();
		String message = service.addHost(host);
		response.getWriter().write(message);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
