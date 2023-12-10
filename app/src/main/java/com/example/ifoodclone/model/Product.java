package com.example.ifoodclone.model;

import com.example.ifoodclone.helper.FirebaseConfiguration;
import com.google.firebase.database.DatabaseReference;

public class Product {
    private String userId;
    private String name;
    private String description;
    private Double price;

    public Product() {
    }
    public void save(){
        DatabaseReference databaseReference = FirebaseConfiguration.getDatabaseReference();
        DatabaseReference productReference = databaseReference.child("products").child(getUserId()).push();
        productReference.setValue(this);
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
