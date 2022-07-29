package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class ItemController implements CrudController<Item> {

    public static final Logger LOGGER = LogManager.getLogger();

    private ItemDAO itemDAO;
    private Utils utils;

    public ItemController(ItemDAO itemDAO, Utils utils) {
        super();
        this.itemDAO = itemDAO;
        this.utils = utils;
    }

    /**
     * Reads all items to the logger
     */
    @Override
    public List<Item> readAll() {
        List<Item> items = itemDAO.readAll();
        for (Item item : items) {
            LOGGER.info(item);
        }
        return items;
    }

    /**
     * Creates a customer by taking in user input
     */
    @Override
    public Item create() {
        LOGGER.info("Please enter a item name");
        String itemName = utils.getString();
        LOGGER.info("Please enter a price");
        double price = utils.getDouble();
        LOGGER.info("Please enter a stock");
        Integer stock = utils.getInteger();
        Item item = itemDAO.create(new Item(itemName, price, stock));
        LOGGER.info("Item created\n");
        return item;
    }

    /**
     * Updates an existing customer by taking in user input
     */
    @Override
    public Item update() {
        LOGGER.info("Please enter the id of the item you would like to update");
        Long itemId = utils.getLong();
        LOGGER.info("Please enter a item name");
        String itemName = utils.getString();
        LOGGER.info("Please enter a new price");
        double price = utils.getDouble();
        LOGGER.info("Please enter a new stock");
        Integer stock = utils.getInteger();
        Item item = itemDAO.update(new Item(itemId, itemName, price, stock));
        LOGGER.info("Customer Updated");
        return item;
    }

    /**
     * Deletes an existing customer by the id of the customer
     * 
     * @return
     */
    @Override
    public int delete() {
        LOGGER.info("Please enter the id of the item you would like to delete");
        Long id = utils.getLong();
        return itemDAO.delete(id);
    }

}
