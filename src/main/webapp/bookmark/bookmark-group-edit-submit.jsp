<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    String id=request.getParameter("id");
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
    const id = '<%=id%>';
    const name = '<%=name%>';
    const seq = '<%=seq%>';
    editBookmarkGroup("북마크 그룹 정보를 수정하였습니다.", "잘못된 입력입니다. 다시 입력해주세요.", id, name, seq);
</script>

</body>
</html>
