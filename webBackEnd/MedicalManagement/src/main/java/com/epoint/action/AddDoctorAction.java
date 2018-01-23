package com.epoint.action;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.epoint.domain.Doctor;
import com.epoint.service.DoctorService;
import com.epoint.utils.RandomId;

@WebServlet("/adddoctoraction")
public class AddDoctorAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddDoctorAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		Doctor doctor = JSONObject.parseObject(data,Doctor.class);
		DoctorService service = new DoctorService();
		doctor.setId(new RandomId().getPetId());
		String message = service.addDoctor(doctor);
		response.getWriter().write(message);
		//System.out.println(data);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
