package com.pos.transaction;

import com.pos.display.Display;
import com.pos.item.Code;
import com.pos.item.Item;
import com.pos.item.ItemDao;
import com.pos.item.ItemService;
import com.pos.printer.Printer;
import com.pos.reader.Reader;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TransactionTest {

    private Reader reader = Mockito.mock(Reader.class);
    private Display display = Mockito.mock(Display.class);
    private Printer printer = Mockito.mock(Printer.class);
    private ItemDao itemDao = Mockito.mock(ItemDao.class);
    private ItemService itemService = new ItemService(itemDao);
    private Transaction transaction = Mockito.spy(new Transaction(reader, display, itemDao, itemService, printer));


    @After
    public void clear(){
        transaction.clearResources();
    }

    @Test
    public void shouldPrintItemNotFound() {
        //given
        Code exampleBarCode = new Code("ASD");
        Mockito.when(reader.read()).thenReturn(exampleBarCode);
        Mockito.when(itemService.getItemByBarcode(exampleBarCode)).thenReturn(null);

        //when
        Item actualItem = itemService.getItemByBarcode(exampleBarCode);
        transaction.startTransaction();

        //then
        Mockito.verify(display).itemNotFound();
        Assert.assertNull(actualItem);
    }


    @Test
    public void shouldPrintInvalidBarcode() throws Exception {
        //given
        Code emptyBarcode = new Code("");
        Mockito.when(reader.read()).thenReturn(emptyBarcode);

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
    public void shouldShowItem() throws Exception {
        //given
        Code exampleBarcode = new Code("XYZ");
        Item exampleItem = new Item(0, exampleBarcode, "Mighty Item", new BigDecimal("999"));

        Mockito.doReturn(exampleBarcode).when(reader).read();
        Mockito.when(itemService.getItemByBarcode(exampleBarcode)).thenReturn(exampleItem);
        Mockito.doNothing().when(display).showItem(exampleItem);

        //when
        transaction.startTransaction();

        //then
        Mockito.verify(display).showItem(exampleItem);
    }

    @Test
    public void shouldCheckout() throws Exception {
        //given
        Item exampleItem = new Item(0, new Code("ABC"), "Item1", new BigDecimal("999"));
        Item exampleItem2 = new Item(0, new Code("DEF"), "Item2", new BigDecimal("1"));
        List<Item> expectedItems = transaction.getScannedItems();
        expectedItems.add(exampleItem);
        expectedItems.add(exampleItem2);
        int scannedItemsCount = expectedItems.size();
        BigDecimal expectedTotalPrice = exampleItem.getPrice().add(exampleItem2.getPrice());

        Mockito.doNothing().when(display).showTotalPrice(Mockito.any());
        Mockito.doNothing().when(printer).printReceipt(Mockito.anyList(), Mockito.any());

        //when
        transaction.checkout();
        BigDecimal actualTotalPrice = transaction.getTotalPrice(expectedItems);

        //then
        Assert.assertEquals(2, scannedItemsCount);
        Assert.assertEquals(expectedTotalPrice, actualTotalPrice);
        Mockito.verify(display).showTotalPrice(Mockito.any());
        Mockito.verify(printer).printReceipt(Mockito.anyList(), Mockito.any());

    }
}