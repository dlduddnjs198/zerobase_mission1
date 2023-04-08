package com.example.bookmarkgroup;

import com.example.SqlService;
import com.example.table.BookmarkGroup;
import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/loadbookmarkgroup"})
public class GetBookmarkGroupFromDB extends HttpServlet {

    @Override
    protected void doGet(@NotNull HttpServletRequest request, HttpServletResponse response) throws IOException {
        SqlService sqlService = SqlService.getInstance();

        try {
            List<BookmarkGroup> groupList =sqlService.sqlGetBookmarkGroup();
            Gson gson = new Gson();
            String json = gson.toJson(groupList);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("sqlGetBookmarkGroup Failed");
        }
    }
}