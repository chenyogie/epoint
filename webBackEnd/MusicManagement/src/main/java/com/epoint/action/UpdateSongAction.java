package com.epoint.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.epoint.domain.Song;
import com.epoint.service.SongService;

@WebServlet("/updatesongaction")
public class UpdateSongAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateSongAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		Song song = JSONObject.parseObject(data,Song.class);
		SongService service = new SongService();
		String messqge = service.updateSongById(song);
		response.getWriter().write(messqge);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
