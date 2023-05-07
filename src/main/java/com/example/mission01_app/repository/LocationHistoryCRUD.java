package repository;

import domain.LocationHistory;
import properties.Properties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LocationHistoryCRUD {

    static Connection con = Properties.getConnection();

    public static void insert(double lat, double lnt) {
        try {
            String sql = "insert into LOCATION_HISTORY (LAT, LNT, INQUIRY_DATE) values(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setDouble(1, lat);
            pstmt.setDouble(2, lnt);
            pstmt.setString(3, LocalDateTime.now().format(Properties.getFormatter()));

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        try {
            String sql = "delete from LOCATION_HISTORY where ID=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<LocationHistory> getLocationHistories() {
        ArrayList<LocationHistory> locationHistories = new ArrayList<>();

        try {
            String sql = "select * from LOCATION_HISTORY";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                LocationHistory locationHistory = new LocationHistory();
                locationHistory.setId(rs.getInt("ID"));
                locationHistory.setLAT(rs.getDouble("LAT"));
                locationHistory.setLNT(rs.getDouble("LNT"));
                locationHistory.setInquiryDate(rs.getString("INQUIRY_DATE"));

                locationHistories.add(locationHistory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return locationHistories;
    }
}
