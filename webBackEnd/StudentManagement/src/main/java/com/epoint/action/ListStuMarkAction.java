package com.epoint.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.ls.LSInput;

import com.alibaba.fastjson.JSONObject;
import com.epoint.domain.StuMarkInfo;
import com.epoint.domain.Student;
import com.epoint.service.StuMarkInfoService;
import com.epoint.service.StudentService;
/**
 * 查询课程信息
 * 模糊查询、分页查询
 * @author Chenyogie
 * @date 2018年1月18日 上午9:24:16
 */
@WebServlet("/liststumarkaction")
public class ListStuMarkAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListStuMarkAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StuMarkInfoService service = new StuMarkInfoService();
		String method = request.getParameter("method");
		if("liststuname".equals(method)){
			findAllStuIdAndStuName(request,response);
		}else if("liststumark".equals(method)){
			quaryAll(request,response);
		}
	}

	private void quaryAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StuMarkInfoService service = new StuMarkInfoService();
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		String kName = request.getParameter("kName");
		String strDate = request.getParameter("kDate");
		Date kDate = null;
		if (strDate!=null && !("".equals(strDate))) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				kDate = sdf.parse(strDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		int count = service.queryCount(kDate,kName);
		List<StuMarkInfo> list = service.queryAll(pageIndex,pageSize,kDate,kName);
		HashMap<String, Object> map = new HashMap<>();
		map.put("total", count);
		map.put("data", list);
		response.getWriter().write(JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd"));
	}

	private void findAllStuIdAndStuName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		StudentService service = new StudentService();
		List<Student> list = service.queryAll();
		String json = JSONObject.toJSONString(list);
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
