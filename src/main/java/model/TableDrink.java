package model;

import java.util.List;
import dao.TableDAO;

public class TableDrink {
    private int id;
    private String name;
    private String status; // Ví dụ: "Trống", "Có người"

    public TableDrink() { }

    public TableDrink(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    // Các phương thức theo spec
    public static List<TableDrink> loadTableList() {
        TableDAO dao = new TableDAO();
        return dao.getAllTables();
    }
    
    public static boolean switchTable(int fromTableId, int toTableId) {
        TableDAO dao = new TableDAO();
        return dao.switchTable(fromTableId, toTableId);
    }
    
    public static boolean editTable(TableDrink table) {
        TableDAO dao = new TableDAO();
        return dao.editTable(table.getId(), table.getName());
    }
    
    public static boolean deleteTable(int tableId) {
        TableDAO dao = new TableDAO();
        return dao.deleteTable(tableId);
    }
    
    public static boolean addNewTable(String tableName) {
        TableDAO dao = new TableDAO();
        return dao.addTable(tableName);
    }
}
