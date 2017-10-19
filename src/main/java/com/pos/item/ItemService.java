package com.pos.item;

import java.math.BigDecimal;

public class ItemService {
    //private Item item;

    public Item getItemByBarcode(String barcode){
        return new Item(0, "SAP021", "Palnik", new BigDecimal("55"));
    }
}
