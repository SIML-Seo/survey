<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href = "css/html5reset-1.6.1.css" rel = "stylesheet">
<link href = "css/survey.css" rel="stylesheet">
<script src = "${pageContext.request.contextPath}/survey/js/script.js"></script>
<title>설문조사</title>
</head>
<body>
 <div id = "survey">
  <form action = "surveyResult.do" method = "post" name = "surveyForm">
   <input type = "hidden" name = "surveyNum" value = "${surveyNum}">
   <input type = "hidden" name = "surveyItemCount" value = "${surveyItemCount}">
   <div>
    <span class ="title">${surveyTitle}</span>
   </div>
   <c:forEach var = "item" items="${surveyItems}" varStatus = "itemstatus">
    <div class = "item">
     <div class = "subject">
      <span class = "num">Q${itemstatus.count}</span>
      <span class = "question">귀하의 <span class = "sub">${item.value}</span>을(를) 입력해주세요.</span>
     </div>
     <ul>
      <c:forEach var = "article" items="${surveyArticle[itemstatus.index]}" varStatus = "articlestatus">
       <li>
        <input type = "radio" name = "Answer${itemstatus.count}" id = "AValue${itemstatus.count}_${articlestatus.count}" value="${article.value}">
        <label for = "AValue${itemstatus.count}_${articlestatus.count}">${article.value}</label>
       </li>
      </c:forEach>
     </ul>
    </div>
   </c:forEach>
   <input type = "submit" value = "제 출 하 기" onclick = "articleCheck()" style = "margin-left: 250px;">
   <!-- <a href = "#"><span>제 출 하 기</span></a> -->
  </form>
 </div>
</body>
</html>