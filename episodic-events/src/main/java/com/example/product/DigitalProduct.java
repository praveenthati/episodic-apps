package com.example.product;

/**
 * Created by Praveen Thati on 5/22/17.
 */

public class DigitalProduct extends Product {



    private String fileUrl;

    public DigitalProduct(String sku, String name, int priceInCents, String fileUrl) {
        super(sku, name, priceInCents);
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getProductCategory() {
        return "media";
    }
}

