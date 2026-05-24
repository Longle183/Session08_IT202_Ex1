package com.restaurant.ex1.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class StockRequest {

    @NotBlank(message = "SKU không được để trống")
    private String sku;

    @Positive(message = "Quantity phải > 0")
    private Integer quantity;
    public StockRequest() {
    }

    public String getSku() {
        return sku;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}