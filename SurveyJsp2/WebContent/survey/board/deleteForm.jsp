<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href = "${pageContext.request.contextPath}/survey/css/boardstyle.css" rel = "stylesheet">
<link href = "${pageContext.request.contextPath}/survey/css/deleteFormstyle.css" rel = "stylesheet">
<title>게시판</title>
</head>
<body>
<section>
 <form method = "post" name = "delForm" action = "deleteArticle.do?pageNum=${pageNum}">
  <table class = "deletetable">
   <tr>
    <td><b>정말로 삭제하시겠습니까?</b></td>
   </tr>
   <tr>
    <td>
     <input type = "hidden" name = "num" value = "${num}">
     <input type = "radio" name = "del" id = "yes" value = "yes" checked>
     <label for = "yes">네</label>
     <input type = "radio" name = "del" id = "no" value = "no">
     <label for = "no">아니오</label>
    </td>
   </tr>
   <tr>
    <td>
     <input type = "submit" value = "삭제">
     <input type = "button" value = "목록" onclick = "window.location='list.do?pageNum=${pageNum}'">
    </td>
   </tr>
  </table>
 </form>
</section>
</body>
</html>