package com.epoint.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.epoint.domain.Pet;
import com.epoint.service.PetService;

@WebServlet("/updatepetaction")
public class UpdatePetAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdatePetAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		Pet pet = JSONObject.parseObject(data,Pet.class);
		PetService service = new PetService();
		String messqge = service.updatePetById(pet);
		response.getWriter().write(messqge);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
