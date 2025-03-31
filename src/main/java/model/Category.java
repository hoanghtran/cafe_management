package model;

import java.util.List;
import dao.CategoryDAO;

public class Category {
    private int id;
    private String name;

    public Category() { }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
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
    
    // Các phương thức theo spec
    public static List<Category> getListCategory() {
        CategoryDAO dao = new CategoryDAO();
        return dao.getAllCategories();
    }
    
    public static Category getCategory(int id) {
        CategoryDAO dao = new CategoryDAO();
        for (Category cat : dao.getAllCategories()) {
            if (cat.getId() == id) {
                return cat;
            }
        }
        return null;
    }
    
    public static boolean addNewCategory(String name) {
        CategoryDAO dao = new CategoryDAO();
        return dao.addCategory(name);
    }
    
    public static boolean deleteCategory(int id) {
        CategoryDAO dao = new CategoryDAO();
        return dao.deleteCategory(id);
    }
    
    public static boolean editCategory(Category category) {
        CategoryDAO dao = new CategoryDAO();
        return dao.editCategory();
    }
}
