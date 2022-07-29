package com.qa.ims.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {

    @Mock
    private Utils utils;

    @Mock
    private ItemDAO dao;

    @InjectMocks
    private ItemController controller;

    @Test
    public void testCreate() {
        final String I_NAME = "cat";
        final double I_PRICE = 3.50f;
        final int I_STOCK = 10;
        final Item created = new Item(I_NAME, I_PRICE, I_STOCK);

        Mockito.when(utils.getString()).thenReturn(I_NAME);
        Mockito.when(utils.getDouble()).thenReturn(I_PRICE);
        Mockito.when(utils.getInteger()).thenReturn(I_STOCK);
        Mockito.when(dao.create(created)).thenReturn(created);
        assertEquals(created, controller.create());
        Mockito.verify(utils, Mockito.times(1)).getString();
        Mockito.verify(utils, Mockito.times(1)).getDouble();
        Mockito.verify(utils, Mockito.times(1)).getInteger();
        Mockito.verify(dao, Mockito.times(1)).create(created);
    }

    @Test
    public void testReadAll() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("cat", 3.50, 10));

        Mockito.when(dao.readAll()).thenReturn(items);

        assertEquals(items, controller.readAll());

        Mockito.verify(dao, Mockito.times(1)).readAll();
    }

    @Test
    public void testUpdate() {
        Item updated = new Item(1L, "cat", 3.50, 10);

        Mockito.when(this.utils.getLong()).thenReturn(1L);
        Mockito.when(this.utils.getString()).thenReturn(updated.getItemName());
        Mockito.when(this.utils.getDouble()).thenReturn(updated.getPrice());
        Mockito.when(this.utils.getInteger()).thenReturn(updated.getStock());

        Mockito.when(this.dao.update(updated)).thenReturn(updated);

        assertEquals(updated, this.controller.update());

        Mockito.verify(this.utils, Mockito.times(1)).getLong();
        Mockito.verify(this.utils, Mockito.times(1)).getString();
        Mockito.verify(this.utils, Mockito.times(1)).getDouble();
        Mockito.verify(this.utils, Mockito.times(1)).getInteger();
        Mockito.verify(this.dao, Mockito.times(1)).update(updated);
    }

    @Test
    public void testDelete() {
        final long ID = 1L;

        Mockito.when(utils.getLong()).thenReturn(ID);
        Mockito.when(dao.delete(ID)).thenReturn(1);

        assertEquals(1L, this.controller.delete());

        Mockito.verify(utils, Mockito.times(1)).getLong();
        Mockito.verify(dao, Mockito.times(1)).delete(ID);
    }

}
