<%@ page import="com.example.SqlService" %>
<%@ page import="com.example.table.BookmarkGroup" %>
<%@ page import="java.sql.SQLException" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String groupId = request.getParameter("id");
    SqlService sqlService=SqlService.getInstance();
    BookmarkGroup bookmarkGroup= null;
    try {
        bookmarkGroup = sqlService.sqlGetBookmarkGroupById(Integer.parseInt(groupId));
    } catch (SQLException e) {
        System.out.println("sqlGetBookmarkGroupById failed");
        e.printStackTrace();
    }
%>
<html>
<head>
    <meta charset="UTF-8">
    <link href="/css/table.css" rel="stylesheet" type="text/css">
    <title>와이파이 정보 구하기</title>
</head>
<body>
<h1>북마크 그룹 삭제</h1>
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
<p>북마크 그룹을 삭제하시겠습니까?</p>
<form action="bookmark-group-delete-submit.jsp" method="post">
    <input style="display : none" type="hidden" name="id" value="<%=groupId%>"/>
    <table id="bookmark_group_delete_table" class="greentable">
        <tr><th>북마크 이름</th><td><input type="text" name="bookmark_name" value="<%=bookmarkGroup.getBookmark_name()%>" disabled/></td></tr>
        <tr><th>순서</th><td><input type="number" name="bookmark_number" value="<%=bookmarkGroup.getSequence()%>" disabled/></td></tr>
        <tr><td style="text-align:center" colspan="2"><a href="bookmark-group.jsp">돌아가기</a> |
            <input type="submit" value="삭제" /></td></tr>
    </table>
</form>
</body>
</html>
