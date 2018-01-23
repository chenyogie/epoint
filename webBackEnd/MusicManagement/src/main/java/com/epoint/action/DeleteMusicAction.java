package com.epoint.action;

import java.awt.MouseInfo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epoint.service.MusicService;

@WebServlet("/deletemusicaction")
public class DeleteMusicAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteMusicAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids = request.getParameter("ids");
		System.out.println("ids="+ids);
		MusicService service = new MusicService();
		String message = service.deleteMusicByIds(ids);
		response.getWriter().write(message);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
