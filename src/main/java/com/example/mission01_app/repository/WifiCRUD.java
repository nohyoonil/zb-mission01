package repository;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import domain.WifiInfo;
import okhttp3.Request;
import okhttp3.Response;
import properties.Properties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class WifiCRUD {

    private static Connection con = Properties.getConnection();
    public static WifiInfo getWifiInfoByMGR_NO(String MGR_NO) {
        WifiInfo wifiInfo = null;

        try {
            String sql = "select * from WIFI_INFO where X_SWIFI_MGR_NO=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, MGR_NO);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                wifiInfo = new WifiInfo();
                wifiInfo.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
                wifiInfo.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
                wifiInfo.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
                wifiInfo.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
                wifiInfo.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
                wifiInfo.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
                wifiInfo.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
                wifiInfo.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
                wifiInfo.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
                wifiInfo.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
                wifiInfo.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
                wifiInfo.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
                wifiInfo.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
                wifiInfo.setLAT(rs.getDouble("LAT"));
                wifiInfo.setLNT(rs.getDouble("LNT"));
                wifiInfo.setWORK_DTTM(rs.getString("WORK_DTTM"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wifiInfo;
    }

    public static List<WifiInfo> get20wifi(double lat, double lnt) {
        PriorityQueue<WifiInfo> wifiInfosQ = new PriorityQueue<>();

        try {
            String sql = "select * from WIFI_INFO where X_SWIFI_WRDOFC=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, getX_SWIFI_WRDOFC(lat, lnt));

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                double lat2 = rs.getDouble("LAT");
                double lnt2 = rs.getDouble("LNT");
                if (lat2 == 0 || lnt2 == 0) continue;

                WifiInfo wifiInfo = new WifiInfo();
                wifiInfo.setDISTANCE(getDistance(lat, lnt, lat2, lnt2));
                wifiInfo.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
                wifiInfo.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
                wifiInfo.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
                wifiInfo.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
                wifiInfo.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
                wifiInfo.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
                wifiInfo.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
                wifiInfo.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
                wifiInfo.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
                wifiInfo.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
                wifiInfo.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
                wifiInfo.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
                wifiInfo.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
                wifiInfo.setLAT(lat2);
                wifiInfo.setLNT(lnt2);
                wifiInfo.setWORK_DTTM(rs.getString("WORK_DTTM"));

                wifiInfosQ.add(wifiInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<WifiInfo> wifiInfos = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (wifiInfosQ.isEmpty()) break;
            wifiInfos.add(wifiInfosQ.poll());
        }
        LocationHistoryCRUD.insert(lat, lnt);

        return wifiInfos;
    }

    private static String getX_SWIFI_WRDOFC(double LAT, double LNT) {
        try {
            String url = String.format(Properties.COORDS_TO_ADDRESS_URL, LNT, LAT);
            Request request = new Request.Builder().url(url).get().build();
            Response response = Properties.getOkHttpClient().newCall(request).execute();

            if (response.isSuccessful()) {
                String json = response.body().string();
                JsonElement jsonElement = JsonParser.parseString(json);
                JsonObject jsonObject = jsonElement.getAsJsonObject().getAsJsonObject("response");

                if (!jsonObject.get("status").getAsString().equals("OK")) {
                    System.out.println("데이터 못찾음");
                } else {
                    JsonArray result = jsonObject.getAsJsonArray("result");
                    JsonElement structure = result.get(0).getAsJsonObject().getAsJsonObject("structure");
                    return structure.getAsJsonObject().get("level2").getAsString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    private static double getDistance(double lat1, double lnt1, double lat2, double lnt2) {
        double theta = lnt1 - lnt2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        return dist * 1.609344;
    }


    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
