package com.foodapp.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodapp.R;
import com.foodapp.model.Product;
import com.foodapp.view.adapter.ProductAdapter;
import com.foodapp.viewmodel.ProductViewModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ProductAdapter.OnProductInteractionListener {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private Snackbar snackbar;
    private View rootView;
    private ProductViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootView = findViewById(android.R.id.content);

        setupRecyclerView();
        setupChips();

        viewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        adapter = new ProductAdapter(this, new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        viewModel.getFilteredProducts().observe(this, products -> adapter.updateList(products));
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.productRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void setupChips() {
        Chip chipOffers = findViewById(R.id.chip_offers);
        Chip chipMeals = findViewById(R.id.chip_meals);
        Chip chipMergeable = findViewById(R.id.chip_mergeable);
        Chip chipExports = findViewById(R.id.chip_exports);

        chipOffers.setOnClickListener(v -> viewModel.filterByCategory("افضل العروض"));
        chipMeals.setOnClickListener(v -> viewModel.filterByCategory("وجبات"));
        chipMergeable.setOnClickListener(v -> viewModel.filterByCategory("وجبات قابلة للدمج"));
        chipExports.setOnClickListener(v -> viewModel.filterByCategory("مستورد"));
    }

    @Override
    public void onProductClicked(Product product) {
        showOrUpdateCustomSnackbar("Price: " + String.format("%.2f", product.getPrice()) + " SAR");
    }

    @Override
    public void onQuantityChanged(Product product, int quantity) {
        double total = product.getPrice() * quantity;
        showOrUpdateCustomSnackbar("Total: " + String.format("%.2f", total) + " SAR");
    }

    private void showOrUpdateCustomSnackbar(String message) {
        if (snackbar == null || !snackbar.isShownOrQueued()) {
            snackbar = Snackbar.make(rootView, "", Snackbar.LENGTH_INDEFINITE);

            // إزالة الخلفية الافتراضية
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.TRANSPARENT);
            @SuppressLint("RestrictedApi") Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbarView;
            snackbarLayout.setPadding(0, 0, 0, 0);

            LayoutInflater inflater = LayoutInflater.from(this);
            View customSnackbarView = inflater.inflate(R.layout.custom_snackbar, null);
            snackbarLayout.addView(customSnackbarView, 0);
            TextView snackbarAction = customSnackbarView.findViewById(R.id.snackbar_action);
            TextView snackbarTotal = customSnackbarView.findViewById(R.id.snackbar_total);
            ImageButton goToCartButton = customSnackbarView.findViewById(R.id.go_to_cart);

            snackbarAction.setText("عرض السلة");
            snackbarTotal.setText(message);

            goToCartButton.setOnClickListener(v -> {
                snackbar.dismiss();
            });

            snackbar.show();
        } else {
            View snackbarView = snackbar.getView();
            @SuppressLint("RestrictedApi") Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbarView;
            View customView = snackbarLayout.getChildAt(0); // أول طفل هو الـ custom view

            TextView snackbarTotal = customView.findViewById(R.id.snackbar_total);
            snackbarTotal.setText(message);
        }
    }
}
