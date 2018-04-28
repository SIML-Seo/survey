<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "DataObject.SurveyDao" %>
<%
	String id = request.getParameter("id");
	SurveyDao dao = SurveyDao.getInstance();
	boolean check = dao.idCheck(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href = "${pageContext.request.contextPath}/survey/css/style.css" rel = "stylesheet">
<title>ID 중복확인</title>
</head>
<body>
<br><br>
<b><%=id %></b>
<%
	if(check){
%>
는 생성가능한 아이디입니다.
<%
	}else{
%>
는 중복된 아이디입니다.
<%
	}
%>
<br><br>
<a href = "#" onclick = "self.close()">닫기</a>
</body>
</html>