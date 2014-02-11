package com.sunshine.fusys.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

/**
 * Mybatis物理分页 
 * 创建人：fantasy <br>
 * 创建时间：2013-3-13 <br>
 * 
 */
public class ParsePagination {
	private Map<String, String> cookieMap;
	private int pageSize = 0;
	private int page = 1;

	public int getPageSize() {
		return pageSize;
	}

	public int getPage() {
		return page;
	}

	public ParsePagination(HttpServletRequest request) {
		cookieMap = new HashMap<String, String>();
		Cookie[] cookies = request.getCookies();
		parseCookies(cookies);
		String pageSize = request.getParameter("rows");
		String page = request.getParameter("page");
		String cookiePageSize = cookieMap.get("rowNum");
		String cookiePage = cookieMap.get("page");
		HttpSession session = request.getSession();
		String sessionPage = (String) session.getAttribute("page");
		String sessionPageSize = (String) session.getAttribute("rowNum");
		if (!StringUtils.isEmpty(pageSize)) {
			this.pageSize = Integer.parseInt(pageSize);
		} else if (!StringUtils.isEmpty(cookiePageSize)) {
			this.pageSize = Integer.parseInt(cookiePageSize);
		} else if (!StringUtils.isEmpty(sessionPageSize)) {
			this.pageSize = Integer.parseInt(sessionPageSize);
		}
		if (!StringUtils.isEmpty(page)) {
			this.page = Integer.parseInt(page);
		} else if (!StringUtils.isEmpty(cookiePage)) {
			this.page = Integer.parseInt(cookiePage);
		} else if (!StringUtils.isEmpty(sessionPage)) {
			this.page = Integer.parseInt(sessionPage);
		}
	}

	protected void parseCookies(Cookie[] cookies) {
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie.getValue());
			}
		}
	}

	public String getCookieByName(String name) {
		return cookieMap.get(name);
	}
}
