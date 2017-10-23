package com.pos.display;

import com.pos.item.Item;

import java.math.BigDecimal;

public class SimpleDisplay implements Display {

    @Override
    public void showItem(Item item) {
        System.out.println(item.getName() + " " + item.getPrice());
    }

    @Override
    public void showTotalPrice(BigDecimal totalPrice) {
        System.out.println("Total price is: " + totalPrice.toString());
    }

    @Override
    public void itemNotFound() {
        System.out.println("Product not found");
    }

    @Override
    public void codeIsEmpty() {
        System.out.println("Invalid bar-code!!");
    }
}
