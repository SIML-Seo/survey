<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id = "currentDate" class="java.util.Date"/>
<fmt:formatDate var = "now" value = "${currentDate}" pattern="yyyy-MM-dd"/>
<!DOCTYPE html>
<html>
<head>
<%
	if(session.getAttribute("loginID") == null){
%>
<script>
	alert("로그인하지 않으셨습니다. 로그인 해주세요.");
	window.location = "member/login.jsp";
</script>
<%
	}
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href = "${pageContext.request.contextPath}/survey/css/surveylist.css" rel="stylesheet">
<title>설문조사 리스트</title>
</head>
<body>
 <div id = "list">
  <table id = "listtb">
   <tr>
    <td colspan = "4" class = "head">지금 가능한 설문조사</td>
   </tr>
   <tr class = "catalog">
    <td class = "date">설문가능일</td>
    <td class = "num">설문조사NO.</td>
    <td class = "title">설문조사 제목</td>
    <td class = "direct">▼ 클릭</td>
   </tr>
   <tr>
    <td colspan = "4" class = "article">■ 설문조사 목차</td>
   </tr>
   <c:forEach var = "list" items="${surveyList}" varStatus = "listStatus">
    <tr class = "content">
     <td>${list.tRegDate}</td>
     <td>${list.tNum}</td>
     <td>${list.tTitle}</td>
     <td>
      <c:set var = "done" value="false"/>
      <c:forEach var = "finishList" items="${finishUser[listStatus.index]}" varStatus = "status">
       <c:choose>	
        <c:when test = "${list.tRegDate lt now and not done}">
         <a href = "#" class = "close"><span>기간경과</span></a>
         <c:set var = "done" value ="true"/>
        </c:when>
        <c:when test="${finishList eq sessionScope.loginID and not done}">
         <a href = "surveyOutcome.do?num=${list.tNum}" class ="end"><span>결과보기</span></a>
         <c:set var = "done" value ="true"/>         
        </c:when>
        <c:when test = "${status.last && not done}">
         <a href = "survey.do?num=${list.tNum}" class = "start"><span>시작하기</span></a>
        </c:when>
       </c:choose>
      </c:forEach>
     </td>
    </tr>
   </c:forEach>
  </table>
 </div>
 <a href = "index.jsp"><label>메인화면</label></a> 
</body>
</html>