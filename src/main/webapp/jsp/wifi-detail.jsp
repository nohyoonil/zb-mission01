<%@ page import="repository.WifiCRUD" %>
<%@ page import="domain.WifiInfo" %>
<%@ page import="repository.BookMarkCRUD" %>
<%@ page import="domain.BookMark" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        #wifi {
            /*font-family: Arial, Helvetica, sans-serif;*/
            border-collapse: collapse;
            width: 100%;
        }
        #wifi td, #wifi th {
            border: 1px solid #ddd;
            padding: 8px;
        }
        #wifi tr:nth-child(even){background-color: #f2f2f2;}

        #wifi .right th:hover { background-color: #ddd;}

        #wifi .left {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: center;
            background-color: #04AA6D;
            color: white;
        }
        #wifi .right {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
        }
    </style>
</head>
<body>
    <h1>와이파이 정보 구하기</h1>
    <a href="../index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="">Open API 와이파이 정보 가져오기</a> | <a href="bookmark-wifi.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹 관리</a>
    <br/>
    <%
        String wifiId = request.getParameter("MGR_NO");
        WifiInfo wifiInfo = WifiCRUD.getWifiInfoByMGR_NO(wifiId);
    %>

    <form action="../submit/bookmark-wifi-create.jsp">
        <select name="bookmarkId" id="lang">
            <option autofocus>북마크 그룹 선택</option>
            <%
                List<BookMark> bookmarks = BookMarkCRUD.getBookmarks();
                String format = "<option value=\"%d\">%s</option>";
                for (BookMark bookmark : bookmarks) {
                    out.write(String.format(format, bookmark.getId(), bookmark.getName(), bookmark.getName()));
                }
            %>
        </select>
        <input type="hidden" name="wifiId" value="<%=wifiId%>">
        <input type="hidden" name="wifiName" value="<%=wifiInfo.getX_SWIFI_MAIN_NM()%>">
        <input type="submit" value="북마크 추가하기" />
    </form>
    <table id = "wifi">
        <%
            String stringFormat = "<tr><th class=\"left\">%s</th><th class=\"right\">%s</th></tr>";

            if (wifiInfo != null) {
                out.write(String.format(stringFormat, "거리(Km)", wifiInfo.getDISTANCE()));
                out.write(String.format(stringFormat, "관리번호", wifiInfo.getX_SWIFI_MGR_NO()));
                out.write(String.format(stringFormat, "자치구", wifiInfo.getX_SWIFI_WRDOFC()));
                out.write(String.format(stringFormat, "와이파이명", wifiInfo.getX_SWIFI_MAIN_NM()));
                out.write(String.format(stringFormat, "도로명주소", wifiInfo.getX_SWIFI_ADRES1()));
                out.write(String.format(stringFormat, "상세주소", wifiInfo.getX_SWIFI_ADRES2()));
                out.write(String.format(stringFormat, "설치위치(층)", wifiInfo.getX_SWIFI_INSTL_FLOOR()));
                out.write(String.format(stringFormat, "설치유형", wifiInfo.getX_SWIFI_INSTL_TY()));
                out.write(String.format(stringFormat, "설치기관", wifiInfo.getX_SWIFI_INSTL_MBY()));
                out.write(String.format(stringFormat, "서비스구분", wifiInfo.getX_SWIFI_SVC_SE()));
                out.write(String.format(stringFormat, "망종류", wifiInfo.getX_SWIFI_CMCWR()));
                out.write(String.format(stringFormat, "설치년도", wifiInfo.getX_SWIFI_CNSTC_YEAR()));
                out.write(String.format(stringFormat, "실내외구분", wifiInfo.getX_SWIFI_INOUT_DOOR()));
                out.write(String.format(stringFormat, "WIFI접속환경", wifiInfo.getX_SWIFI_REMARS3()));
                out.write(String.format(stringFormat, "X좌표", wifiInfo.getLAT()));
                out.write(String.format(stringFormat, "Y좌표", wifiInfo.getLNT()));
                out.write(String.format(stringFormat, "작업일자", wifiInfo.getWORK_DTTM()));
            }
        %>
    </table>
</body>
</html>
