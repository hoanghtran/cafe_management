package model;

import java.util.List;
import dao.BillInfoDAO;

public class BillInfo {
    private int id;
    private int billId;
    private int foodId;
    private int count;

    public BillInfo() { }

    public BillInfo(int id, int billId, int foodId, int count) {
        this.id = id;
        this.billId = billId;
        this.foodId = foodId;
        this.count = count;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getBillId() {
        return billId;
    }
    public void setBillId(int billId) {
        this.billId = billId;
    }
    public int getFoodId() {
        return foodId;
    }
    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    
    // Các phương thức sử dụng DAO
    public static boolean insertBillInfo(BillInfo billInfo) {
        BillInfoDAO dao = new BillInfoDAO();
        return dao.insertBillInfo(billInfo);
    }
    
    public static List<BillInfo> getListBillInfo(int billId) {
        BillInfoDAO dao = new BillInfoDAO();
        return dao.getListBillInfo(billId);
    }
    
    public static boolean deleteBillInfoByDrinkID(int drinkId) {
        BillInfoDAO dao = new BillInfoDAO();
        return dao.deleteBillInfoByDrinkID(drinkId);
    }
}
