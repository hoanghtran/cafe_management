package model;

import java.util.List;
import dao.RevenueDAO;

public class Revenue {
    private String date;
    private float total;

    public Revenue() { }

    public Revenue(String date, float total) {
        this.date = date;
        this.total = total;
    }

    // Getters & Setters
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public float getTotal() {
        return total;
    }
    public void setTotal(float total) {
        this.total = total;
    }
    
    // Các phương thức theo spec
    public static List<Revenue> getListBillInfo(String date) {
        RevenueDAO dao = new RevenueDAO();
        return dao.getListBillInfo(date);
    }
    
    public static List<Revenue> getDateBill(String date) {
        RevenueDAO dao = new RevenueDAO();
        return dao.getDateBill(date);
    }
    
    public static List<Revenue> statisticalBill(String fromDate, String toDate) {
        RevenueDAO dao = new RevenueDAO();
        return dao.statisticalBill(fromDate, toDate);
    }
}
