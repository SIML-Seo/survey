<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원탈퇴</title>
<link href = "${pageContext.request.contextPath}/survey/css/style.css" rel = "stylesheet" type = "text/css">
<script src = "${pageContext.request.contextPath}/survey/js/script.js"></script>
</head>
<body>
<form name = "myForm" method = "post" action = "deleteUser.do" onsubmit = "return passcheck()">
 <input type = "hidden" name = "id" value = "${sessionScope.loginID}">
 <table class = "deleteTable">
  <tr>
   <td colspan = "2"><b>회원 탈퇴</b></td>
  </tr>
  <tr>
   <td><b>비밀번호 입력</b></td>
   <td><input type = "password" name = "password"></td>
  </tr>
 </table>
 <input type = "submit" value = "회원탈퇴">
 <input type = "button" value = "취소" onclick="window.location='../index.jsp'">
</form>
</body>
</html>