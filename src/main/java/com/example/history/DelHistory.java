package com.example.history;

import com.example.SqlService;
import org.jetbrains.annotations.NotNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/delhistory"})
public class DelHistory extends HttpServlet {
    @Override
    protected void doGet(@NotNull HttpServletRequest request, HttpServletResponse response) throws IOException {
        SqlService sqlService = SqlService.getInstance();
        String idStr = request.getParameter("id");
        if (idStr == null) {
            // id 파라미터 값이 없으면 처리하지 않음
            return;
        }
        int id = Integer.parseInt(idStr);
        sqlService.sqlDeleteHistory(id);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
