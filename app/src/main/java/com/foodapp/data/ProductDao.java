package com.foodapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.foodapp.model.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM products")
    List<Product> getAllProducts();


    @Query("SELECT * FROM products WHERE category = :category")
    List<Product> getProductsByCategory(String category);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Product> products);
}

