<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%
	boolean master = Boolean.TRUE == request.getAttribute("master");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<link href = "${pageContext.request.contextPath}/survey/css/boardstyle.css" rel = "stylesheet">
<link href = "${pageContext.request.contextPath}/survey/css/contentstyle.css" rel = "stylesheet">
<script src = "${pageContext.request.contextPath}/survey/js/script.js"></script>
</head>
<body>
<iframe id = "ifrm_file" style="position:absolute; z-index:1; visibility:hidden;"></iframe>
<section>
 <b>공지사항</b><br><br>
 <form>
  <table class = "contenttable">
   <tr>
    <th class = "contenttitleth">제 목</th>
    <td colspan="5" class = "contenttitle">${article.bTitle}</td>
    <td class = "date">${article.bRegDate}</td>
   </tr>
   <tr>
    <th class = "writer">글쓴이</th>
    <td>${article.bName}</td>
    <th class = "hit">조회</th>
    <td>${article.bHit}</td>
    <th class = "file">파일</th>
    <td>
     <c:choose>
      <c:when test="${empty article.bFileOriName}">
              업로드된 파일이 없습니다.
      </c:when>
      <c:otherwise>
       ${article.bFileOriName}&nbsp;&nbsp;<a href = "#" onclick="fileDown(${article.bNum})"><span>내려받기</span></a>
      </c:otherwise>
     </c:choose>
    </td>
    <td class = "blank"></td>
   </tr>
   <tr>
    <td colspan="7" class ="content">
     <pre>${article.bContent}</pre>
    </td>
   </tr>
   <tr>
    <td colspan="7" class = "tablefooter">
     <c:if test='${master }'>
      <input type = "button" value = "수 정" onclick="document.location.href='updatePre.do?num=${article.bNum}&pageNum=${pageNum}'">
      &nbsp;&nbsp;&nbsp;&nbsp;
      <input type="button" value = "삭 제" onclick = "document.location.href='deletePre.do?num=${article.bNum}&pageNum=${pageNum}'">
      &nbsp;&nbsp;&nbsp;&nbsp;
     </c:if>
     <input type = "button" value = "목 록" onclick = "document.location.href='list.do?pageNum=${pageNum}'">
     &nbsp;&nbsp;&nbsp;&nbsp;
     <input type = "button" value = "메인화면" onclick = "document.location.href='index.jsp'">
    </td>
   </tr>
  </table>
 </form>
</section>
</body>
</html>