package com.pos.item;

import java.math.BigDecimal;

public class ItemService {

    private ItemDao itemDao;

    public ItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public Item getItemByBarcode(Code barcode){
        return itemDao.getItemByBarcode(barcode);
        //return new Item(0, "CDA", "Palnik", new BigDecimal("55"));
    }
}
