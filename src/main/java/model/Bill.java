package model;

import java.util.Date;
import java.util.List;
import dao.BillDAO;

public class Bill {
    private int id;
    private Date dateCheckIn;
    private Date dateCheckOut;
    private int idTable;
    private boolean status;
    private int discount;
    private float totalPrice;

    public Bill() { }

    public Bill(int id, Date dateCheckIn, Date dateCheckOut, int idTable, boolean status, int discount, float totalPrice) {
        this.id = id;
        this.dateCheckIn = dateCheckIn;
        this.dateCheckOut = dateCheckOut;
        this.idTable = idTable;
        this.status = status;
        this.discount = discount;
        this.totalPrice = totalPrice;
    }

    // Getters & Setters
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
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
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
    
    // Các phương thức theo spec
    public static int getNumBillListByDate(String date) {
        BillDAO dao = new BillDAO();
        return dao.getNumBillListByDate(date);
    }
    
    public static List<Bill> getBillListByDateAndPage(String date, int page) {
        BillDAO dao = new BillDAO();
        return dao.getBillListByDateAndPage(date, page);
    }
    
    public static List<Bill> getBillListByDate(String date) {
        BillDAO dao = new BillDAO();
        return dao.getBillListByDate(date);
    }
    
    public static boolean checkOut(int billId, int discount, float totalPrice) {
        BillDAO dao = new BillDAO();
        return dao.updateBill(billId, discount, totalPrice);
    }
    
    public static int getMaxIdBill() {
        BillDAO dao = new BillDAO();
        return dao.getMaxIdBill();
    }
    
    public static int insertBill(int tableId) {
        BillDAO dao = new BillDAO();
        return dao.insertBill(tableId);
    }
    
    public static Bill getUnCheckBillByTableId(int tableId) {
        BillDAO dao = new BillDAO();
        return dao.getCurrentBill(tableId);
    }
}
