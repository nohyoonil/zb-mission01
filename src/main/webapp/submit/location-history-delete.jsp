<%@ page import="repository.LocationHistoryCRUD" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<%
    String id = request.getParameter("id");
    LocationHistoryCRUD.delete(Integer.parseInt(id));
%>
<script>
    alert(`위치 히스토리가 삭제되었습니다.`)
    location.href='../jsp/history.jsp';
</script>
</body>
</html>
