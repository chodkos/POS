package com.pos.item;

import java.math.BigDecimal;

public class ItemDaoImpl implements ItemDao {
    @Override
    public Item getItemByBarcode(Code barcode) {
        return new Item(0, new Code("ABC"), "Desk", new BigDecimal("300"));
    }
}
