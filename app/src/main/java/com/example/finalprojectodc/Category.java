package com.example.finalprojectodc;

public class Category {
    public int id;
    public String name;
    public String image;
    public String keyLoremSpace;

    public Category(int id, String name, String image, String keyLoremSpace) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.keyLoremSpace = keyLoremSpace;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKeyLoremSpace() {
        return keyLoremSpace;
    }

    public void setKeyLoremSpace(String keyLoremSpace) {
        this.keyLoremSpace = keyLoremSpace;
    }
}
