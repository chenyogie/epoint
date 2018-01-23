package com.epoint.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.epoint.domain.Song;
import com.epoint.service.SongService;

@WebServlet("/listsongnameaction")
public class ListSongNameAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListSongNameAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SongService service = new SongService();
		List<Song> list = service.queryAll();
		String json = JSONObject.toJSONString(list);
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
