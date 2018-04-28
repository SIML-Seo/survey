<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%
	boolean master = Boolean.TRUE == request.getAttribute("master");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항</title>
<link href = "${pageContext.request.contextPath}/survey/css/boardstyle.css" rel = "stylesheet">
<link href = "${pageContext.request.contextPath}/survey/css/liststyle.css" rel = "stylesheet">
</head>
<body>
 <section>
  <b>공지사항(전체 글: ${count})</b>
  <table class = "listwritebutton">
   <tr>
    <td>
      <a href = "index.jsp">메인화면</a>
     <c:if test='${master}'>
      <a href = "writePre.do">글쓰기</a>
     </c:if>
    </td>
   </tr>
  </table>
  <c:if test = "${count == 0 }">
   <table class ="listtable">
    <tr>
     <td>등록된 공지사항이 없습니다.</td>
    </tr>
   </table>
  </c:if>
  <c:if test = "${count > 0 }">
   <table class = "listtable">
    <tr>
     <th id = "num">번호</th>
     <th id = "title">제목</th>
     <th id = "writer">글쓴이</th>
     <th id = "date">날짜</th>
     <th id = "hit">조회</th> 
    </tr>
    <c:forEach var = "article" items = "${articleList}">
     <tr>
      <td align = "center" width = "50">
       <c:out value = "${number }"/>
       <c:set var = "number" value = "${number -1 }"/>
      </td>
      <td class = "titleld">
       <a href = "content.do?num=${article.bNum}&pageNum=${currentPage}">${article.bTitle}</a>
      </td>
      <td>${article.bName}</td>
      <td>${article.bRegDate}</td>
      <td>${article.bHit}</td>
     </tr>
    </c:forEach>
   </table>
  </c:if>
  <c:if test = "${count > 0}">
   <c:set var = "imsi" value = "${count % pageSize == 0 ? 0 : 1}"/>
   <c:set var = "pageCount" value = "${count / pageSize + imsi}"/>
   <c:set var = "pageBlock" value = "${3 }"/>
   <fmt:parseNumber var = "result" value = "${currentPage / pageBlock}" integerOnly="true"/>
   <c:set var = "startPage" value = "${result * pageBlock + 1 }"/>
   <c:set var = "endPage" value = "${startPage + pageBlock - 1 }"/>
   
   <c:if test = "${endPage > pageCount}">
    <c:set var = "endPage" value = "${pageCount}"/>
   </c:if>
   
   <c:if test = "${startPage > pageBlock}">
    <a href = "list.do?pageNum=${startPage - pageBlock}">이전</a>
   </c:if>
   
   <c:forEach var = "i" begin="${startPage}" end ="${endPage}">
    <a href = "list.do?pageNum=${i}">[${i}]</a>
   </c:forEach>
   
   <c:if test = "${endPage < pageCount}">
    <a href = "list.do?pageNum=${startPage + pageBlock}">다음</a>
   </c:if>
  </c:if>
 </section>	
</body>
</html>