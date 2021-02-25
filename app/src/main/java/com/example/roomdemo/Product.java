package com.example.roomdemo;

import androidx.annotation.NonNull;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Update;

@Entity(tableName = "products")
public class Product {

    @PrimaryKey
    @NonNull
    int _id;
    @NonNull
    String name, vendor;
    @NonNull
    float price;

    public Product(int _id, @NonNull String name, @NonNull String vendor, float price) {
        this._id = _id;
        this.name = name;
        this.vendor = vendor;
        this.price = price;
    }


}
