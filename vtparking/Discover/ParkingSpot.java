package com.example.fredliu.vtparking.Discover;

import android.graphics.Bitmap;

/**
 * Created by fredliu on 12/3/17.
 */

public class ParkingSpot {
    private Bitmap image;
    private String lot;
    private String spot;
    private String floor;


    public ParkingSpot(Bitmap image, String lot, String spot, String floor) {
        super();
        this.image = image;
        this.lot = lot;
        this.spot = spot;
        this.floor = floor;

    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }


    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getSpot() {
        return spot;
    }

    public void setNumber(String spot) {
        this.spot = spot;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }
}
