package com.example.assignmentiii;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context; // Context reference for inflating layouts
    private List<Products> productsList; // List of products to display
    private List<Products> selectedProducts = new ArrayList<>(); // List to hold selected products

    // Constructor to initialize the adapter with context and list of products
    public ProductAdapter(Context context, List<Products> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    // ViewHolder creation: inflates item layout and returns ViewHolder instance
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    // Binds data to ViewHolder: sets data to views based on position
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Products product = productsList.get(position); // Get product at current position
        // Bind product data to ViewHolder views
        holder.productName.setText(product.getName());
        holder.productDescription.setText(product.getDescription());
        holder.productSeller.setText(product.getSeller());
        holder.productPrice.setText(String.valueOf(product.getPrice()));
        holder.productImage.setImageResource(product.getPictureResId());

        // Set CheckBox state based on selectedProducts list
        holder.checkBox.setChecked(selectedProducts.contains(product));

        // Handle CheckBox state changes: add/remove product from selectedProducts list
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedProducts.add(product); // Add product to selected list
            } else {
                selectedProducts.remove(product); // Remove product from selected list
            }
        });
    }

    // Returns total number of items in the data set
    @Override
    public int getItemCount() {
        return productsList.size();
    }

    // Returns the list of selected products
    public List<Products> getSelectedProducts() {
        return selectedProducts;
    }

    // ViewHolder class: holds references to views in each item layout
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        ImageView productImage;
        TextView productName;
        TextView productDescription;
        TextView productSeller;
        TextView productPrice;

        // Constructor: initializes views by finding their IDs
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productDescription = itemView.findViewById(R.id.productDescription);
            productSeller = itemView.findViewById(R.id.productSeller);
            productPrice = itemView.findViewById(R.id.productPrice);
        }
    }
}
