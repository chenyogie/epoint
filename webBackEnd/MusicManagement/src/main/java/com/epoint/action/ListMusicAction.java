package com.epoint.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.epoint.domain.Music;
import com.epoint.service.MusicService;

@WebServlet("/listmusicaction")
public class ListMusicAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListMusicAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MusicService service = new MusicService();
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int count = service.queryCount();
		List<Music> list = service.queryAll(pageIndex,pageSize);
		HashMap<String, Object> map = new HashMap<>();
		map.put("total", count);
		map.put("data", list);
		response.getWriter().write(JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
