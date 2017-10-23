package com.pos.display;

import com.pos.item.Item;

import java.math.BigDecimal;

public interface Display {
    void showItem(Item item);
    void showTotalPrice(BigDecimal totalPrice);
    void itemNotFound();
    void codeIsEmpty();
}
