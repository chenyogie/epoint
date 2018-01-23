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
import com.epoint.utils.RandomId;

@WebServlet("/addpetaction")
public class AddPetAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddPetAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = request.getParameter("data");		
		Pet pet = JSONObject.parseObject(json,Pet.class);
		pet.setId(new RandomId().getPetId());
		pet.setPhoto(pet.getPhoto().substring(pet.getPhoto().indexOf('>')+1, pet.getPhoto().lastIndexOf('<')));
		PetService service = new PetService();
		String message = service.insertPet(pet);
		response.getWriter().write(message);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	

}
