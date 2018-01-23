package com.epoint.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epoint.service.PetService;

@WebServlet("/deletepetaction")
public class DeletePetAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeletePetAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PetService service = new PetService();
		String ids = request.getParameter("ids");
		String message = service.deletePetByIds(ids);
//		String[] ids = str.split(",");
//		for (String id : ids) {
//			service.deletePetById(id);
//		}
		response.getWriter().write(message);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
