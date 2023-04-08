package com.example;

import com.example.table.Wifi;
import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/wifi"})
public class GetWifiFromDB extends HttpServlet {

    @Override
    protected void doGet(@NotNull HttpServletRequest request, HttpServletResponse response) throws IOException {
        SqlService sqlService = SqlService.getInstance();
        String latStr = request.getParameter("lat");
        String lntStr = request.getParameter("lnt");
        if (latStr == null || lntStr == null) {
            // lat, lnt 파라미터 값이 없으면 처리하지 않음
            return;
        }
        double mylat=Double.parseDouble(latStr);
        double mylnt=Double.parseDouble(lntStr);
        double limit=5.0;

        try {
            List<Wifi> wifiList =sqlService.sqlGetWifi(mylat, mylnt, limit);
            Gson gson = new Gson();
            String json = gson.toJson(wifiList);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("sqlGetWifi Failed");
        }
    }
}
