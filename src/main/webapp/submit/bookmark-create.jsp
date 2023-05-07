<%@ page import="repository.BookMarkCRUD" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
    <%
        String name = request.getParameter("name");
        int sequence = Integer.parseInt(request.getParameter("sequence"));
        BookMarkCRUD.create(name, sequence);
    %>
    <script>
        alert('새로운 북마크 그룹이 등록되었습니다.')
        location.href='../jsp/bookmark-group.jsp';
    </script>
</body>
</html>
