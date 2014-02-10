<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>文件上传系统</title>
<%@include file="/common/head.jsp" %>
</head>
<body>
<!-- activate -->
<div class="activate">
	<h2>错误提示<c:if test="${ret_map.status == 1}">：${ret_map.msg}</c:if></h2>
    <div class="activebox">
        <div class="errorPage">
        	<p><b>出错了！！</b><a href="${pageContext.request.contextPath}">后退</a></p>
        </div>
    </div>
</div>
<!-- activate -->
</body>
</html>