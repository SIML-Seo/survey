<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	String id = request.getParameter("id");
	String result = request.getParameter("result");
	int point = (int)request.getAttribute("point");
	if(result.equals("success")){
		session.setAttribute("loginID", id);
		session.setAttribute("point", point);
		response.sendRedirect("../index.jsp");
	}else if (result.equals("fail")){
 %>
 <script>
 	alert("비밀번호가 틀렸습니다.");
 	window.location = "login.jsp";
 </script>
 <% 
	}else{
 %>
 <script>
 	alert("존재하지 않는 ID입니다.");
 	window.location = "login.jsp";
 </script>
 <% 
	}
 %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>