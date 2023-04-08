package com.example.bookmark;

import com.example.SqlService;
import org.jetbrains.annotations.NotNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/addbookmark"})
public class AddBookmark extends HttpServlet {
    @Override
    protected void doGet(@NotNull HttpServletRequest request, HttpServletResponse response) throws IOException {
        SqlService sqlService = SqlService.getInstance();
        String wifiIdStr = request.getParameter("wifiId");
        String groupIdStr = request.getParameter("groupId");
        String dateStr = request.getParameter("date");
        if (wifiIdStr == null || groupIdStr == null || dateStr == null) {
            // wifiIdStr, groupIdStr, dateStr 파라미터 값이 없으면 처리하지 않음
            return;
        }
        int groupId=Integer.parseInt(groupIdStr);

        sqlService.sqlInsertBookmark(wifiIdStr, groupId, dateStr);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}