package com.epoint.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

/**
 * 集中对网页内容进行gzip压缩
 * Created with IntelliJ IDEA.
 * Author: Chenyogie
 * Date: 2018/1/9
 * Time: 8:18
 */
public class GzipFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		//过滤请求

		MyHttpResponse myresponse = new MyHttpResponse((HttpServletResponse) response);

		//放行
		chain.doFilter(request,myresponse);

		//3）过滤响应
		//从缓存容器对象得到压缩前的内容
		//注意：response对象中没有方法获取实体内容，怎么办？
		char[] content = myresponse.getCharArray();

		//gzip压缩
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(buf);
		gzip.write(new String(content).getBytes());
		gzip.finish();
		byte[] result = buf.toByteArray();

		//告诉浏览器发送内容的压缩格式
		myresponse.setHeader("content-encoding", "gzip");

		//输出
		response.getOutputStream().write(result);
		//myRresponse.getWriter().write(new String(result,0,result.length));

	}

	public void destroy() {

	}
}

/**
 *
 */
class MyHttpResponse extends HttpServletResponseWrapper{

	private HttpServletResponse response;

	//定义一个缓存容器对象
	private CharArrayWriter charArray = new CharArrayWriter();

	/**
	 * 提供一个获取charArray内容的方法(包含网页内容)
	 */
	public char[] getCharArray(){
		return charArray.toCharArray();
	}

	public MyHttpResponse(HttpServletResponse response) {
		super(response);
		this.response = response;
	}

	/**
	 * 重写getWriter()方法，让其返回一个带缓存功能的PrintWriter
	 */
	@Override
	public PrintWriter getWriter() throws IOException {
		/**
		 * 现在已经创建了一个带CharArrayWriter缓存容器的PrintWriter了，
		 * 如果我们调用带缓存PrintWriter对象的write()方法，那么内容会直接写入到CharrArrayWriter缓存容器中。
		 */
		return new PrintWriter(charArray);
	}
}