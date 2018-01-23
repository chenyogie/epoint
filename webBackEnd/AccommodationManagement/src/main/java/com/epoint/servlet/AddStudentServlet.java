package com.epoint.servlet;


import com.epoint.po.Student;
import com.epoint.service.StudentService;
import com.epoint.service.impl.StudentServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 新增一个学生对象
 * Author: Chenyogie
 * Date: 2018/1/11
 * Time: 14:41
 */
public class AddStudentServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		StudentService stuService = new StudentServiceImpl();

		/**
		 * 先生成一个符合要求的stuId
		 */
		String stuId="XH";

		Date date= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String format = sdf.format(date);
		stuId+=format;
		/**
		 * 获取最后一条记录的stuId值
		 */
		String lastStuId = stuService.findLastRecordId();
		//如果查询结果为空，则表的记录为空
		if (lastStuId==null || "".equals(lastStuId)){
			stuId+="0001";
		}else{
			//否则就截取查询结果记录的最后四位数组
			String str = lastStuId.substring(8);
			int i = Integer.parseInt(str)+1;
			stuId+=insertZeroToLeftId(i);//3 --> 0003
		}
		/*------------------------------生成了标准的stuId------------------------------------*/

		String json = req.getParameter("data");
		//System.out.println(json);
		//[{"stuId":"","name":"123","sex":"0","age":18,"phone":"123","roomInfo":"123","detail":"123"}]四川省成都市

		//截取到籍贯字符串
		String stuNative = json.substring(json.lastIndexOf(']')+1);
		//System.out.println("籍贯："+stuNative);
		//截取json字符串
		String stuJson = json.substring(json.indexOf('{'), json.lastIndexOf(']'));
		//System.out.println("json字符串："+stuJson);
		Student student = (Student) JSONObject.toBean(JSONObject.fromBean(stuJson), Student.class);
		//System.out.println("json对象："+student.toString());
		student.setStuId(stuId);
		student.setStuNative(stuNative);
		System.out.println("bean对象："+student.toString());

		//姓名不能重复
		Student s = stuService.findStudentByName(student.getName());
		if(s==null){
			//没有重复的姓名，直接插入数据
			stuService.insertStudent(student);
		}else{
			//姓名重复,名称加上学号末四位后缀以作区分。例如：张三_0001
			String stuName = student.getName()+"_"+stuId.substring(8);
			student.setName(stuName);
			System.out.println("stuName"+stuName);
			stuService.insertStudent(student);
		}

		resp.sendRedirect("index.html");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req,resp);
	}

	/**
	 *
	 * @param i 查询出来的stuId锁截取的数字
	 * @return
	 */
	public String insertZeroToLeftId(int i){
		char[] chs = new char[4];
		for (int j = 0; j <chs.length; j++) {
			chs[j]='0';
		}
		String s = String.valueOf(i);
		for (int j = 3; j >= (chs.length - s.length()); j--) {
			chs[j] = s.charAt(j-(chs.length-s.length()));
		}
		return String.valueOf(chs);
	}
}
