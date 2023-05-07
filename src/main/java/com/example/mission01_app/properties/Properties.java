package properties;

import okhttp3.OkHttpClient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public abstract class Properties {

    public static final String COORDS_TO_ADDRESS_URL = "http://api.vworld.kr/req/address?key=95C7EF65-92F2-3086-B34C-F3CEA4BDDEE1&service=address&version=2.0&point=%f,%f&format=json&crs=epsg:4326&request=getAddress&type=PARCEL";

    public final static String DB_FILE_PATH = "C:\\zb_mission01\\mission01_db.db";
    private final static Connection CONNECTION;
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final static OkHttpClient client = new OkHttpClient();

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            CONNECTION = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE_PATH);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static OkHttpClient getOkHttpClient() {
        return client;
    }
    public static Connection getConnection() {
        return CONNECTION;
    }

    public static DateTimeFormatter getFormatter() {
        return formatter;
    }
}
