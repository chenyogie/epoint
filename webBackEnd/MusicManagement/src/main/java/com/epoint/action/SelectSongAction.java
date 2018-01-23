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

@WebServlet("/selectsongaction")
public class SelectSongAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectSongAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		SongService service = new SongService();
		Song s = service.findSongById(id);
		String json = JSONObject.toJSONStringWithDateFormat(s, "yyyy-MM-dd");
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
