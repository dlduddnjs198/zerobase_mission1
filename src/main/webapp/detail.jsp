<%@ page import="com.example.SqlService" %>
<%@ page import="com.example.table.Wifi" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String mgrno = request.getParameter("mgrNo");
    double distance=Double.parseDouble(request.getParameter("dist"));
    SqlService sqlService=SqlService.getInstance();
    Wifi wifi=sqlService.sqlGetWifiById(mgrno);
    wifi.setDistance_value(distance);
%>
<html>
<head>
    <link href="/css/table.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/bookmark.js" charset="UTF-8"></script>
    <title>와이파이 정보 구하기</title>
<%-- sp에서 바로 sql문으로 해보기--%>
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
<form action="bookmark/bookmark-add-submit.jsp" method="get">
    <input style="display : none" type="hidden" name="wifi_id" value="<%=wifi.getMgr_no()%>"/>
    <select name="bookmark_name" id="bookmark_group_list">
        <option value="select" disabled selected>북마크 그룹 이름 선택</option>
        <script>loadBookmarkGroupInHistory();</script>
    </select>
    <input type="submit" value="즐겨찾기 추가하기" />
</form>
<table id="historytable" class="greentable">
    <tr><th>거리(Km)</th><td><%=wifi.getDistance_value()%></td></tr>
    <tr><th>관리번호</th><td><%=wifi.getMgr_no()%></td></tr>
    <tr><th>자치구</th><td><%=wifi.getWrdofc()%></td></tr>
    <tr><th>와이파이명</th><td><a href=""><%=wifi.getMain_name()%></a></td></tr>
    <tr><th>도로명주소</th><td><%=wifi.getAddress1()%></td></tr>
    <tr><th>상세주소</th><td><%=wifi.getAddress2()%></td></tr>
    <tr><th>설치위치(층)</th><td><%=wifi.getInstall_floor()%></td></tr>
    <tr><th>설치유형</th><td><%=wifi.getInstall_type()%></td></tr>
    <tr><th>설치기관</th><td><%=wifi.getInstall_mby()%></td></tr>
    <tr><th>서비스구분</th><td><%=wifi.getService_se()%></td></tr>
    <tr><th>망종류</th><td><%=wifi.getCmcwr()%></td></tr>
    <tr><th>설치년도</th><td><%=wifi.getCnstc_year()%></td></tr>
    <tr><th>실내외구분</th><td><%=wifi.getInout_door()%></td></tr>
    <tr><th>WIFI접속환경</th><td><%=wifi.getRemars3()%></td></tr>
    <tr><th>X좌표</th><td><%=wifi.getLatitude()%></td></tr>
    <tr><th>Y좌표</th><td><%=wifi.getLongitude()%></td></tr>
    <tr><th>작업일자</th><td><%=wifi.getWork_date()%></td></tr>
</table>
</body>
</html>
