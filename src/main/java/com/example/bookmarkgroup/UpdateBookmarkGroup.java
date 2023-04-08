package com.example.bookmarkgroup;

import com.example.SqlService;
import org.jetbrains.annotations.NotNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/editbookmarkgroup"})
public class UpdateBookmarkGroup extends HttpServlet {
    @Override
    protected void doGet(@NotNull HttpServletRequest request, HttpServletResponse response) throws IOException {
        SqlService sqlService = SqlService.getInstance();
        String idStr = request.getParameter("id");
        String nameStr = request.getParameter("name");
        String seqStr = request.getParameter("seq");
        String dateStr = request.getParameter("date");
        if (idStr == null || nameStr == null || seqStr == null || dateStr == null) {
            // idStr, nameStr, seqStr, dateStr 파라미터 값이 없으면 처리하지 않음
            return;
        }
        int seq=Integer.parseInt(seqStr);
        int id=Integer.parseInt(idStr);

        boolean joongbok = sqlService.sqlUpdateBookmarkGroup(id, nameStr, seq, dateStr);
        if(!joongbok){
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            return;
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }
}