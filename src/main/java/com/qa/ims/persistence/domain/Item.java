package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item {
    private long itemId;
    private String itemName;
    private double price;
    private int stock;

    public Item(long itemId, String itemName, double price, int stock) {
        this.setItemId(itemId);
        this.setItemName(itemName);
        this.setPrice(price);
        this.setStock(stock);
    }

    public Item(String itemName, double price, int stock) {
        this.setItemName(itemName);
        this.setPrice(price);
        this.setStock(stock);
    }

    public long getItemId() {
        return this.itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Item)) {
            return false;
        }
        Item item = (Item) o;
        return itemId == item.itemId && Objects.equals(itemName, item.itemName) && Objects.equals(price, item.price)
                && stock == item.stock;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemName, price, stock);
    }

    @Override
    public String toString() {
        return "{" +
                " itemId='" + getItemId() + "'" +
                ", itemName='" + getItemName() + "'" +
                ", price='" + getPrice() + "'" +
                ", stock='" + getStock() + "'" +
                "}";
    }

}
