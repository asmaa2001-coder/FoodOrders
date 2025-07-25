package com.foodapp.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.foodapp.R;
import com.foodapp.data.ProductRepository;
import com.foodapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private final ProductRepository repository;
    private final MutableLiveData<List<Product>> filteredProducts = new MutableLiveData<>();
    private List<Product> allProducts = new ArrayList<>();

    public ProductViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductRepository(application);
        insertDummyProducts();  // insert dummy products if needed
        loadAllProducts();
    }


    private void insertDummyProducts() {
        List<Product> dummyList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            dummyList.add(new Product(
                    "Product " + i,
                    10.0 + i,
                    R.drawable.product_image,
                    R.drawable.brand_logo,
                    (i % 2 == 0) ? "وجبات" : "افضل العروض"
            ));
        }
        repository.insertDummyProducts(dummyList);
    }

    private void loadAllProducts() {
        repository.getProducts(filteredProducts::postValue);
    }
    public LiveData<List<Product>> getFilteredProducts() {
        return filteredProducts;
    }

    public void filterByCategory(String category) {
        if (category.equals("افضل العروض")) {
            loadAllProducts();
        } else {
            repository.getProductsByCategory(category, filteredProducts::postValue);
        }
    }
}
