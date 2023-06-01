package br.com.inline.order.model;

import java.math.BigDecimal;

public class Order {

    private String product;
    private BigDecimal amount;

    private Boolean isTaxed;

    private Boolean sentEmail;
    public Order(String product, BigDecimal amount) {
        this.product = product;
        this.amount = amount;
        this.isTaxed =  false;
        this.sentEmail = false;
    }

    public Order(String product, BigDecimal amount, Boolean isTaxed) {
        this.product = product;
        this.amount = amount;
        this.isTaxed = isTaxed;
        this.sentEmail = false;
    }

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

    public Boolean getTaxed() {
        return isTaxed;
    }

    public Boolean getSentEmail() {
        return sentEmail;
    }

    public void setSentEmail(Boolean sentEmail) {
        this.sentEmail = sentEmail;
    }
}
