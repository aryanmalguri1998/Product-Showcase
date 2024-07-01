package com.example.assignmentiii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Products> productsList;
    private DatabaseHelper databaseHelper;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView and its layout manager
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        nextButton = findViewById(R.id.nextButton);

        // Initialize DatabaseHelper to interact with the SQLite database
        databaseHelper = new DatabaseHelper(this);

        // Insert sample products into the database if it's empty
        if (databaseHelper.getAllProducts().isEmpty()) {
            insertSampleProducts();
        }

        // Retrieve all products from the database
        productsList = databaseHelper.getAllProducts();

        // Initialize the ProductAdapter with the retrieved product list
        productAdapter = new ProductAdapter(this, productsList);
        recyclerView.setAdapter(productAdapter);

        // Set click listener for the "Next" button
        nextButton.setOnClickListener(v -> {
            // Get the list of selected products from the adapter
            List<Products> selectedProducts = productAdapter.getSelectedProducts();
            if (selectedProducts.size() < 3) {
                // Display a toast message if less than 3 products are selected
                Toast.makeText(MainActivity.this, "Select at least three products", Toast.LENGTH_SHORT).show();
            } else {
                // If at least 3 products are selected, proceed to the SecondActivity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                // Pass the selected products as ParcelableArrayListExtra to SecondActivity
                intent.putParcelableArrayListExtra("selectedProducts", new ArrayList<>(selectedProducts));
                startActivity(intent);
            }
        });
    }

    // Method to insert sample products into the database if it's empty
    private void insertSampleProducts() {
        databaseHelper.insertProduct(new Products(1, "Desktop Computer", "Powerful desktop for gaming and work", "Tech Store", 1599.99, R.drawable.desktop_computer));
        databaseHelper.insertProduct(new Products(2, "Tablet", "Lightweight tablet with high-resolution display", "Electronics Hub", 499.99, R.drawable.tablet));
        databaseHelper.insertProduct(new Products(3, "Camera", "Professional DSLR camera with 4K video recording", "Camera World", 2199.99, R.drawable.camera));
        databaseHelper.insertProduct(new Products(4, "Wireless Earbuds", "Premium wireless earbuds with noise isolation", "AudioTech", 149.99, R.drawable.earbuds));
    }
}
