package com.pos.display;

import com.pos.item.Item;

import java.util.List;

public interface Display {
    void showItem(Item item);
    void showTotalPrice(List<Item> scannedItems);
    void itemNotFound();
    void codeIsEmpty();
}
