<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href = "${pageContext.request.contextPath}/survey/css/login.css" rel = "stylesheet">
<title>로그인</title>
</head>
<body>
 <div id = "login">
  <label class = "loginl" for = "id">LOGIN</label>
  <form method = "post" action = "login.do">
   <input class = "loginin" type = "text" name = "id" placeholder="UserName" size="20"><br>
   <input class = "loginin" type = "password" name = "password" placeholder="Password" size = "20"><br>
   <br><br>
   <input class = "loginsubmit" type = "submit" value = "SignIn" >
  </form>
  <a href = "join.jsp" class = "regis"><span>Register</span></a>
 </div>
</body>
</html>