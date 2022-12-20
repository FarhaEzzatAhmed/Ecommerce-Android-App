package com.example.finalprojectodc;

public class ProductModel {
    private String productTitle;
    private int productImg;

    public ProductModel(String productTitle, int productImg) {
        this.productTitle = productTitle;
        this.productImg = productImg;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getProductImg() {
        return productImg;
    }

    public void setProductImg(int productImg) {
        this.productImg = productImg;
    }
}
