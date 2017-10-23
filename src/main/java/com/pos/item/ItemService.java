package com.pos.item;

public class ItemService {

    private ItemDao itemDao;

    public ItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public Item getItemByBarcode(Code barcode){
        return itemDao.getItemByBarcode(barcode);
    }
}
