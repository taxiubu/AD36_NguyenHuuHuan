package com.example.applicationorder;

import java.io.Serializable;

public class Food implements Serializable {
    String nameFood, image;
    int price;

    public Food(String nameFood, String image, int price) {
        this.nameFood = nameFood;
        this.image = image;
        this.price = price;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
