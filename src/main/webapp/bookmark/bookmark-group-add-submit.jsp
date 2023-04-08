<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    String name=request.getParameter("bookmark_name");
    String seq=request.getParameter("bookmark_number");
%>
<html>
<head>
    <script type="text/javascript" src="/js/bookmark.js" charset="UTF-8"></script>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<script>
    const name = '<%=name%>';
    const seq = '<%=seq%>';
    addBookmarkGroup("북마크 그룹 정보를 추가하였습니다.", "잘못된 입력입니다. 다시 입력해주세요.", name, seq);
</script>
</body>
</html>
