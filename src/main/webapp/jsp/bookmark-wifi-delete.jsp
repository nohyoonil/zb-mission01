<%@ page import="repository.BookMark_WifiCRUD" %>
<%@ page import="domain.BookMark_WIfi" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        #bookmark {
            /*font-family: Arial, Helvetica, sans-serif;*/
            border-collapse: collapse;
            width: 100%;
        }
        #bookmark td, #bookmark th {
            border: 1px solid #ddd;
            padding: 8px;
        }
        #bookmark tr:nth-child(even){background-color: #f2f2f2;}

        #bookmark .right th:hover { background-color: #ddd;}

        #bookmark .left {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: center;
            background-color: #04AA6D;
            color: white;
        }
        #bookmark .right {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
        }
    </style>
</head>
<body>
<h1>북마크 삭제</h1>
<a href="../index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="">Open API 와이파이 정보 가져오기</a> | <a href="bookmark-wifi.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹 관리</a>
<%
    String id = request.getParameter("id");
    BookMark_WIfi bookmarkWifi = BookMark_WifiCRUD.getBookmarkWifi(Integer.parseInt(id));
%>
<br/>
<br/>
북마크 그룹 이름을 삭제하시겠습니까?
<br/>
<br/>
<form method="get" name="delete_form" action="../submit/bookmark-wifi-delete.jsp">
    <table id="bookmark">
        <tr><th class="left">북마크 이름</th><th class="right"><%=bookmarkWifi.getBookmarkName()%></th></tr>
        <tr><th class="left">와이파이명</th><th class="right"><%=bookmarkWifi.getWifiName()%></th></tr>
        <tr><th class="left">등록일자</th><th class="right"><%=bookmarkWifi.getRegistrationData()%></th></tr>
        <tr><th colspan="2"><a href="bookmark-wifi.jsp">돌아가기</a> | <input type="button" onclick=submit(); value="삭제"></th></tr>
        <input type="hidden" id="id" name="id" value="<%=id%>"/>
    </table>
</form>
</body>
</html>
