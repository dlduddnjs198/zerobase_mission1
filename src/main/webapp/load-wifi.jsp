<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.example.GetWifiApi"%>

<%
    GetWifiApi api=new GetWifiApi();
    api.getWifiInfo();
%>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<h1>위치 히스토리 목록</h1>
<div style="text-align:center">
    <h1><%= api.getList_total_count() %>개의 WIFI정보를 정상적으로 저장하였습니다.</h1>
    <a href="/">홈으로 가기</a>
</div>

</body>
</html>
