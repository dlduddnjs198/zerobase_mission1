<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="/css/table.css" rel="stylesheet" type="text/css">
    <title>와이파이 정보 구하기</title>
</head>
<body>
<h1>북마크 그룹 추가</h1>
<p>
    <a href="/">홈</a>
    <span> | </span>
    <a href="../history.jsp">위치 히스토리 목록</a>
    <span> | </span>
    <a href="../load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
    <span> | </span>
    <a href="bookmark-list.jsp">즐겨 찾기 보기</a>
    <span> | </span>
    <a href="bookmark-group.jsp">즐겨 찾기 그룹 관리</a>
</p>
<form action="bookmark-group-add-submit.jsp" method="post">
    <table id="bookmark_group_add_table" class="greentable">
        <tr><th>북마크 이름</th><td><input type="text" name="bookmark_name"/></td></tr>
        <tr><th>순서</th><td><input type="number" min="1" name="bookmark_number"/></td></tr>
        <tr><td style="text-align:center" colspan="2"><a href="bookmark-group.jsp">돌아가기</a> |
            <input type="submit" value="추가"/></td></tr>
    </table>
</form>
</body>
</html>
