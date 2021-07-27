package com.happy.Reptile_Store.petsgrocery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {
    String name, description, quantity;
    double price;
    int imgID;
    List<Integer> images;

    public Product(String name, String description, double price, int imgID, List<Integer> images, String quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgID = imgID;
        this.images = images;
        this.quantity = quantity;
    }

    public Product() {
        this.name = "";
        this.description = "";
        this.quantity = "";
        this.price = 0;
        this.imgID = 0;
        this.images = new ArrayList<>();
    }


    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public List<Integer> getImages() {
        return images;
    }

    public void setImages(List<Integer> images) {
        this.images = images;
    }
}
