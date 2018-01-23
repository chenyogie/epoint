package com.epoint.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.epoint.domain.Doctor;
import com.epoint.service.DoctorService;

@WebServlet("/updatedoctortaction")
public class UpdateDoctortAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateDoctortAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		Doctor doctor = JSONObject.parseObject(data,Doctor.class);
		DoctorService service = new DoctorService();
		String messqge = service.updateDoctorById(doctor);
		response.getWriter().write(messqge);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
