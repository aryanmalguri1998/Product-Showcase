package com.example.assignmentiii;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database name and version
    private static final String DATABASE_NAME = "products.db";
    private static final int DATABASE_VERSION = 1;

    // Table name and column names
    private static final String TABLE_PRODUCTS = "products";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_SELLER = "seller";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_PICTURE = "picture";

    // Constructor for DatabaseHelper
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create the products table
        String createTable = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_SELLER + " TEXT,"
                + COLUMN_PRICE + " REAL,"
                + COLUMN_PICTURE + " INTEGER" + ")";
        // Execute the SQL statement
        db.execSQL(createTable);
    }

    // Called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing products table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        // Create a new table by calling onCreate method
        onCreate(db);
    }

    // Method to insert a product into the database
    public void insertProduct(Products products) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // Put values into ContentValues object for insertion
        values.put(COLUMN_NAME, products.getName());
        values.put(COLUMN_DESCRIPTION, products.getDescription());
        values.put(COLUMN_SELLER, products.getSeller());
        values.put(COLUMN_PRICE, products.getPrice());
        values.put(COLUMN_PICTURE, products.getPictureResId());

        // Insert the values into the products table
        db.insert(TABLE_PRODUCTS, null, values);
        // Close the database connection
        db.close();
    }

    // Method to retrieve all products from the database
    public List<Products> getAllProducts() {
        List<Products> productsList = new ArrayList<>();
        // Select query to fetch all columns from the products table
        String selectQuery = "SELECT * FROM " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getReadableDatabase();
        // Execute the select query and obtain a Cursor object
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Check if the cursor is not null and move to the first row
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Retrieve column values from the cursor
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                String seller = cursor.getString(cursor.getColumnIndex(COLUMN_SELLER));
                double price = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE));
                int pictureResId = cursor.getInt(cursor.getColumnIndex(COLUMN_PICTURE));

                // Create a Products object with retrieved values
                Products products = new Products(id, name, description, seller, price, pictureResId);
                // Add the Products object to the list
                productsList.add(products);
            } while (cursor.moveToNext());
            // Close the cursor once all rows are processed
            cursor.close();
        }
        // Close the database connection
        db.close();
        // Return the list of products retrieved from the database
        return productsList;
    }
}
