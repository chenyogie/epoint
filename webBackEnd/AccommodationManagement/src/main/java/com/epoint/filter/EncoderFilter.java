package com.epoint.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.IOException;
/**
 * Created with IntelliJ IDEA.
 * Author: Chenyogie
 * Date: 2018/1/8
 * Time: 16:42
 */
public class EncoderFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)req;
		//post提交参数
		request.setCharacterEncoding("utf-8");
		/**
		 *创建一个HttpServletRequest实现类的装饰者类,重写getParameter方法
		 */
		MyRequest myRequest = new MyRequest(request);
		//放行:myRequest中携带的参数已经进行了编码装换
		chain.doFilter(myRequest,resp);

	}

	public void destroy() {

	}
}

/**
 * 装饰者类
 */
class MyRequest extends HttpServletRequestWrapper {

	//声明一个被装饰者类型的成员变量
	private HttpServletRequest request;

	/**
	 * 接收被装饰者类对象
	 * @param request
	 */
	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	/**
	 * 重写getParameter方法
	 * @param name
	 * @return
	 */
	@Override
	public String getParameter(String name) {
		//得到原来的参数
		String value = request.getParameter(name);
		try {
			//对get方式提交参数处理,手动解码
			if ("GET".equals(request.getMethod())) {
				value = new String(value.getBytes("iso-8859-1"), "utf-8");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return value;
	}
}
