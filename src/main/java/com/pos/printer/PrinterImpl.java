package com.pos.printer;

import com.pos.item.Item;

import java.math.BigDecimal;
import java.util.List;

public class PrinterImpl implements Printer {
    @Override
    public void printReceipt(List<Item> scannedItems, BigDecimal totalPrice) {
        System.out.println("\nReceipt\n");
        for (Item item : scannedItems) {
            System.out.println(item.getName() + " " + item.getPrice() + "\n");
        }

        System.out.println("To pay: " + totalPrice.toString() );
    }
}
