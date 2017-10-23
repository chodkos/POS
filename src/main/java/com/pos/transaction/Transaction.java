package com.pos.transaction;

import com.pos.display.Display;
import com.pos.display.SimpleDisplay;
import com.pos.item.*;
import com.pos.printer.Printer;
import com.pos.printer.PrinterImpl;
import com.pos.reader.ReadFromKeyboard;
import com.pos.reader.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private Reader reader = new ReadFromKeyboard();
    private Display display = new SimpleDisplay();
    private ItemDao itemDao = new ItemDaoImpl();
    private ItemService itemService = new ItemService(itemDao);
    private Printer printer = new PrinterImpl();
    List<Item> scannedItems = new ArrayList<>();

    public Transaction(Reader reader, Display display, ItemDao itemDao, ItemService itemService, Printer printer) {
        this.reader = reader;
        this.display = display;
        this.itemDao = itemDao;
        this.itemService = itemService;
        this.printer = printer;
    }

    public Transaction() {
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
        BigDecimal totalprice = getTotalPrice(scannedItems);
        display.showTotalPrice(totalprice);
        printer.printReceipt(scannedItems, totalprice);
    }

    public BigDecimal getTotalPrice(List<Item> scannedItems){
        BigDecimal sumOfPrices = new BigDecimal("0");
        for (Item item : scannedItems){
            sumOfPrices = sumOfPrices.add(item.getPrice());
        }
        return sumOfPrices;
    }
}
