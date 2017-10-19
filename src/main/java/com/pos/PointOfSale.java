package com.pos;

import com.pos.display.Display;
import com.pos.display.SimpleDisplay;
import com.pos.item.Item;
import com.pos.item.ItemService;
import com.pos.printer.Printer;
import com.pos.printer.PrinterImpl;
import com.pos.reader.ReadFromKeyboard;
import com.pos.reader.Reader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PointOfSale {

    public static void main(String[] args) {

        Reader reader = new ReadFromKeyboard();
        Display display = new SimpleDisplay();
        List<Item> scannedItems = new ArrayList<>();
        ItemService itemService = new ItemService();
        Printer printer = new PrinterImpl();


        if(reader.read().isEmpty()){
            display.codeIsEmpty();
        }else
        {
            Item scannedItem = itemService.getItemByBarcode("SAP021");
            display.showItem(scannedItem);
            scannedItems.add(scannedItem);
        }

        display.showTotalPrice(scannedItems);
        printer.printReceipt(scannedItems);

    }

}
