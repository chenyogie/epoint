package com.epoint.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.asm.Label;
import com.epoint.domain.Music;
import com.epoint.service.MusicService;

@WebServlet("/addmusicaction")
public class AddMusicAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddMusicAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = request.getParameter("data");		
		Music music = JSONObject.parseObject(json,Music.class);
		//System.out.println(music.toString());
		MusicService service = new MusicService();
		int lstorder = service.findLastRecord();
		System.out.println("lstorder="+lstorder);
		if(lstorder!=0){
			music.setOrder(lstorder+1);
		}else{
			music.setOrder(1);
		}
		String message = service.insertMusic(music);
		response.getWriter().write(message);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
