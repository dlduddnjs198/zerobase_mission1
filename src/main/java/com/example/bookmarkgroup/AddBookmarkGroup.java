package com.example.bookmarkgroup;

import com.example.SqlService;
import org.jetbrains.annotations.NotNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/addbookmarkgroup"})
public class AddBookmarkGroup extends HttpServlet {
    @Override
    protected void doGet(@NotNull HttpServletRequest request, HttpServletResponse response) throws IOException {
        SqlService sqlService = SqlService.getInstance();
        String nameStr = request.getParameter("name");
        String seqStr = request.getParameter("seq");
        String dateStr = request.getParameter("date");
        if (nameStr == null || seqStr == null || dateStr == null) {
            // nameStr, seqStr, dateStr 파라미터 값이 없으면 처리하지 않음
            return;
        }
        int seq=Integer.parseInt(seqStr);

        boolean joongbok = sqlService.sqlInsertBookmarkGroup(nameStr, seq, dateStr);
        if(!joongbok){
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            return;
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
