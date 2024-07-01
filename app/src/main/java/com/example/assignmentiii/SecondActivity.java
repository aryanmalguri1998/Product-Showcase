package com.example.assignmentiii;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Products> selectedProducts;
    private Button sendEmailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve selected products from the intent
        selectedProducts = getIntent().getParcelableArrayListExtra("selectedProducts");

        // Set up RecyclerView with selected products using ProductAdapter
        productAdapter = new ProductAdapter(this, selectedProducts);
        recyclerView.setAdapter(productAdapter);

        // Initialize and set onClickListener for the sendEmailButton
        sendEmailButton = findViewById(R.id.sendEmailButton);
        sendEmailButton.setOnClickListener(v -> sendEmail());
    }

    // Method to handle sending email
    private void sendEmail() {
        // Prepare email content
        StringBuilder emailContent = new StringBuilder();
        for (Products products : selectedProducts) {
            emailContent.append("Name: ").append(products.getName()).append("\n")
                    .append("Description: ").append(products.getDescription()).append("\n")
                    .append("Seller: ").append(products.getSeller()).append("\n")
                    .append("Price: ").append(products.getPrice()).append("\n\n");
        }

        // Create an Intent for sending email
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"sweng888mobileapps@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Selected Products");
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailContent.toString());

        try {
            // Start activity to choose email client and send email
            startActivity(Intent.createChooser(emailIntent, "Send email..."));

            // Clear selected products list and update RecyclerView
            selectedProducts.clear();
            productAdapter.notifyDataSetChanged();

            // Show success message
            Toast.makeText(this, "Email sent successfully", Toast.LENGTH_SHORT).show();
        } catch (android.content.ActivityNotFoundException ex) {
            // Handle case where no email client is installed
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
