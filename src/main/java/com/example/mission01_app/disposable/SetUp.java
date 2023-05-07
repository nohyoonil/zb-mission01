package disposable;

import com.google.gson.*;
import gson.GsonContainer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import domain.WifiInfo;
import properties.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SetUp {

    private final String PUBLIC_WIFI_URL = "http://openapi.seoul.go.kr:8088/4a5772766b61676c37306b7a7a6f70/json/TbPublicWifiInfo/%d/%d/";
    private final Gson gson = GsonContainer.getGson();
    private Connection con = Properties.getConnection();

    private int getTotalCnt(){
        OkHttpClient client = new OkHttpClient();
        int totalCnt = -1;
        try {
            Request request = new Request.Builder().url(String.format(PUBLIC_WIFI_URL, 1, 1)).get().build();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String json = response.body().string();
                JsonElement jsonElement = JsonParser.parseString(json);
                JsonObject tbPublicWifiInfo = jsonElement.getAsJsonObject().getAsJsonObject("TbPublicWifiInfo");
                totalCnt = tbPublicWifiInfo.get("list_total_count").getAsInt();

                return totalCnt;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalCnt;
    }

    private void setPublicWifiDataToMyDB() throws ClassNotFoundException, SQLException {
        int totalCnt = getTotalCnt();
        OkHttpClient client = new OkHttpClient();
        Class.forName("org.sqlite.JDBC");

        int startPage = 1;
        while (startPage <= totalCnt) {
            try {
                Request request = new Request.Builder().url(String.format(PUBLIC_WIFI_URL, startPage, startPage + 999)).get().build();
                startPage += 1000;
                Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    String json = response.body().string();
                    JsonElement jsonElement = JsonParser.parseString(json);
                    JsonObject tbPublicWifiInfo = jsonElement.getAsJsonObject().getAsJsonObject("TbPublicWifiInfo");
                    JsonArray wifiInfoAsJsonArray = tbPublicWifiInfo.getAsJsonArray("row");

                    for (JsonElement element : wifiInfoAsJsonArray) {
                        WifiInfo wifiInfo = gson.fromJson(element.toString(), WifiInfo.class);
                        insert(wifiInfo);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void insert(WifiInfo wifiInfo) {
        try {
            String sql = "insert into WIFI_INFO values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, wifiInfo.getX_SWIFI_MGR_NO());
            pstmt.setString(2, wifiInfo.getX_SWIFI_WRDOFC());
            pstmt.setString(3, wifiInfo.getX_SWIFI_MAIN_NM());
            pstmt.setString(4, wifiInfo.getX_SWIFI_ADRES1());
            pstmt.setString(5, wifiInfo.getX_SWIFI_ADRES2());
            pstmt.setString(6, wifiInfo.getX_SWIFI_INSTL_FLOOR());
            pstmt.setString(7, wifiInfo.getX_SWIFI_INSTL_TY());
            pstmt.setString(8, wifiInfo.getX_SWIFI_INSTL_MBY());
            pstmt.setString(9, wifiInfo.getX_SWIFI_SVC_SE());
            pstmt.setString(10, wifiInfo.getX_SWIFI_CMCWR());
            pstmt.setString(11, wifiInfo.getX_SWIFI_CNSTC_YEAR());
            pstmt.setString(12, wifiInfo.getX_SWIFI_INOUT_DOOR());
            pstmt.setString(13, wifiInfo.getX_SWIFI_REMARS3());
            pstmt.setDouble(14,wifiInfo.getLNT());
            pstmt.setDouble(15,wifiInfo.getLAT());
            pstmt.setString(16,wifiInfo.getWORK_DTTM());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
