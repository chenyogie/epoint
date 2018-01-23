package com.epoint.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epoint.domain.Doctor;
import com.epoint.service.DoctorService;

@WebServlet("/deletedoctoraction")
public class DeleteDoctorAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteDoctorAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DoctorService service = new DoctorService();
		String ids = request.getParameter("ids");
		String message = service.deleteDoctorByIds(ids);
		response.getWriter().write(message);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
