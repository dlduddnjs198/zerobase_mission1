package com.example.history;

import com.example.SqlService;
import com.example.table.History;
import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/loadhistory"})
public class GetHistoryFromDB extends HttpServlet {

    @Override
    protected void doGet(@NotNull HttpServletRequest request, HttpServletResponse response) throws IOException {
        SqlService sqlService = SqlService.getInstance();

        try {
            List<History> historyList =sqlService.sqlGetHistory();
            Gson gson = new Gson();
            String json = gson.toJson(historyList);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("sqlGetHistory Failed");
        }
    }
}
