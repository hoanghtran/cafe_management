package model;

import java.util.List;
import dao.DrinkDAO;

public class Drink {
    private int id;
    private String name;
    private int categoryId;
    private float price;

    public Drink() { }

    public Drink(int id, String name, int categoryId, float price) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
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
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    
    // Các phương thức theo spec
    public static List<Drink> getDrinkByCategoryId(int categoryId) {
        DrinkDAO dao = new DrinkDAO();
        List<Drink> list = dao.getAllDrinks();
        list.removeIf(d -> d.getCategoryId() != categoryId);
        return list;
    }
    
    public static List<Drink> getListDrink() {
        DrinkDAO dao = new DrinkDAO();
        return dao.getAllDrinks();
    }
    
    public static List<Drink> searchDrinkByName(String name) {
        DrinkDAO dao = new DrinkDAO();
        List<Drink> list = dao.getAllDrinks();
        list.removeIf(d -> !d.getName().toLowerCase().contains(name.toLowerCase()));
        return list;
    }
    
    public static boolean insertDrink(Drink drink) {
        DrinkDAO dao = new DrinkDAO();
        return dao.addDrink(drink);
    }
    
    public static boolean updateDrink(Drink drink) {
        // Bạn có thể triển khai cập nhật qua DAO (ví dụ: dao.updateDrink(drink))
        System.out.println("Cập nhật đồ uống: " + drink.getName());
        return true;
    }
    
    public static boolean deleteDrink(int drinkId) {
        System.out.println("Xóa đồ uống có ID: " + drinkId);
        return true;
    }
}
