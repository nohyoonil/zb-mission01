package domain;

import properties.Properties;

import java.time.LocalDateTime;

public class BookMark_WIfi {
    private int id;
    private String bookmarkName;
    private String wifiName;
    private LocalDateTime registrationData;

    private int bookmarkID;
    private String wifiID;


    public int getId() {
        return id;
    }

    public String getBookmarkName() {
        return bookmarkName;
    }

    public String getWifiName() {
        return wifiName;
    }

    public String getRegistrationData() {
        return registrationData.format(Properties.getFormatter());
    }

    public int getBookmarkID() {
        return bookmarkID;
    }

    public String getWifiID() {
        return wifiID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookmarkName(String bookmarkName) {
        this.bookmarkName = bookmarkName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public void setRegistrationData(String registrationData) {
        this.registrationData = LocalDateTime.parse(registrationData, Properties.getFormatter());
    }

    public void setBookmarkID(int bookmarkID) {
        this.bookmarkID = bookmarkID;
    }

    public void setWifiID(String wifiID) {
        this.wifiID = wifiID;
    }
}
