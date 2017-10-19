package com.pos.printer;

import com.pos.item.Item;

import java.math.BigDecimal;
import java.util.List;

public class PrinterImpl implements Printer {
    @Override
    public void printReceipt(List<Item> scannedItems) {
        BigDecimal totalPrice = new BigDecimal("0");
        for (Item item : scannedItems) {
            System.out.println(item.getName() + " " + item.getPrice() + "\n");
            totalPrice = totalPrice.add(item.getPrice());
        }

        System.out.println("To pay: " + totalPrice.toString() );
    }
}
