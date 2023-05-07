package repository;

import domain.BookMark;
import properties.Properties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookMarkCRUD {
    static Connection con = Properties.getConnection();

    public static void create(String name, int sequence) {
        try {
            String sql = "insert into BOOKMARK (NAME, SEQUENCE, REGISTRATION_DATE) values(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, sequence);
            pstmt.setString(3, LocalDateTime.now().format(Properties.getFormatter()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(int id, String updateName, int updateSequence) {
        if (updateName == null || updateName.equals("")) return;
        BookMark_WifiCRUD.updateByBookmarkUpdated(id, updateName);

        try {
            String sql = "update BOOKMARK set NAME=?, SEQUENCE=?, UPDATE_DATE=? where ID=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, updateName);
            pstmt.setInt(2, updateSequence);
            pstmt.setString(3, LocalDateTime.now().format(Properties.getFormatter()));
            pstmt.setInt(4, id);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        BookMark_WifiCRUD.deleteByBookmarkID(id);

        try {
            String sql = "delete from BOOKMARK where ID=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BookMark getBookmark(int id) {
        BookMark bookMark = new BookMark();
        try {
            String sql = "select * from BOOKMARK where ID=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                bookMark.setId(id);
                bookMark.setName(rs.getString("NAME"));
                bookMark.setSequence(rs.getInt("SEQUENCE"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookMark;
    }

    public static List<BookMark> getBookmarks() {
        List<BookMark> bookMarks = new ArrayList<>();
        try {
            String sql = "select * from BOOKMARK";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                BookMark bookMark = new BookMark();
                bookMark.setId(rs.getInt("ID"));
                bookMark.setName(rs.getString("NAME"));
                bookMark.setSequence(rs.getInt("SEQUENCE"));
                bookMark.setRegistrationDate(rs.getString("REGISTRATION_DATE"));
                bookMark.setUpdateDate(rs.getString("UPDATE_DATE"));

                bookMarks.add(bookMark);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookMarks;
    }

    public static String getBookmarkName(int id) {
        try {
            String sql = "select NAME from BOOKMARK where ID=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getString("NAME");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
