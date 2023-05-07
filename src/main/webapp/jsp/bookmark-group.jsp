<%@ page import="repository.BookMarkCRUD" %>
<%@ page import="domain.BookMark" %>
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
        #histories .etc {text-align: center;}
    </style>
</head>
<body>
<h1>북마크 그룹 관리</h1>
<a href="../index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="">Open API 와이파이 정보 가져오기</a> | <a href="bookmark-wifi.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹 관리</a>
<br/>
<br/>
<form method="get" name="bookmark-create" action="bookmark-create.jsp">
    <input type="button" onclick=submit(); value="북마크 그룹 이름 추가">
<table id="histories">
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>순서</th>
        <th>등록일자</th>
        <th>수정일자</th>
        <th>비고</th>
    </tr>
<%
    List<BookMark> bookmarks = BookMarkCRUD.getBookmarks();
    if (bookmarks.isEmpty()) {
        out.write("<tr><td class=\"etc\" colspan=\"6\">정보가 존재하지 않습니다.</td></tr>");
    } else {
        for (BookMark bookmark : bookmarks) {
            out.write("<tr>");
            out.write("<td>" + bookmark.getId() + "</td>");
            out.write("<td>" + bookmark.getName() + "</td>");
            out.write("<td>" + bookmark.getSequence() + "</td>");
            out.write("<td>" + bookmark.getRegistrationDate() + "</td>");
            out.write("<td>" + bookmark.getUpdateDate() + "</td>");
            out.write("<td class=\"etc\">" + "<a href=\"bookmark-update.jsp?id=" + bookmark.getId() +"\">수정</a> " + "<a href=\"bookmark-delete.jsp?id=" + bookmark.getId() + "\">삭제</a></td>");
            out.write("</tr>");
        }
    }
%>
</table>
</body>
</html>
