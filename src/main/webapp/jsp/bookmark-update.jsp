<%@ page import="repository.BookMarkCRUD" %>
<%@ page import="domain.BookMark" %>
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
<h1>북마크 수정</h1>
<a href="../index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="">Open API 와이파이 정보 가져오기</a> | <a href="bookmark-wifi.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹 관리</a>
<%
    String id = request.getParameter("id");
    BookMark bookmark = BookMarkCRUD.getBookmark(Integer.parseInt(id));
%>
    <form method="get" name="update_form" action="../submit/bookmark-update.jsp">
        <table id="bookmark">
            <tr><th class="left">북마크 이름</th><th class="right"><label for="name"></label><input type="text" id="name" name="name" value="<%=bookmark.getName()%>"></th></tr>
            <tr><th class="left">순서</th><th class="right"><label for="sequence"></label><input type="text" id="sequence" name="sequence" value="<%=bookmark.getSequence()%>"></th></tr>
            <tr><th colspan="2"><input type="button" onclick=submit(); value="수정"></th></tr>
            <input type="hidden" id="id" name="id" value="<%=id%>"/>
        </table>
    </form>
</body>
</html>
