package com.pos.item;

public interface ItemDao {
    Item getItemByBarcode(Code barcode);
}
