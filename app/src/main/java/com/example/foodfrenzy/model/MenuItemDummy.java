package com.example.foodfrenzy.model;

public class MenuItem {
    private String id;
    private String name;
    private double price;
    private String description;

    public MenuItem(String id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "MenuItem{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", price=" + price +
               ", description='" + description + '\'' +
               '}';
    }
}