package com.pos.item;

import java.math.BigDecimal;

public class Item {

    public Item(Integer id, String barCode, String name, BigDecimal price) {
        this.id = id;
        this.barCode = barCode;
        this.name = name;
        this.price = price;
    }

    private Integer id;
    private String barCode;
    private String name;
    private BigDecimal price;



    public Integer getId() {
        return id;
    }

    public String getBarCode() {
        return barCode;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
