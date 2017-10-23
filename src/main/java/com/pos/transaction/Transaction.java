package com.pos.transaction;

import com.pos.display.Display;
import com.pos.display.SimpleDisplay;
import com.pos.item.*;
import com.pos.printer.Printer;
import com.pos.printer.PrinterImpl;
import com.pos.reader.ReadFromKeyboard;
import com.pos.reader.Reader;
import sun.invoke.empty.Empty;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
    Reader reader;// = new ReadFromKeyboard();
    Display display = new SimpleDisplay();
    List<Item> scannedItems = new ArrayList<>();
    ItemDao itemDao = new ItemDaoImpl();
    ItemService itemService = new ItemService(itemDao);
    Printer printer = new PrinterImpl();

    public Transaction(Reader reader, Display display, ItemDao itemDao, ItemService itemService, Printer printer) {
        this.reader = reader;
        this.display = display;
        this.itemDao = itemDao;
        this.itemService = itemService;
        this.printer = printer;
    }

    public void startTransaction() {
        Code barcode = reader.read();
        if (barcode.getBarcode().isEmpty()) {
            display.codeIsEmpty();
            return;
        } else {
            Item scannedItem = itemService.getItemByBarcode(barcode);
            if(scannedItem == null){
                display.itemNotFound();
            }else {
                display.showItem(scannedItem);
                scannedItems.add(scannedItem);
            }
        }


    }

    public void checkout(){
        display.showTotalPrice(scannedItems);
        printer.printReceipt(scannedItems);
    }
}
