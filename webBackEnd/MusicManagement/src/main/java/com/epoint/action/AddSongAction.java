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
import com.epoint.utils.RandomId;

@WebServlet("/addsongaction")
public class AddSongAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddSongAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		Song song = JSONObject.parseObject(data,Song.class);
		SongService service = new SongService();
		Song s = service.findSongByName(song.getName());
		if(s!=null){
			if(s.getName()!=null && !"".equals(s.getName())){
				song.setName(song.getName()+"_"+song.getSinger());
			}
		}
		song.setId(new RandomId().getPetId());
		String message = service.addSong(song);
		response.getWriter().write(message);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
