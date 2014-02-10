package com.sunshine.fusys.controllers;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础控制controller
 */
public class BaseController {
    
    /**JSP页面-新增**/
	protected final static String VIEW_NEW = "new"; 
	
	/**JSP页面-主页**/
	protected final static String VIEW_INDEX = "index";
	
	/**JSP页面-编辑**/
	protected final static String VIEW_EDIT = "edit";
	
	/** JSP页面-详细**/
	protected final static String VIEW_SHOW = "show";
	
	/** 重定向**/
	protected final static String REQUEST_REDIRECT = "redirect:";
	
	/** 成功页面**/
	protected final static String SUCCESS = "success";
	
	/** 失败页面**/
	protected final static String FAILED = "failed";
	
	@Autowired  
	protected  HttpServletRequest request;
	
	protected static final Log LOG = LogFactory.getLog(BaseController.class);
	
	// AJAX输出，返回null
    public String ajax(String content, String type, HttpServletResponse response) {
        try {
            response.setContentType(type + ";charset=UTF-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.getWriter().write(content);
            response.getWriter().flush();
        } catch (IOException e) {
            LOG.error("ajax", e);
        }
        return null;
    }
	
	/**AJAX输出HTML，返回null**/
    public String ajaxHtml(String html, HttpServletResponse response) {
        return ajax(html, "text/html", response);
    }
    
    /**AJAX输出json，返回null**/
    public String ajaxJson(String json, HttpServletResponse response) {
        return ajax(json, "application/json", response);
    }
}