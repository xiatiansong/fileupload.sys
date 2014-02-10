package com.sunshine.fusys.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 主页框架控制器
 */
@Controller
@RequestMapping("/indexMain")
public class IndexMainController extends BaseController{
	
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) throws Exception {
		return "main/index-main";
	}
	
	@RequestMapping(value = "/indexTop")
	public String indexTop(HttpServletRequest request) throws Exception {
		return "main/top";
	}
	
	@RequestMapping(value = "/indexRight")
	public String indexRight(HttpServletRequest request) throws Exception {
		return "main/right";
	}
	
	@RequestMapping(value = "/indexLeft")
	public String indexLeft(HttpServletRequest request) throws Exception {
		return "main/left";
	}
}