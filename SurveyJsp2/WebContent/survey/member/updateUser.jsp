<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix = "m" uri="/WEB-INF/tlds/email.tld" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<script src = "${pageContext.request.contextPath}/survey/js/script.js"></script>
<link href = "${pageContext.request.contextPath}/survey/css/join.css" rel = "stylesheet">
<title>회원 정보 수정하기</title>
</head>
<body>
 <fieldset>
  <legend>회원가입</legend>
   <form method = "post" action = "updateUser.do" name = "regForm">
    <ul>
     <li><label>ID</label><br>
      <input type = "hidden" name = "id" value = "${User.id}">
  	  <label class = "idL">${User.id}</label> 
  	 </li>
     <li><label for = "passwd">비밀번호</label><br>
      <input type = "password" id ="passwd" name ="password" title = "비밀번호입력" required/>
     </li>
     <li><label for = "passwdd">비밀번호 확인</label><br>
      <input type = "password" id = "passwdd" name = "repassword" title = "비밀번호재입력" required/>
     </li>
     <li><label for = "emaill">Email</label><br>
      <input type = "text" id = "emaill" name = "email1" title = "이메일입력" placeholder = "${m : mysub(User, 1)}" required/>
      @
      <input type = "text" name = "email2" placeholder = "${m : mysub(User, 2)}" required/>
      <select name = "emaillist" onchange="this.form.email2.value = this.value">
       <option value = "">직접입력</option>
       <option value = "naver.com">naver.com</option>
       <option value = "gmail.com">gmail.com</option>
       <option value = "daum.net">daum.net</option>
      </select>
     </li>
     <li>
      <br><br>
      <!-- <a href = "join.do" class = "redbtn" onclick = "inputCheck()"><span>가입하기</span></a> -->
      <input type = "submit" class = "redbtn" onclick = "updateCheck()" value = "수정하기">
      <input type = "button" class = "redbtn" onclick = "window.location = '../index.jsp'" value = "취소하기">
     </li>
    </ul>
   </form>
 </fieldset>
</body>
</html>