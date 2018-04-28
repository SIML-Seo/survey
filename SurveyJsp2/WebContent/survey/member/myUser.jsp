<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>내 계정 정보</title>
<%
	if(session.getAttribute("loginID") == null){
%>
<script>
	alert("로그인하지 않으셨습니다. 로그인 해주세요.");
	window.location = "login.jsp";
</script>
<%
	}
%>
<link href = "${pageContext.request.contextPath}/survey/css/html5reset-1.6.1.css" rel = "stylesheet">
<link href = "${pageContext.request.contextPath}/survey/css/meminfo.css" rel = "stylesheet">
</head>
<body>
 <div id = "meminfo">
  <table>
   <tr>
    <th>계정명</th>
    <td colspan="2">${sessionScope.loginID}</td>
   </tr>
   <tr>
    <th>획득 포인트</th>
    <td colspan="2">${sessionScope.point}</td>
   </tr>
   <tr>
    <td><a href = "updateUserPre.do"><span>정보 수정하기</span></a></td>
    <td><a href = "deleteUser.jsp"><span>계정 탈퇴하기</span></a></td>
    <td><a href = "../index.jsp"><span>메인 화면으로</span></a></td>
   </tr>
  </table>
 </div>
</body>
</html>