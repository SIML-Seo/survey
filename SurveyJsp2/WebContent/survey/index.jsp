<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"  %>
<%@ page import = "DataObject.SurveyDao" %>
<%@ page import = "DataObject.SurveyBoardDto" %>
<%@ page import = "DataObject.SurveyUserDto" %>
<%@ page import = "java.util.List" %>
<%
 SurveyDao dao = SurveyDao.getInstance();
 List<SurveyBoardDto> list = dao.findArticleRecently();
%>
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
 <link href = "${pageContext.request.contextPath}/survey/css/html5reset-1.6.1.css" rel = "stylesheet">
 <link href = "${pageContext.request.contextPath}/survey/css/basic.css" rel = "stylesheet">
<title>메인화면</title>
</head>
<body>	
 <header id="header">
  <nav>
   <div class="logo">
    <a href="#"><img src="http://via.placeholder.com/75x50" alt="메인화면"></a>
   </div>
   <div class="top_menu">
    <a href="list.do">공지사항</a> 
    <a href="surveyList.do">설문조사</a> 
    <a href="#">쇼핑몰</a> 
    <a href="member/myUser.jsp">내 계정</a>
   </div>
   <div class="top_side">
    <a href="index.jsp">HOME</a> <a href="member/join.jsp">회원가입</a> 
    <c:choose>
     <c:when test="${empty sessionScope.loginID}">
      <a href="member/login.jsp">로그인</a>
     </c:when>
     <c:otherwise>
      <a href="member/logout.jsp">로그아웃</a>
     </c:otherwise>
    </c:choose>
   </div>
  </nav>
 </header>
 <section id="content">
  <div id="maindiv">
   <table id="maintable">
    <tr>
     <td class="notice">
      <span>공지사항</span>
	   <div class = "noticeArticle">
	    <ul>
		 <c:set var = "list" value="<%= list %>"/>
		 <c:forEach var = "article" items = "${list}" varStatus="status" end= "2">
		  <li><a href = "content.do?num=${article.getbNum()}&pageNum=1">${article.getbTitle()}</a></li>
		 </c:forEach>
	    </ul>
	  </div>		     	
     </td>
     <td><a href="#"><img src="http://via.placeholder.com/150x150" alt="설문조사"></a></td>
    </tr>
   </table>
  </div>
 </section>
 <aside id="login">
  <form>
   <div>
    <table class="loginBG">
     <tr>
      <th>현재 획득 포인트</th>
     </tr>
     <c:choose>
      <c:when test = "${empty sessionScope.loginID}">
       <tr>
        <td>로그인하지 않으셨습니다.</td>
       </tr>
       <tr>
        <td><input type="button" value="로그인" class="loginB" onclick = "window.location = 'member/login.jsp'"></td>
       </tr>
      </c:when>
      <c:otherwise>
       <tr>
        <td>${sessionScope.point}점</td>
       </tr>
       <tr>
        <td><input type="button" value="로그아웃" class="loginB" onclick = "window.location = 'member/logout.jsp'"></td>
       </tr>
      </c:otherwise>
     </c:choose>
    </table>
   </div>
  </form>
 </aside>
 <footer id="footer"> (亡)과제물 부산광역시 부산진구 범천로 22 | 제출자 : 서원호 | MP : 010-2411-0757 </footer>
</body>
</html>