package com.epoint.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.epoint.service.SongService;

public class RandomId {

	public String getPetId() {
		/**
		 * 先生成一个符合要求的stuId
		 */
		String newId="GQ";
		Date date= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String format = sdf.format(date);
		newId+=format;
		/**
		 * 获取最后一条记录的stuId值
		 */
		SongService service  = new SongService();
		String lastId = service.findLastRecordId();
		//System.out.println("lastid="+lastId);
		//如果查询结果为空，则表的记录为空
		if (lastId == null || "".equals(lastId)){
			newId+="0001";
		}else{
			//否则就截取查询结果记录的最后四位数组
			String str = lastId.substring(8);
			int i = Integer.parseInt(str)+1;
			newId+=insertZeroToLeftId(i);//3 --> 0003
		}
		return newId;
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
