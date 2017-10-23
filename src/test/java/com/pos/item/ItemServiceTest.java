package com.pos.item;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;

import static org.junit.Assert.*;

public class ItemServiceTest {

    @Mock
    private ItemDao itemDao;

    @Test
    public void name() throws Exception {
        MockitoAnnotations.initMocks(this);
        ItemService itemService = new ItemService(itemDao);
        Mockito.when(itemDao.getItemByBarcode(new Code("SAS"))).thenReturn(new Item(0, new Code("SAS"), "Kaparek", new BigDecimal("131")));
        Item actual = itemService.getItemByBarcode(new Code("SAS"));

      //  Mockito.verify(itemDao).getItemByBarcode("SAS");
        Assert.assertEquals("Kaparek", actual.getName());
    }




}