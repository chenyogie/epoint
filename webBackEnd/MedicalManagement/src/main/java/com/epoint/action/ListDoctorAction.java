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
import com.epoint.domain.Doctor;
import com.epoint.service.DoctorService;

@WebServlet("/listdoctoraction")
public class ListDoctorAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListDoctorAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		String ktype = request.getParameter("ktype");
		String kname = request.getParameter("kname");
		DoctorService service = new DoctorService();
		List<Doctor> list = service.queryAll(pageIndex,pageSize,ktype,kname);
		int count = service.queryCount(ktype,kname);
		HashMap<String, Object> map = new HashMap<>();
		map.put("total", count);
		map.put("data", list);
		response.getWriter().write(JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd"));	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
