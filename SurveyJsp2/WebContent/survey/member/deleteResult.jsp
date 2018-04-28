<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	boolean result = Boolean.TRUE == request.getAttribute("result");
	if(result){		
		session.invalidate();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Refresh" content = "3;url=../index.jsp">
<title>삭제완료</title>
</head>
<body>
회원정보가 삭제되었습니다.
3초후 메인페이지로 이동합니다.
<%
	}else{
%>
<script>
	alert('비밀번호가 잘못되었습니다.');
	history.go(-1);
</script>
<%
	}
%>
</body>
</html>