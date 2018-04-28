<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <link href = "css/html5reset-1.6.1.css" rel = "stylesheet"> -->
<link href = "css/resulttable.css" rel = "stylesheet">

<c:set var = "answer" value="${surveyAnswer}"/>
<c:set var = "surveyCount" value = "${surveyCount}"/>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">	
	var list = [];
	<c:forEach var = "answer" items="${answer}">
		list.push("${answer}");
	</c:forEach>

	var surveyTitle = "${surveyTitle}";
    google.charts.load("current", {packages:['corechart']});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
      var data = google.visualization.arrayToDataTable([
        ["Element", "Density", { role: "style" } ],
        [list[0].substring(1, list[0].length-1).split("=")[0],
        	parseInt(list[0].substring(1, list[0].length-1).split("=")[1]), "#84A6F3"],
        [list[1].substring(1, list[1].length-1).split("=")[0], 
        	parseInt(list[1].substring(1, list[1].length-1).split("=")[1]), "#84A6F3"],
        [list[2].substring(1, list[2].length-1).split("=")[0], 
        	parseInt(list[2].substring(1, list[2].length-1).split("=")[1]), "#84A6F3"],
        [list[3].substring(1, list[3].length-1).split("=")[0], 
        	parseInt(list[3].substring(1, list[3].length-1).split("=")[1]), "#84A6F3"],
        [list[4].substring(1, list[4].length-1).split("=")[0], 
        	parseInt(list[4].substring(1, list[4].length-1).split("=")[1]), "#84A6F3"]
      ]);

      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);

      var options = {
        title: surveyTitle,
        width: 760,
        height: 300,
        bar: {groupWidth: "50%"},
        legend: { position: "none" },
      };
      var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
      chart.draw(view, options);
  }
 </script>
<title>결과창</title>
</head>
<body>
 <div id="columnchart_values" style="width: 900px; height: 300px;"></div>
 <div id = "total">
  <table class ="total_table">
   <tr>
    <th>번호</th>
    <th class = "answer">보기</th>
    <th>응답자수(응답률%)</th>
   </tr>
   <c:forEach var = "tmp" begin = "0" end = "4">
    <c:forEach var = "item" items= "${surveyAnswer[tmp]}" varStatus = "status">
     <c:set var = "decimal"><fmt:formatNumber type = "number" minFractionDigits = "0"
      maxFractionDigits = "2" value = "${item.value / surveyCount['총원'] * 100}"/></c:set>
     <tr>
      <td>${tmp + 1}</td>
      <td>${item.key}</td>
      <td>${item.value}(${decimal}%)</td>
     </tr>
    </c:forEach>
   </c:forEach>
  </table>
 </div>
 <div id = "group">
  <table class = "group_table">
   <tr>
    <td class = "hide" rowspan="3"></td>
    <th>전체</th>
    <th colspan="2">성별</th>
    <th colspan="5">연령</th>
   </tr>
   <tr class = "trcolor">
    <td rowspan="2">총원<br>${surveyCount['총원']}</td>
    <td>남</td>
    <td>여</td>
    <td>10대이하</td>
    <td>20대</td>
    <td>30대</td>
    <td>40대</td>
    <td>50대이상</td>
   </tr>
   <tr class = "trcolor">
    <td>${surveyCount['남성']}</td>
    <td>${surveyCount['여성']}</td>
    <td>${surveyCount['10대 이하']}</td>
    <td>${surveyCount['20대']}</td>
    <td>${surveyCount['30대']}</td>
    <td>${surveyCount['40대']}</td>
    <td>${surveyCount['50대 이상']}</td>
   </tr>
   <c:forEach var = "answer" items="${surveyAnswerCount}">
    <tr class = "trresult">
    <c:forEach var = "count" items = "${answer}" varStatus="status">
     <c:choose>
      <c:when test= "${status.first}">
       <th>${count}</th>
      </c:when>
      <c:otherwise>
       <td>${count}</td>
      </c:otherwise>
     </c:choose>
    </c:forEach>
    </tr>
   </c:forEach>
  </table>
 </div>
 <a href = "index.jsp"><label>메인화면</label></a> 
</body>
</html>