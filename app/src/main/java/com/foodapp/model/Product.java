package com.foodapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "products")
public class Product {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String name;

    private double price;
    private int productImageResId;
    private int brandLogoResId;
    private String category;

    public Product(@NonNull String name, double price, int productImageResId, int brandLogoResId, String category) {
        this.name = name;
        this.price = price;
        this.productImageResId = productImageResId;
        this.brandLogoResId = brandLogoResId;
        this.category = category;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductImageResId() {
        return productImageResId;
    }

    public void setProductImageResId(int productImageResId) {
        this.productImageResId = productImageResId;
    }

    public int getBrandLogoResId() {
        return brandLogoResId;
    }

    public void setBrandLogoResId(int brandLogoResId) {
        this.brandLogoResId = brandLogoResId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
