package br.com.inline.order.model;

import java.math.BigDecimal;

public class OrderRequest {
    private String product;
    private BigDecimal amount;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
