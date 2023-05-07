<%@ page import="repository.BookMarkCRUD" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
  <%
    String id = request.getParameter("id");
    BookMarkCRUD.delete(Integer.parseInt(id));
  %>
  <script>
    alert(`북마크 그룹이 삭제되었습니다.`)
    location.href='../jsp/bookmark-group.jsp';
  </script>
</body>
</html>
