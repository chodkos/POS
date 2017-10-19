package com.pos.printer;

import com.pos.item.Item;

import java.util.List;

public interface Printer {
    void printReceipt(List<Item> scannedItems);
}
