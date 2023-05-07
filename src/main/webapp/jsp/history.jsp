<%@ page import="repository.LocationHistoryCRUD" %>
<%@ page import="domain.LocationHistory" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        #histories {
            border-collapse: collapse;
            width: 100%;
        }
        #histories td, #histories th {
            border: 1px solid #ddd;
            padding: 8px;
        }
        #histories tr:nth-child(even){background-color: #f2f2f2;}
        #histories tr:hover {background-color: #ddd;}

        #histories th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: center;
            background-color: #04AA6D;
            color: white;
        }
        #histories .etc {
            text-align: center;
        }
    </style>
</head>
<body>


<h1>위치 히스토리 목록</h1>
<a href="../index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="">Open API 와이파이 정보 가져오기</a> | <a href="bookmark-wifi.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹 관리</a>
<table id="histories">
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    <%
        String deleteButton = "<form method=\"get\" action=\"../submit/location-history-delete.jsp\"><input type=\"hidden\" id=\"id\" name=\"id\" value=\"%d\"><input type=\"button\" onclick=\"submit()\"; value=\"삭제\"/></form>";
        List<LocationHistory> locationHistories = LocationHistoryCRUD.getLocationHistories();
        for (LocationHistory locationHistory : locationHistories) {
            out.write("<tr>");
            out.write("<td>" + locationHistory.getId() + "</td>");
            out.write("<td>" + locationHistory.getLAT() + "</td>");
            out.write("<td>" + locationHistory.getLNT() + "</td>");
            out.write("<td>" + locationHistory.getInquiryDate() + "</td>");
            out.write("<td class=\"etc\">" + String.format(deleteButton, locationHistory.getId()) + "</td>");
            out.write("</tr>");
        }
    %>

</table>
</body>
</html>