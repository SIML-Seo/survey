<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<script src = "${pageContext.request.contextPath}/survey/js/script.js"></script>
<link href ="${pageContext.request.contextPath}/survey/css/join.css" rel = "stylesheet">
<title>회원가입</title>
</head>
<body>
 <fieldset>
  <legend>회원가입</legend>
   <form method = "post" action = "join.do" name = "regForm">
    <ul>
     <li>
      <label for = "idd">ID</label><br>
      <input type = "text" id = "idd" name = "id" title = "ID입력" maxlength="10" required/>
      <input type = "button" class = "graybtn" onclick = "idCheck(this.form.id.value)" value = "중복확인"/><!-- <span>중복확인</span></input> -->
     </li>
     <li><label for = "passwd">비밀번호</label><br>
      <input type = "password" id ="passwd" name ="password" title = "비밀번호입력" required/>
     </li>
     <li><label for = "passwdd">비밀번호 확인</label><br>
      <input type = "password" id = "passwdd" name = "repassword" title = "비밀번호재입력" required/>
     </li>
     <li><label for = "emaill">Email</label><br>
      <input type = "text" id = "emaill" name = "email1" title = "이메일입력" required/>
      @
      <input type = "text" name = "email2" required/>
      <select name = "emaillist" onchange="this.form.email2.value = this.value">
       <option value = "">직접입력</option>
       <option value = "naver.com">naver.com</option>
       <option value = "gmail.com">gmail.com</option>
       <option value = "daum.net">daum.net</option>
      </select>
     </li>
     <li style= "list-style-type:none;">
      <input type = "submit" class = "redbtninput" onclick = "inputCheck()" value = "가입하기">
      <a href = "../index.jsp" class = "redbtn"><span>메인화면으로</span></a>
     </li>
    </ul>
   </form>
 </fieldset>
</body>
</html>