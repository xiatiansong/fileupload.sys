<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传系统</title>
<%@include file="/common/head.jsp" %>
<script type="text/javascript">
	jQuery(function() {
		var menuLI = document.getElementById('levelmenu2').getElementsByTagName('li');
		var menuid = "";
		for ( var i = 0; i < menuLI.length; i++) {
			var menuA = menuLI[i].getElementsByTagName('a');
			for ( var j = 0; j < menuA.length; j++) {
				if (i == 0 && j == 0) {
					menuid = "#" + menuA[j].id;
				} else {
					menuid = menuid + ",#" + menuA[j].id;
				}
			}
		}
		var list = jQuery(menuid);
		list.click(function() {
			list.removeClass('on');
			jQuery(this).addClass('on');
			return true;
		});
	});
</script>
</head>
<body>
<div class="left" id="levelmenu2">
    <ul>
    	<li><a id="1" class="on" target="rightframe" href="${pageContext.request.contextPath}/userFile/indexPage">文件管理</a></li>
    </ul>
</div>
</body>
</html>