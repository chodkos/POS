package com.pos.transaction;

import com.pos.display.Display;
import com.pos.display.SimpleDisplay;
import com.pos.item.Code;
import com.pos.item.Item;
import com.pos.item.ItemDao;
import com.pos.item.ItemService;
import com.pos.printer.Printer;
import com.pos.printer.PrinterImpl;
import com.pos.reader.ReadFromKeyboard;
import com.pos.reader.Reader;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class TransactionTest {

    Reader reader = Mockito.mock(Reader.class);
    Display display = Mockito.mock(Display.class);
    Printer printer = Mockito.mock(Printer.class);
    ItemDao itemDao = Mockito.mock(ItemDao.class);
    ItemService itemService = new ItemService(itemDao);

    @Test
    public void shouldPrintOneItem() {
       /* Reader reader = Mockito.mock(Reader.class);
        Display display = Mockito.mock(Display.class);
        Printer printer = Mockito.mock(Printer.class);
        ItemDao itemDao = Mockito.mock(ItemDao.class);
        ItemService itemService = new ItemService(itemDao);*/
        String message = "Code is empty";
        Code exampleBarCode = new Code("SAS");

        //when
        Mockito.when(reader.read()).thenReturn(exampleBarCode);
        Mockito.when(itemService.getItemByBarcode(exampleBarCode)).thenReturn(new Item(0, new Code("SAS"), "Kaktus", new BigDecimal("1223")));

        Mockito.doNothing().when(display).codeIsEmpty();
        Item actualItem = itemService.getItemByBarcode(exampleBarCode);
        Item expectedItem = new Item(0, exampleBarCode, "Kaktus", new BigDecimal("1223"));

        //then
        display.codeIsEmpty();
        Mockito.verify(display).itemNotFound();
        //Assert.assertEquals("Kaktus", actualItem.getName());
    }


    @Test
    public void shouldPrintInvalidBarcode() throws Exception {
        //given
        Code emptyBarcode = new Code("");
        Transaction transaction = Mockito.spy(new Transaction(reader, display, itemDao, itemService, printer));
        Mockito.when(reader.read()).thenReturn(emptyBarcode);
        // Mockito.doReturn(emptyBarcode).when(reader).read();

        //when
        transaction.startTransaction();

        //then
        Mockito.verify(display).codeIsEmpty();
    }

    @Test
    public void shouldReadCode() throws Exception {
        //given
        Code exampleBarcode = new Code("XYZ");
        Mockito.doReturn(exampleBarcode).when(reader).read();

        //when
        String actualBarcode = reader.read().getBarcode();

        //then
        Assert.assertEquals("XYZ", actualBarcode);
    }

    @Test
    public void shouldShowItemAndPrintRecipt() throws Exception {
        Code exampleBarcode = new Code("XYZ");
        Item exampleItem = new Item(0, exampleBarcode, "Mighty Item", new BigDecimal("999"));
        Transaction transaction = Mockito.spy(new Transaction(reader, display, itemDao, itemService, printer));
        Mockito.doReturn(exampleBarcode).when(reader).read();
        Mockito.when(itemService.getItemByBarcode(exampleBarcode)).thenReturn(exampleItem);
    }
}