package com.example;

import com.example.table.Bookmark;
import com.example.table.BookmarkGroup;
import com.example.table.History;
import com.example.table.Wifi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlService {
    private String url="jdbc:sqlite:./wifidata.db";
    private Connection conn=null;
    // 싱글턴 사용(아직 뭔지 잘 모름)
    private static SqlService instance = null;

    private SqlService(){
        try{
            Class.forName("org.sqlite.JDBC");
            conn= DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Constructor Error");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Constructor Error2");
            e.printStackTrace();
        }
    }

    // getInstance() 메서드를 사용하여 SqlService의 유일한 인스턴스를 반환합니다.
    public static SqlService getInstance() {
        if (instance == null) {
            instance = new SqlService();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if(conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(url);
        }
        return conn;
    }

    public boolean tableNameCheck(String name){
        String query = "SELECT name FROM sqlite_master WHERE type='table' AND name='"+name+"'";
        try{
            Statement stmt=getConnection().createStatement();
            ResultSet rs=stmt.executeQuery(query);
            return rs.next();
        } catch (SQLException e) {
            System.out.println("tableNameCheck Error | name = "+name);
            e.printStackTrace();
        }
        return false;
    }

    public void sqlCreateWifi() {
        String query="CREATE TABLE IF NOT EXISTS wifi ("+
                "X_SWIFI_MGR_NO TEXT UNIQUE,"+
                "X_SWIFI_WRDOFC TEXT,"+
                "X_SWIFI_MAIN_NM TEXT,"+
                "X_SWIFI_ADRES1 TEXT,"+
                "X_SWIFI_ADRES2 TEXT,"+
                "X_SWIFI_INSTL_FLOOR TEXT,"+
                "X_SWIFI_INSTL_TY TEXT,"+
                "X_SWIFI_INSTL_MBY TEXT,"+
                "X_SWIFI_SVC_SE TEXT,"+
                "X_SWIFI_CMCWR TEXT,"+
                "X_SWIFI_CNSTC_YEAR INTEGER,"+
                "X_SWIFI_INOUT_DOOR TEXT,"+
                "X_SWIFI_REMARS3 TEXT,"+
                "LAT REAL,"+
                "LNT REAL,"+
                "WORK_DTTM TEXT);";

        try {
            Statement stmt = getConnection().createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            System.out.println("sqlCreateWifi Error");
            e.printStackTrace();
        }

    }
    public void sqlUpsertWifi(Wifi wifi) {
        String query="INSERT OR REPLACE INTO wifi(X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM," +
                " X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, " +
                "X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, " +
                "X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) "+
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, wifi.getMgr_no());
            pstmt.setString(2, wifi.getWrdofc());
            pstmt.setString(3, wifi.getMain_name());
            pstmt.setString(4, wifi.getAddress1());
            pstmt.setString(5, wifi.getAddress2());
            pstmt.setString(6, wifi.getInstall_floor());
            pstmt.setString(7, wifi.getInstall_type());
            pstmt.setString(8, wifi.getInstall_mby());
            pstmt.setString(9, wifi.getService_se());
            pstmt.setString(10, wifi.getCmcwr());
            pstmt.setString(11, wifi.getCnstc_year());
            pstmt.setString(12, wifi.getInout_door());
            pstmt.setString(13, wifi.getRemars3());
            pstmt.setDouble(14, wifi.getLatitude());
            pstmt.setDouble(15, wifi.getLongitude());
            pstmt.setString(16, wifi.getWork_date());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sqlUpsertWifi Error");
            e.printStackTrace();
        }
    }
    public List<Wifi> sqlGetWifi(double mylat, double mylnt, double limit) throws SQLException {
        String query="SELECT *, (6371 * acos(cos(pi()*?/180) * cos(pi()*LAT/180) * cos(pi()*LNT/180 - pi()*?/180)" +
                " + sin(pi()*?/180) * sin(pi()*LAT/180))) AS distance_value "
                + "FROM wifi "
                + "GROUP BY X_SWIFI_MGR_NO "
                + "HAVING distance_value <= ? ORDER BY distance_value ASC LIMIT 20";
        try{
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setDouble(1, mylat);
            pstmt.setDouble(2, mylnt);
            pstmt.setDouble(3, mylat);
            pstmt.setDouble(4, limit);
            ResultSet rs = pstmt.executeQuery();
            List<Wifi> wifiList = new ArrayList<Wifi>();
            while(rs.next()) {
                Wifi wifi = new Wifi();
                wifi.setDistance_value(rs.getDouble("distance_value"));
                wifi.setMgr_no(rs.getString("X_SWIFI_MGR_NO"));
                wifi.setWrdofc(rs.getString("X_SWIFI_WRDOFC"));
                wifi.setMain_name(rs.getString("X_SWIFI_MAIN_NM"));
                wifi.setAddress1(rs.getString("X_SWIFI_ADRES1"));
                wifi.setAddress2(rs.getString("X_SWIFI_ADRES2"));
                wifi.setInstall_floor(rs.getString("X_SWIFI_INSTL_FLOOR"));
                wifi.setInstall_type(rs.getString("X_SWIFI_INSTL_TY"));
                wifi.setInstall_mby(rs.getString("X_SWIFI_INSTL_MBY"));
                wifi.setService_se(rs.getString("X_SWIFI_SVC_SE"));
                wifi.setCmcwr(rs.getString("X_SWIFI_CMCWR"));
                wifi.setCnstc_year(rs.getString("X_SWIFI_CNSTC_YEAR"));
                wifi.setInout_door(rs.getString("X_SWIFI_INOUT_DOOR"));
                wifi.setRemars3(rs.getString("X_SWIFI_REMARS3"));
                wifi.setLatitude(rs.getDouble("LAT"));
                wifi.setLongitude(rs.getDouble("LNT"));
                wifi.setWork_date(rs.getString("WORK_DTTM"));
                wifiList.add(wifi);
            }
            return wifiList;
        } catch (SQLException e) {
            System.out.println("sqlWifiSelect Error");
            e.printStackTrace();
        }
        return null;
    }

    public Wifi sqlGetWifiById(String mgrno) throws SQLException {
        String query="SELECT * FROM wifi WHERE X_SWIFI_MGR_NO=?";
        try{
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, mgrno);
            ResultSet rs = pstmt.executeQuery();
            Wifi wifi=new Wifi();
            wifi.setMgr_no(rs.getString("X_SWIFI_MGR_NO"));
            wifi.setWrdofc(rs.getString("X_SWIFI_WRDOFC"));
            wifi.setMain_name(rs.getString("X_SWIFI_MAIN_NM"));
            wifi.setAddress1(rs.getString("X_SWIFI_ADRES1"));
            wifi.setAddress2(rs.getString("X_SWIFI_ADRES2"));
            wifi.setInstall_floor(rs.getString("X_SWIFI_INSTL_FLOOR"));
            wifi.setInstall_type(rs.getString("X_SWIFI_INSTL_TY"));
            wifi.setInstall_mby(rs.getString("X_SWIFI_INSTL_MBY"));
            wifi.setService_se(rs.getString("X_SWIFI_SVC_SE"));
            wifi.setCmcwr(rs.getString("X_SWIFI_CMCWR"));
            wifi.setCnstc_year(rs.getString("X_SWIFI_CNSTC_YEAR"));
            wifi.setInout_door(rs.getString("X_SWIFI_INOUT_DOOR"));
            wifi.setRemars3(rs.getString("X_SWIFI_REMARS3"));
            wifi.setLatitude(rs.getDouble("LAT"));
            wifi.setLongitude(rs.getDouble("LNT"));
            wifi.setWork_date(rs.getString("WORK_DTTM"));
            return wifi;
        } catch (SQLException e) {
            System.out.println("sqlWifiSelectById Error");
            e.printStackTrace();
        }
        return null;
    }

    public void sqlCreateHistory() {
        String query="CREATE TABLE IF NOT EXISTS history ("+
                "HISTORY_ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "LOOK_LAT REAL,"+
                "LOOK_LNT REAL,"+
                "LOOK_DATE TEXT);";
        try {
            Statement stmt = getConnection().createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            System.out.println("sqlHistoryCreate Error");
            e.printStackTrace();
        }
    }

    public void sqlInsertHistory(double mylat, double mylnt, String time) {
        String query="INSERT INTO history(LOOK_LAT, LOOK_LNT, LOOK_DATE) "+
                "VALUES(?, ?, ?)";
        try{
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setDouble(1, mylat);
            pstmt.setDouble(2, mylnt);
            pstmt.setString(3, time);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sqlHistoryInsert Error");
            e.printStackTrace();
        }
    }

    public List<History> sqlGetHistory() throws SQLException {
        String query = "SELECT * FROM history";
        try{
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            List<History> historyList = new ArrayList<History>();
            while(rs.next()) {
                History history = new History();
                history.setHistory_id(rs.getInt("HISTORY_ID"));
                history.setLatitude(rs.getDouble("LOOK_LAT"));
                history.setLongitude(rs.getDouble("LOOK_LNT"));
                history.setDate(rs.getString("LOOK_DATE"));
                historyList.add(history);
            }
            return historyList;
        } catch (SQLException e) {
            System.out.println("sqlHistorySelect Error");
            e.printStackTrace();
        }
        return null;
    }

    public void sqlDeleteHistory(int id){
        String query = "DELETE FROM history WHERE HISTORY_ID = ?;";
        try{
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sqlHistoryDelete Error");
            e.printStackTrace();
        }
    }

    public void sqlCreateBookmarkGroup(){
        String query="CREATE TABLE IF NOT EXISTS bookmark_group ("+
                "GROUP_ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "BOOKMARK_NAME TEXT NOT NULL,"+
                "SEQUENCE INTEGER NOT NULL,"+
                "CREATE_DATE TEXT NOT NULL,"+
                "MODIFY_DATE TEXT);";
        try {
            Statement stmt = getConnection().createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            System.out.println("sqlBookmarkGroupCreate Error");
            e.printStackTrace();
        }
    }

    public List<BookmarkGroup> sqlGetBookmarkGroup() throws SQLException {
        String query = "SELECT * FROM bookmark_group";
        try{
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            List<BookmarkGroup> bookmarkGroupList = new ArrayList<BookmarkGroup>();
            while(rs.next()) {
                BookmarkGroup bookmarkGroup = new BookmarkGroup();
                bookmarkGroup.setGroup_id(rs.getInt("GROUP_ID"));
                bookmarkGroup.setBookmark_name(rs.getString("BOOKMARK_NAME"));
                bookmarkGroup.setSequence(rs.getInt("SEQUENCE"));
                bookmarkGroup.setCreate_date(rs.getString("CREATE_DATE"));
                bookmarkGroup.setModify_date(rs.getString("MODIFY_DATE"));
                bookmarkGroupList.add(bookmarkGroup);
            }
            return bookmarkGroupList;
        } catch (SQLException e) {
            System.out.println("sqlBookmarkGroupSelect Error");
            e.printStackTrace();
        }
        return null;
    }

    public BookmarkGroup sqlGetBookmarkGroupById(int group_id) throws SQLException {
        String query="SELECT * FROM bookmark_group WHERE GROUP_ID=?";
        try{
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setInt(1, group_id);
            ResultSet rs = pstmt.executeQuery();
            BookmarkGroup bookmarkGroup=new BookmarkGroup();
            bookmarkGroup.setGroup_id(rs.getInt("GROUP_ID"));
            bookmarkGroup.setBookmark_name(rs.getString("BOOKMARK_NAME"));
            bookmarkGroup.setSequence(rs.getInt("SEQUENCE"));
            bookmarkGroup.setCreate_date(rs.getString("CREATE_DATE"));
            bookmarkGroup.setModify_date(rs.getString("MODIFY_DATE"));
            return bookmarkGroup;
        } catch (SQLException e) {
            System.out.println("sqlBookmarkGroupSelectById Error");
            e.printStackTrace();
        }
        return null;
    }

    public boolean sqlInsertBookmarkGroup(String name, int sequence, String create_date) {
        String checkQuery = "SELECT COUNT(*) FROM bookmark_group WHERE SEQUENCE=?";
        String insertQuery = "INSERT INTO bookmark_group(BOOKMARK_NAME, SEQUENCE, CREATE_DATE) VALUES(?, ?, ?)";
        try{
            PreparedStatement checkStmt = getConnection().prepareStatement(checkQuery);
            checkStmt.setInt(1, sequence);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count > 0) {
                return false;
            }

            PreparedStatement insertStmt = getConnection().prepareStatement(insertQuery);
            insertStmt.setString(1, name);
            insertStmt.setInt(2, sequence);
            insertStmt.setString(3, create_date);

            insertStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("sqlBookmarkGroupInsert Error");
            e.printStackTrace();
        }
        return false;
    }

    public boolean sqlUpdateBookmarkGroup(int id, String name, int sequence, String modify_date) {
        String checkQuery = "SELECT COUNT(*) FROM bookmark_group WHERE SEQUENCE=?";
        String updateQuery="UPDATE bookmark_group " +
                "SET BOOKMARK_NAME = ?, SEQUENCE = ?, MODIFY_DATE = ? "+
                "WHERE GROUP_ID= ?;";
        String updateBookmarkQuery="UPDATE bookmark SET BOOKMARK_NAME=? WHERE GROUP_ID=?;";
        try{
            PreparedStatement checkStmt = getConnection().prepareStatement(checkQuery);
            checkStmt.setInt(1, sequence);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count > 0) {
                // 현재 수정하려는 값과 같은 SEQUENCE 값이 자신의 것인지 체크합니다.
                String selectQuery = "SELECT SEQUENCE FROM bookmark_group WHERE GROUP_ID = ?";
                PreparedStatement selectStmt = getConnection().prepareStatement(selectQuery);
                selectStmt.setInt(1, id);
                rs = selectStmt.executeQuery();
                rs.next();
                int currentSequence = rs.getInt(1);
                if (currentSequence != sequence) {
                    // 예외를 발생시키거나, false를 리턴합니다.
                    //throw new Exception("Duplicate SEQUENCE value");
                    return false;
                }
            }

            PreparedStatement insertStmt = getConnection().prepareStatement(updateQuery);
            insertStmt.setString(1, name);
            insertStmt.setInt(2, sequence);
            insertStmt.setString(3, modify_date);
            insertStmt.setInt(4, id);

            insertStmt.executeUpdate();

            PreparedStatement bkStmt = getConnection().prepareStatement(updateBookmarkQuery);
            bkStmt.setString(1, name);
            bkStmt.setInt(2, id);

            bkStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("sqlBookmarkGroupInsert Error");
            e.printStackTrace();
        }
        return false;
    }

    public void sqlDeleteBookmarkGroup(int id){
        String query = "DELETE FROM bookmark_group WHERE GROUP_ID = ?;";
        try{
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sqlBookmarkGroupDelete Error");
            e.printStackTrace();
        }
    }

    public void sqlCreateBookmark(){
        String query="CREATE TABLE IF NOT EXISTS bookmark (" +
                "BOOKMARK_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "BOOKMARK_NAME TEXT NOT NULL," +
                "X_SWIFI_MAIN_NM TEXT NOT NULL," +
                "CREATE_DATE TEXT NOT NULL," +
                "X_SWIFI_MGR_NO TEXT NOT NULL," +
                "GROUP_ID INTEGER NOT NULL," +
                "FOREIGN KEY (X_SWIFI_MGR_NO) REFERENCES wifi (X_SWIFI_MGR_NO) ON DELETE CASCADE," +
                "FOREIGN KEY (GROUP_ID) REFERENCES bookmark_group (GROUP_ID) ON DELETE CASCADE);";
        try {
            Statement stmt = getConnection().createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            System.out.println("sqlBookmarkCreate Error");
            e.printStackTrace();
        }
    }

    public boolean sqlInsertBookmark(String wifiId, int groupId, String create_date) {
        String insertQuery = "INSERT INTO bookmark(BOOKMARK_NAME, X_SWIFI_MAIN_NM, CREATE_DATE, X_SWIFI_MGR_NO, GROUP_ID) " +
                "VALUES((SELECT BOOKMARK_NAME FROM bookmark_group WHERE GROUP_ID=?), " +
                "(SELECT X_SWIFI_MAIN_NM FROM wifi WHERE X_SWIFI_MGR_NO=?), ?, ?, ?)";
        try{

            PreparedStatement insertStmt = getConnection().prepareStatement(insertQuery);
            insertStmt.setInt(1, groupId);
            insertStmt.setString(2, wifiId);
            insertStmt.setString(3, create_date);
            insertStmt.setString(4, wifiId);
            insertStmt.setInt(5, groupId);

            insertStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("sqlBookmarkInsert Error");
            e.printStackTrace();
        }
        return false;
    }

    public List<Bookmark> sqlGetBookmark() throws SQLException {
        String query = "SELECT * FROM bookmark";
        try{
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            List<Bookmark> bookmarkList = new ArrayList<Bookmark>();
            while(rs.next()) {
                Bookmark bookmark = new Bookmark();
                bookmark.setBookmark_id(rs.getInt("BOOKMARK_ID"));
                bookmark.setBookmark_name(rs.getString("BOOKMARK_NAME"));
                bookmark.setWifi_name(rs.getString("X_SWIFI_MAIN_NM"));
                bookmark.setCreate_date(rs.getString("CREATE_DATE"));
                bookmark.setWifi_key(rs.getString("X_SWIFI_MGR_NO"));
                bookmark.setGroup_key(rs.getInt("GROUP_ID"));
                bookmarkList.add(bookmark);
            }
            return bookmarkList;
        } catch (SQLException e) {
            System.out.println("sqlBookmarkSelect Error");
            e.printStackTrace();
        }
        return null;
    }

    public Bookmark sqlGetBookmarkById(int bookmark_id) throws SQLException {
        String query="SELECT * FROM bookmark WHERE BOOKMARK_ID=?";
        try{
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setInt(1, bookmark_id);
            ResultSet rs = pstmt.executeQuery();
            Bookmark bookmark=new Bookmark();
            bookmark.setBookmark_id(rs.getInt("BOOKMARK_ID"));
            bookmark.setBookmark_name(rs.getString("BOOKMARK_NAME"));
            bookmark.setWifi_name(rs.getString("X_SWIFI_MAIN_NM"));
            bookmark.setCreate_date(rs.getString("CREATE_DATE"));
            bookmark.setWifi_key(rs.getString("X_SWIFI_MGR_NO"));
            bookmark.setGroup_key(rs.getInt("GROUP_ID"));
            return bookmark;
        } catch (SQLException e) {
            System.out.println("sqlBookmarkSelectById Error");
            e.printStackTrace();
        }
        return null;
    }

    public void sqlDeleteBookmark(int id){
        String query = "DELETE FROM bookmark WHERE BOOKMARK_ID = ?;";
        try{
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sqlBookmarkDelete Error");
            e.printStackTrace();
        }
    }

    public void sqlDeleteTable(String name){
        String query = "DROP TABLE IF EXISTS "+name+";";
        try{
            Statement stmt = getConnection().createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            System.out.println("sqlTableDelete Error");
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try{
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("closeConnection Error");
            e.printStackTrace();
        }
    }
}
