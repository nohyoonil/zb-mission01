package domain;

import properties.Properties;

import java.time.LocalDateTime;

public class BookMark {
    private int id;
    private String name;
    private int sequence;
    private LocalDateTime registrationDate;
    private LocalDateTime updateDate;



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSequence() {
        return sequence;
    }

    public String getRegistrationDate() {
        return registrationDate.format(Properties.getFormatter());
    }

    public String getUpdateDate() {
        if (updateDate == null) return "";
        return updateDate.format(Properties.getFormatter());
    }

    public String getLatestUpdateDate() {
        if (updateDate == null) return getRegistrationDate();
        else return getUpdateDate();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = LocalDateTime.parse(registrationDate, Properties.getFormatter());
    }

    public void setUpdateDate(String updateDate) {
        if (updateDate != null) this.updateDate = LocalDateTime.parse(updateDate, Properties.getFormatter());

    }
}
