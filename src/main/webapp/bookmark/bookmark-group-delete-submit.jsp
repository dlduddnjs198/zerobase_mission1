<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    String id=request.getParameter("id");
%>
<html>
<head>
    <script type="text/javascript" src="/js/bookmark.js" charset="UTF-8"></script>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<script>
    const id = '<%=id%>';
    delBookmarkGroup("북마크 그룹 정보를 삭제하였습니다.", id);
</script>

</body>
</html>
