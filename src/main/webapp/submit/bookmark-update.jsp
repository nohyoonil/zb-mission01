<%@ page import="repository.BookMarkCRUD" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
    <%
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int sequence = Integer.parseInt(request.getParameter("sequence"));
        BookMarkCRUD.update(id, name, sequence);
    %>
    <script>
        alert(`북마크 그룹이 수정되었습니다.`)
        location.href='../jsp/bookmark-group.jsp'
    </script>
</body>
</html>
