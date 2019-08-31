//Date.java

import java.io.Serializable;

//Handles the date information for each Event
public class Date implements Serializable {
    private int day;
    private int month;
    private int year;

    Date() {
        this.setDay(-1);
        this.setMonth(-1);
        this.setYear(-1);
    }
    
    public void setDay(int day) {
        this.day = day;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public String getDate() {
        return (this.day + "/" + this.month + "/" + this.year);
    }
} //end date
