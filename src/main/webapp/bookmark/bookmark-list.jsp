<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="/css/table.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/bookmark.js" charset="UTF-8"></script>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<h1>북마크 보기</h1>
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
<table id="bookmark_table" class="greentable">
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>와이파이명</th>
        <th>등록일자</th>
        <th>비고</th>
    </tr>
    <tr><td id="bookmarktext" class="initialtext" colspan="5">정보가 존재하지 않습니다.</td></tr>
    <script charset="UTF-8">loadBookmark("삭제");</script>
</table>
</body>
</html>
