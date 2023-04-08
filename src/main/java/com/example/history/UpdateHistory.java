package com.example.history;

import com.example.SqlService;
import org.jetbrains.annotations.NotNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/history"})
public class UpdateHistory extends HttpServlet {
    @Override
    protected void doGet(@NotNull HttpServletRequest request, HttpServletResponse response) throws IOException {
        SqlService sqlService = SqlService.getInstance();
        String latStr = request.getParameter("lat");
        String lntStr = request.getParameter("lnt");
        String dateStr = request.getParameter("time");
        if (latStr == null || lntStr == null || dateStr == null) {
            // lat, lnt, time 파라미터 값이 없으면 처리하지 않음
            return;
        }
        double mylat=Double.parseDouble(latStr);
        double mylnt=Double.parseDouble(lntStr);

        sqlService.sqlInsertHistory(mylat, mylnt, dateStr);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}

