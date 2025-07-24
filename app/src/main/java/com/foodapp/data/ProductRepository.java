package com.foodapp.data;

import android.app.Application;

import com.foodapp.model.Product;

import java.util.List;

public class ProductRepository {
    private final ProductDao productDao;

    public ProductRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        productDao = db.productDao();
    }

    public void getProducts(Callback callback) {
        AppExecutors.getInstance().diskIO().execute(() -> {
            List<Product> products = productDao.getAllProducts();
            callback.onResult(products);
        });
    }

    public void getProductsByCategory(String category, Callback callback) {
        AppExecutors.getInstance().diskIO().execute(() -> {
            List<Product> products = productDao.getProductsByCategory(category);
            callback.onResult(products);
        });
    }
    public void insertDummyProducts(List<Product> products) {
        AppExecutors.getInstance().diskIO().execute(() -> {
            productDao.insertAll(products);
        });
    }
    public interface Callback {
        void onResult(List<Product> products);
    }
}
