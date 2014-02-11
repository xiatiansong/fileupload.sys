package com.sunshine.fusys.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登录请求拦截器，所有请求都将经过这里
 * 创建人：fantasy <br>
 * 创建时间：2013-9-5 <br>
 */
public class SessionFilter implements Filter {

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest re, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) re;
		HttpSession session = request.getSession();
		if( session == null || session.getAttribute(Constants.USER_SESSION ) == null){
			//不对这些请求进行过滤
			String [] file = new String[]{".js",".css",".jpg",".gif","login.jsp","/user/login",".html",".png"};
			String path = request.getServletPath();
			boolean bool = false;
			for (String key : file) {
				if(path.toLowerCase().endsWith( key.toLowerCase() )){
					bool = true;
					break;
				}
			}
			if(bool){
				filterChain.doFilter(re, response);
			}else{
				request.getSession().invalidate();
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}
		}else{
			filterChain.doFilter(re, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}
