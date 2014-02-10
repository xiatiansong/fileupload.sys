<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>文件上传系统</title>
<%@include file="/common/head.jsp" %>
</head>
<frameset rows="48,*" cols="*" frameborder="no" border="0" id="parentFrame" name="parentFrame" framespacing="0">
  <frame src="${pageContext.request.contextPath}/indexMain/indexTop" name="topFrame" frameborder="no" scrolling="no" noresize="noresize" marginheight="48" id="topFrame" title="topFrame" />
    <frameset id="subParentFrame" name="subParentFrame" rows="*" cols="200,*" framespacing="0" frameborder="no" border="0">
    <frame src="${pageContext.request.contextPath}/indexMain/indexLeft" name="leftFrame" frameborder="no" scrolling="no" noresize="noresize" marginwidth="200" id="leftFrame" class="leftframe" title="leftFrame" />
    <frame src="${pageContext.request.contextPath}/indexMain/indexRight" name="rightframe" frameborder="no" overflow-x="none"  scrolling="auto" id="rightframe" class="rightframe" title="rightframe" />
  </frameset>
</frameset>
<noframes>
</noframes>
</html>