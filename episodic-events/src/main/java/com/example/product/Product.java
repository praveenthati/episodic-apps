package com.example.product;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by Praveen Thati on 5/22/17.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "productCategory")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Product.class, name = "product"),
        @JsonSubTypes.Type(value = DigitalProduct.class, name = "media"),
        @JsonSubTypes.Type(value = StreamingProduct.class, name = "stream")
})
public class Product {
    private String sku;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(int priceInCents) {
        this.priceInCents = priceInCents;
    }

    private String name;
    private int priceInCents;

    public Product(String sku, String name, int priceInCents) {
        this.sku = sku;
        this.name = name;
        this.priceInCents = priceInCents;
    }

    public String getProductCategory() {
        return "product";
    }
}
