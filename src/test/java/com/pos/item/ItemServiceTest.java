package com.pos.item;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    private ItemDao itemDao = Mockito.mock(ItemDao.class);
    private ItemService itemService = new ItemService(itemDao);

    @Test
    public void shouldGetItem() throws Exception {
        //given
        Code exampleCode = new Code("SAS");
        Item exampleItem = new Item(0, new Code("SAS"), "Pencil", new BigDecimal("131"));
        String expectedItemName = "Pencil";

        Mockito.when(itemService.getItemByBarcode(exampleCode)).thenReturn(exampleItem);

        //when
        Item actual = itemService.getItemByBarcode(exampleCode);

        //then
        Mockito.verify(itemDao).getItemByBarcode(exampleCode);
        Assert.assertEquals(expectedItemName, actual.getName());
    }




}