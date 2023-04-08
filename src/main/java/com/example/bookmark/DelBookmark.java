package com.example.bookmark;

import com.example.SqlService;
import org.jetbrains.annotations.NotNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/delbookmark"})
public class DelBookmark extends HttpServlet {
    @Override
    protected void doGet(@NotNull HttpServletRequest request, HttpServletResponse response) throws IOException {
        SqlService sqlService = SqlService.getInstance();
        String idStr = request.getParameter("id");
        if (idStr == null) {
            return;
        }
        int id = Integer.parseInt(idStr);
        sqlService.sqlDeleteBookmark(id);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}