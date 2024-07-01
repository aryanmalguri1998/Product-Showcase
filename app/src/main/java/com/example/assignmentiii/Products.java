package com.example.assignmentiii;

import android.os.Parcel;
import android.os.Parcelable;

public class Products implements Parcelable {
    private int id;
    private String name;
    private String description;
    private String seller;
    private double price;
    private int pictureResId;

    // Default constructor
    public Products() {}

    // Constructor with parameters
    public Products(int id, String name, String description, String seller, double price, int pictureResId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.seller = seller;
        this.price = price;
        this.pictureResId = pictureResId;
    }

    // Parcelable constructor used for unmarshalling data
    protected Products(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        seller = in.readString();
        price = in.readDouble();
        pictureResId = in.readInt();
    }

    // Parcelable.Creator instance to create instances of this Parcelable class
    public static final Creator<Products> CREATOR = new Creator<Products>() {
        @Override
        public Products createFromParcel(Parcel in) {
            return new Products(in);
        }

        @Override
        public Products[] newArray(int size) {
            return new Products[size];
        }
    };

    // Getters and Setters for private fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPictureResId() {
        return pictureResId;
    }

    public void setPictureResId(int pictureResId) {
        this.pictureResId = pictureResId;
    }

    // Parcelable implementation methods

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);                 // Write id to parcel
        dest.writeString(name);            // Write name to parcel
        dest.writeString(description);     // Write description to parcel
        dest.writeString(seller);          // Write seller to parcel
        dest.writeDouble(price);           // Write price to parcel
        dest.writeInt(pictureResId);       // Write pictureResId to parcel
    }
}
