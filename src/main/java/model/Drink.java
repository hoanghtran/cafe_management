package model;

public class Drink {

    private int id;
    private String name;
    private int idCategory;
    private float price;

    public Drink() {
    }

    public Drink(int id, String name, int idCategory, float price) {
        this.id = id;
        this.name = name;
        this.idCategory = idCategory;
        this.price = price;
    }

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

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
