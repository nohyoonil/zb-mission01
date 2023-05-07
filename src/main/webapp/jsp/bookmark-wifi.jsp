<%@ page import="repository.BookMark_WifiCRUD" %>
<%@ page import="domain.BookMark_WIfi" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        #bookmark {
            border-collapse: collapse;
            width: 100%;
        }
        #bookmark td, #bookmark th {
            border: 1px solid #ddd;
            padding: 8px;
        }
        #bookmark tr:nth-child(even){background-color: #f2f2f2;}
        #bookmark tr:hover {background-color: #ddd;}

        #bookmark th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: center;
            background-color: #04AA6D;
            color: white;
        }
        #bookmark .etc {text-align: center;}
    </style>
</head>
<body>
<h1>북마크 목록</h1>
<a href="../index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="">Open API 와이파이 정보 가져오기</a> | <a href="bookmark-wifi.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹 관리</a>
<table id="bookmark">
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>와이파이명</th>
        <th>등록일자</th>
        <th>비고</th>
    </tr>
    <%
        List<BookMark_WIfi> bookmarkWifis = BookMark_WifiCRUD.getBookmarkWifis();
        if (bookmarkWifis.isEmpty()) {
            out.write("<tr><td class=\"etc\" colspan=\"5\">정보가 존재하지 않습니다.</td></tr>");
        } else {
            for (BookMark_WIfi bookmarkWifi : bookmarkWifis) {
                out.write("<tr>");
                out.write("<td>" + bookmarkWifi.getId());
                out.write("<td>" + bookmarkWifi.getBookmarkName());
                out.write("<td>" + bookmarkWifi.getWifiName());
                out.write("<td>" + bookmarkWifi.getRegistrationData());
                out.write("<td class=\"etc\">" + "<a href=\"bookmark-wifi-delete.jsp?id=" + bookmarkWifi.getId() +"\">삭제</a></td>");
                out.write("</tr>");
            }
        }
    %>
</table>
</body>
</html>
