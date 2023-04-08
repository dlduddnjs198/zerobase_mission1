<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<html>
<head>
    <link href="/css/table.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/getMyLocation.js"></script>
    <script type="text/javascript" src="/js/getWifiData.js"></script>
    <script type="text/javascript" src="/js/updateHistory.js"></script>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<h1>와이파이 정보 구하기</h1>
<p>
    <a href="/">홈</a>
    <span> | </span>
    <a href="history.jsp">위치 히스토리 목록</a>
    <span> | </span>
    <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
    <span> | </span>
    <a href="bookmark/bookmark-list.jsp">즐겨 찾기 보기</a>
    <span> | </span>
    <a href="bookmark/bookmark-group.jsp">즐겨 찾기 그룹 관리</a>
</p>
<p>
    <label for="lat">LAT: </label>
    <input type="text" id="lat" name="lat" value="0.0" >
    <label for="lnt">LNT: </label>
    <input type="text" id="lnt" name="lnt" value="0.0" >
    <input type="button" id="myLocation" onclick="location_click()" value="내 위치 가져오기" >
    <input type="button" id="getWifiInfo" onclick="wifiInfo_click()" value="근처 WIFI 정보 보기" >
</p>
<table id="indextable" class="greentable">
    <tr>
        <th>거리(Km)</th><th>관리번호</th><th>자치구</th><th>와이파이명</th><th>도로명주소</th><th>상세주소</th>
        <th>설치위치(층)</th><th>설치유형</th><th>설치기관</th><th>서비스구분</th><th>망종류</th><th>설치년도</th>
        <th>실내외구분</th><th>WIFI접속환경</th><th>X좌표</th><th>Y좌표</th><th>작업일자</th>
    </tr>
    <tr><td id="indextext" class="initialtext" colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td></tr>

</table>

</body>
</html>
