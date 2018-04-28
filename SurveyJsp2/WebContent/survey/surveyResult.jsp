<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	int point = (int)request.getAttribute("point");
	session.setAttribute("point", point);
%>
<script>
 alert("설문조사를 완료했습니다.\n포인트 1000점을 적립하였습니다.");
 window.location.href = "surveyList.do"
</script>
<title>Insert title here</title>
</head>
<body>

</body>
</html>