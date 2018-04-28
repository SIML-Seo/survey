<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href = "${pageContext.request.contextPath}/survey/css/boardstyle.css" rel = "stylesheet">
<link href = "${pageContext.request.contextPath}/survey/css/writeFormstyle.css" rel = "stylesheet">
<script src = "${pageContext.request.contextPath}/survey/js/script.js"></script>
<title>게시판</title>
</head>
<body>
<section>
 <article>
  <b>글쓰기</b><br><br>
  <form action = "write.do" name = "writeForm" method = "post" onsubmit="return writeSave()" enctype="Multipart/form-data">				<input type = "hidden" name = "num" value = "${num}">
   <table class = "board">
    <tr>
     <td class = "attr">이 름</td>
     <td>
      <input type = "text" name = "name">
     </td>
    </tr>
    <tr>
     <td class = "attr">제 목</td>	
     <td>
      <input class = "input" type = "text" name = "title">
     </td>
    </tr>
    <tr>
     <td class = "attr">파일 업로드</td>
     <td>
      <input class = "file" type = "file" name = "uploadfile">
     </td>
    </tr>	
    <tr>
     <td class = "attr">내 용</td>
     <td>
      <textarea name = "content" rows = "17" cols="70"></textarea>
     </td>
    </tr>
    <tr>
     <td colspan = "2" class = "tablefooter">
      <input type = "submit" value = "글쓰기">
      <input type = "reset" value = "다시작성">
      <input type = "button" value = "목록" onclick ="document.location.href='list.do'">
     </td>
    </tr>
   </table>
  </form>
 </article>
</section>
</body>
</html>