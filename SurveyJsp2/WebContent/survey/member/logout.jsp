<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	session.invalidate();
%>
<script>
	alert("로그아웃하였습니다.");
</script>
<%
	response.sendRedirect("../index.jsp");
%>

</body>
</html>