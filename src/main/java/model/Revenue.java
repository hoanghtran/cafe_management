package model;

import java.util.Date;

public class Revenue {
    private Date date;
    private float totalRevenue;

    public Revenue() {}

    public Revenue(Date date, float totalRevenue) {
        this.date = date;
        this.totalRevenue = totalRevenue;
    } 

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    } 

    public float getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(float totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
