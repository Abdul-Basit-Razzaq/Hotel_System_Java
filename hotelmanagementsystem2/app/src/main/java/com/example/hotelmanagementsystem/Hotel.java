package com.example.hotelmanagementsystem;


public class Hotel {
    private String name;
    private String address;
    private double price;
    private String description;

    public Hotel() {
        // Default constructor required for calls to DataSnapshot.getValue(Hotel.class)
    }

    public Hotel(String name, String address, double price, String description) {
        this.name = name;
        this.address = address;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
