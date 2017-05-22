package com.example.product;

import com.example.product.Product;

/**
 * Created by Praveen Thati on 5/22/17.
 */
public class StreamingProduct extends Product {

    public String getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    private String streamUrl;

    public StreamingProduct(String sku, String name, int priceInCents, String streamUrl) {
        super(sku, name, priceInCents);
        this.streamUrl = streamUrl;
    }

    public String getProductCategory() {
        return "stream";
    }

}