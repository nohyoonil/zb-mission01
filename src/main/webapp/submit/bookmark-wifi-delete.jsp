<%@ page import="repository.BookMark_WifiCRUD" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
  <%
    int id = Integer.parseInt(request.getParameter("id"));
    BookMark_WifiCRUD.delete(id);
  %>
  <script>
    alert(`북마크가 삭제되었습니다.`)
    location.href='../jsp/bookmark-wifi.jsp'
  </script>
</body>
</html>
