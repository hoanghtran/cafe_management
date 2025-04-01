package model;

import java.util.Date;

public class Bill {
    private int id;
    private Date dateCheckIn;
    private Date dateCheckOut;
    private int idTable;
    private int status;
    private int discount;
    private float totalPrice;

    public Bill() {}

    public Bill(int id, Date dateCheckIn, Date dateCheckOut, int idTable, int status, int discount, float totalPrice) {
        this.id = id;
        this.dateCheckIn = dateCheckIn;
        this.dateCheckOut = dateCheckOut;
        this.idTable = idTable;
        this.status = status;
        this.discount = discount;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 

    public Date getDateCheckIn() {
        return dateCheckIn;
    }

    public void setDateCheckIn(Date dateCheckIn) {
        this.dateCheckIn = dateCheckIn;
    } 

    public Date getDateCheckOut() {
        return dateCheckOut;
    }

    public void setDateCheckOut(Date dateCheckOut) {
        this.dateCheckOut = dateCheckOut;
    } 

    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    } 

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    } 

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    } 

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
