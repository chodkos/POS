package com.pos.item;

import java.math.BigDecimal;

public class Item {

    private Integer id;
    private Code barcode;
    private String name;
    private BigDecimal price;

    public Item(Integer id, Code barcode, String name, BigDecimal price) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public Code getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (barcode != null ? !barcode.equals(item.barcode) : item.barcode != null) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        return price != null ? price.equals(item.price) : item.price == null;
    }

    @Override
    public int hashCode() {
        int result = barcode != null ? barcode.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
