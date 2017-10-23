package com.pos.display;

import com.pos.item.Item;

import java.math.BigDecimal;
import java.util.List;

public class SimpleDisplay implements Display {

    @Override
    public void showItem(Item item) {
        System.out.println(item.getName() + " " + item.getPrice());
    }

    @Override
    public void showTotalPrice(List<Item> scannedItems) {
        BigDecimal totalPrice = new BigDecimal("0");
        for (Item item : scannedItems){
            totalPrice = totalPrice.add(item.getPrice());
        }

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
