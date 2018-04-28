<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix= "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<link href = "${pageContext.request.contextPath}/survey/css/boardstyle.css" rel = "stylesheet">
<link href = "${pageContext.request.contextPath}/survey/css/writeFormstyle.css" rel = "stylesheet">
<script src = "${pageContext.request.contextPath}/survey/js/script.js"></script>
</head>
<body>
<iframe id = "ifrm_file" style="position:absolute; z-index:1; visibility:hidden;"></iframe>
<section>
 <b>글수정</b><br><br>
 <form method = "post" name = "writeForm" action = "updateArticle.do?pageNum=${pageNum}" onsubmit = "return writeSave()" enctype = "Multipart/form-data">
  <table class = "board">
   <tr>
    <td class = "attr">이 름</td>
    <td>
     ${article.bName}
     <input type = "hidden" name = "num" value = "${article.bNum}">
     <input type = "hidden" name = "name" value = "${article.bName}">
    </td>
   </tr>
   <tr>
    <td class = "attr">제 목</td>
    <td>
     <input class = "input" type = "text" name = "title" placeholder = "${article.bTitle}">
    </td>
   </tr>
   <tr>
    <td class = "attr">파 일</td>
    <td id = "filetd">
    <c:choose>
     <c:when test="${empty article.bFileOriName}">
       업로드된 파일이 없습니다.
     </c:when>
     <c:otherwise>
      ${article.bFileOriName}&nbsp;&nbsp;<a href = "#" onclick="fileDown(${article.bNum})"><span>내려받기</span></a>&nbsp;&nbsp;
      <a href = "#" onclick = "fileDelete(${article.bNum})"><span>삭제하기</span></a>
     </c:otherwise>
    </c:choose>
   </td>
  </tr>
  <tr>
   <td class = "attr">내 용</td>
   <td>
    <textarea name = "content" rows = "17" cols="70" placeholder = "${article.bContent}"></textarea>
   </td>
  </tr>
  <tr>
   <td colspan = "2" class = "tablefooter">
    <input type = "submit" value = "글수정">
    <input type = "reset" value = "다시작성">
    <input type = "button" value = "목록보기" onclick = "window.location='list.do?pageNum=${pageNum}'">
   </td>
  </tr>
 </table>
</form>
</section>
</body>
</html>