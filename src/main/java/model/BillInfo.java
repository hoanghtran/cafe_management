package model;

public class BillInfo {

    private int id;
    private int idBill;
    private int idFood;
    private int count;

    public BillInfo() {
    }

    public BillInfo(int id, int idBill, int idFood, int count) {
        this.id = id;
        this.idBill = idBill;
        this.idFood = idFood;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
