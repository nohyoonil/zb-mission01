<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<script>
    function onGeoOkay(position) {
        console.log(position);
        document.coords_form.LAT.value=position.coords.latitude;
        document.coords_form.LNT.value=position.coords.longitude;

    }

    function onGeoError() {
        alert("I can't find you. No weather for you.");
    }

    function getCoords() {
        navigator.geolocation.getCurrentPosition(onGeoOkay, onGeoError);
    }
</script>
<head>
    <style>
        #wifis {
            /*font-family: Arial, Helvetica, sans-serif;*/
            border-collapse: collapse;
            width: 100%;
        }
        #wifis td, #wifis th {
            border: 1px solid #ddd;
            padding: 8px;
        }
        #wifis tr:nth-child(even){background-color: #f2f2f2;}

        #wifis tr:hover {background-color: #ddd;}

        #wifis th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: center;
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>
<body>
<h1>와이파이 정보 구하기</h1>
<a href="index.jsp">홈</a> | <a href="/jsp/history.jsp">위치 히스토리 목록</a> | <a href="">Open API 와이파이 정보 가져오기</a> | <a href="jsp/bookmark-wifi.jsp">북마크 보기</a> | <a href="jsp/bookmark-group.jsp">북마크 그룹 관리</a>
<form method="get" name="coords_form" action="jsp/get-20-wifi.jsp">
    <label for="LAT">LAT: </label><input type="text" id="LAT" name="LAT" value=""> ,
    <label for="LNT">LNT: </label><input type="text" id="LNT" name="LNT" value="">
    <input type="button" onclick= getCoords(); value="내 위치 가져오기">
    <input type="button" onclick=submit(); value="근처 WIFI 정보 보기">
</form>
<table id="wifis">
    <tr>
        <th>거리(Km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
</table>
</body>
</html>