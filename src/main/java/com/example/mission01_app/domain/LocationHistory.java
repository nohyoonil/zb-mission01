package domain;

import properties.Properties;

import java.time.LocalDateTime;

public class LocationHistory {
    private int id;
    private double LAT;
    private double LNT;
    private LocalDateTime inquiryDate;



    public int getId() {
        return id;
    }

    public double getLAT() {
        return LAT;
    }

    public double getLNT() {
        return LNT;
    }

    public String getInquiryDate() {
        return inquiryDate.format(Properties.getFormatter());
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLAT(double LAT) {
        this.LAT = LAT;
    }

    public void setLNT(double LNT) {
        this.LNT = LNT;
    }

    public void setInquiryDate(String inquiryDate) {
        this.inquiryDate = LocalDateTime.parse(inquiryDate, Properties.getFormatter());
    }
}
