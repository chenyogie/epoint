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

@WebServlet("/selectpetaction")
public class SelectPetAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectPetAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		PetService service = new PetService();
		Pet p = service.findPetById(id);
		String json = JSONObject.toJSONStringWithDateFormat(p, "yyyy-MM-dd");
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
