package com.foodapp.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
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

    private Chip  chipOffers, chipMeals, chipMergeable, chipExports;

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
        chipOffers = findViewById(R.id.chip_offers);
        chipMeals = findViewById(R.id.chip_meals);
        chipMergeable = findViewById(R.id.chip_mergeable);
        chipExports = findViewById(R.id.chip_exports);

        chipOffers.setOnClickListener(v -> viewModel.filterByCategory("افضل العروض"));
        chipMeals.setOnClickListener(v -> viewModel.filterByCategory("وجبات"));
        chipMergeable.setOnClickListener(v -> viewModel.filterByCategory("وجبات قابلة للدمج"));
        chipExports.setOnClickListener(v -> viewModel.filterByCategory("مستورد"));
    }

    @Override
    public void onProductClicked(Product product) {
        showOrUpdateSnackbar("Price:" + String.format("%.2f", product.getPrice())+" SAR");
    }

    @Override
    public void onQuantityChanged(Product product, int quantity) {
        double total = product.getPrice() * quantity;
        showOrUpdateSnackbar("Total:" + String.format("%.2f", total)+" SAR");
    }

    private void showOrUpdateSnackbar(String message) {
        if (snackbar == null || !snackbar.isShownOrQueued()) {
            snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_INDEFINITE);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundResource(R.drawable.snakerbar_background);
            TextView textView = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(16f);
            snackbar.show();
        } else {
            snackbar.setText(message);
        }
    }
}
