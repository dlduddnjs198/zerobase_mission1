<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    String wifiId=request.getParameter("wifi_id");
    String groupId=request.getParameter("bookmark_name");
%>
<html>
<head>
    <script type="text/javascript" src="/js/bookmark.js" charset="UTF-8"></script>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<script>
    const wifiId='<%=wifiId%>';
    const groupId='<%=groupId%>';
    addBookmark("북마크 정보를 추가하였습니다.", wifiId, groupId);
</script>
</body>
</html>