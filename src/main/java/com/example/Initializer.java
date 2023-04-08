package com.example;

import lombok.Getter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebListener
@Getter
public class Initializer implements ServletContextListener {

    // 테이블 없으면 만들어주기
    public void contextInitialized(ServletContextEvent sce) {
        SqlService sqlService = SqlService.getInstance();
        Statement stmt= null;
        try {
            stmt = sqlService.getConnection().createStatement();
            stmt.execute("PRAGMA foreign_keys = ON;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(!sqlService.tableNameCheck("wifi")){
            sqlService.sqlCreateWifi();
        }
        if(!sqlService.tableNameCheck("history")){
            sqlService.sqlCreateHistory();
        }
        if(!sqlService.tableNameCheck("bookmark_group")){
            sqlService.sqlCreateBookmarkGroup();
        }
        if(!sqlService.tableNameCheck("bookmark")){
            sqlService.sqlCreateBookmark();
        }
    }
}
