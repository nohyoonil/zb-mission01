<%@ page import="repository.BookMark_WifiCRUD" %>
<%@ page import="repository.BookMarkCRUD" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
    <%
        int bookmarkId = Integer.parseInt(request.getParameter("bookmarkId"));
        String wifiId = request.getParameter("wifiId");
        String wifiName = request.getParameter("wifiName");
        String bookmarkName = BookMarkCRUD.getBookmarkName(bookmarkId);
        BookMark_WifiCRUD.create(bookmarkName, wifiName, bookmarkId, wifiId);
    %>
    <script>
        alert('새로운 북마크가 추가되었습니다.')
        location.href='../jsp/bookmark-wifi.jsp'
    </script>
</body>
</html>
