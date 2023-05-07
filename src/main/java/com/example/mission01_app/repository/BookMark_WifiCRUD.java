package repository;

import domain.BookMark_WIfi;
import properties.Properties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookMark_WifiCRUD {
    static Connection con = Properties.getConnection();

    public static void create(String bookmarkName, String wifiName, int bookmarkID, String wifiID) {
        try {
            String sql = "insert into BOOKMARK_WIFI (BOOKMARK_NAME, WIFI_INFO_NAME, REGISTRATION_DATE, BOOKMARK_ID, WIFI_INFO_ID) values(?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bookmarkName);
            pstmt.setString(2, wifiName);
            pstmt.setString(3, LocalDateTime.now().format(Properties.getFormatter()));
            pstmt.setInt(4, bookmarkID);
            pstmt.setString(5, wifiID);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        try {
            String sql = "delete from BOOKMARK_WIFI where ID=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteByBookmarkID(int bookmarkId) {
        try {
            String sql = "delete from BOOKMARK_WIFI where BOOKMARK_ID=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, bookmarkId);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<BookMark_WIfi> getBookmarkWifis() {
        ArrayList<BookMark_WIfi> bookMarkWIfis = new ArrayList<>();
        try {
            String sql = "select * from BOOKMARK_WIFI";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                BookMark_WIfi bookMarkWIfi = new BookMark_WIfi();
                bookMarkWIfi.setId(rs.getInt("ID"));
                bookMarkWIfi.setBookmarkName(rs.getString("BOOKMARK_NAME"));
                bookMarkWIfi.setWifiName(rs.getString("WIFI_INFO_NAME"));
                bookMarkWIfi.setRegistrationData(rs.getString("REGISTRATION_DATE"));
                bookMarkWIfi.setBookmarkID(rs.getInt("BOOKMARK_ID"));
                bookMarkWIfi.setWifiID(rs.getString("WIFI_INFO_ID"));

                bookMarkWIfis.add(bookMarkWIfi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookMarkWIfis;
    }

    public static void updateByBookmarkUpdated(int bookmarkId, String updateName) {
        try {
            String sql = "update BOOKMARK_WIFI set BOOKMARK_NAME=? where BOOKMARK_ID=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, updateName);
            pstmt.setInt(2, bookmarkId);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BookMark_WIfi getBookmarkWifi(int id) {
        BookMark_WIfi bookMarkWIfi = new BookMark_WIfi();

        try {
            String sql = "select * from BOOKMARK_WIFI where ID=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                bookMarkWIfi.setId(id);
                bookMarkWIfi.setBookmarkName(rs.getString("BOOKMARK_NAME"));
                bookMarkWIfi.setWifiName(rs.getString("WIFI_INFO_NAME"));
                bookMarkWIfi.setRegistrationData(rs.getString("REGISTRATION_DATE"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookMarkWIfi;
    }
}
