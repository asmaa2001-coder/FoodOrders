package com.foodapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodapp.R;
import com.foodapp.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    public interface OnProductInteractionListener {
        void onProductClicked(Product product);
        void onQuantityChanged(Product product, int quantity);
    }

    private Context context;
    private List<Product> productList;
    private OnProductInteractionListener listener;

    // Track quantity per product position
    private Map<Integer, Integer> quantityMap = new HashMap<>();

    public ProductAdapter(Context context, List<Product> productList, OnProductInteractionListener listener) {
        this.context = context;
        this.productList = productList;
        this.listener = listener;
    }
    public void updateList(List<Product> newList) {
        this.productList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, final int position) {
        Product product = productList.get(position);

        holder.productImage.setImageResource(product.getProductImageResId());
        holder.brandLogo.setImageResource(product.getBrandLogoResId());
        holder.productName.setText(product.getName());
        holder.productPrice.setText(String.format("$%.2f", product.getPrice()));

        int quantity = quantityMap.containsKey(position) ? quantityMap.get(position) : 0;
        holder.quantityText.setText(String.valueOf(quantity));

        // Item click listener
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onProductClicked(product);
            }
        });

        holder.btnPlus.setOnClickListener(v -> {
            int currentQty = quantityMap.containsKey(position) ? quantityMap.get(position) : 0;
            currentQty++;
            quantityMap.put(position, currentQty);
            holder.quantityText.setText(String.valueOf(currentQty));
            if (listener != null) {
                listener.onQuantityChanged(product, currentQty);
            }
        });

        holder.btnMinus.setOnClickListener(v -> {
            int currentQty = quantityMap.containsKey(position) ? quantityMap.get(position) : 0;
            if (currentQty > 0) {
                currentQty--;
                quantityMap.put(position, currentQty);
                holder.quantityText.setText(String.valueOf(currentQty));
                if (listener != null) {
                    listener.onQuantityChanged(product, currentQty);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        ImageView brandLogo;
        TextView productName;
        TextView productPrice;
        ImageButton btnPlus;
        ImageButton btnMinus;
        TextView quantityText;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            brandLogo = itemView.findViewById(R.id.brandLogo);
            productName = itemView.findViewById(R.id.itemName);
            productPrice = itemView.findViewById(R.id.itemPrice);
            btnPlus = itemView.findViewById(R.id.increaseButton);
            btnMinus = itemView.findViewById(R.id.decreaseButton);
            quantityText = itemView.findViewById(R.id.itemQuantity);
        }
    }
}