package com.qa.qommon.controller;

import com.qa.qommon.persistence.dao.OrderDAO;
import com.qa.qommon.persistence.domain.Order;
import com.qa.qommon.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

    public static final Logger LOGGER = LogManager.getLogger();

    private OrderDAO orderDAO;
    private Utils utils;

    public OrderController(OrderDAO orderDAO, Utils utils) {
        super();
        this.orderDAO = orderDAO;
        this.utils = utils;
    }

    /**
     * Creates a customer by taking in user input
     */
    @Override
    public Order create() {
        // Float totalAmount = price * Quantity;
        LOGGER.info("Please enter a Customer ID you would like to make an order with:");
        long customerId = utils.getLong();
        LOGGER.info("Please enter the item ID for the items you wish you add");
        long itemId = utils.getLong();
        LOGGER.info("Please enter quantity of items");
        int quantity = utils.getInteger();
        Order order = orderDAO.create(new Order(customerId, itemId, quantity));
        LOGGER.info("Order created");
        return order;
    }

    @Override
    public List<Order> readAll() {
        List<Order> orders = orderDAO.readAll();
        for (Order order : orders) {
            LOGGER.info(order);
        }
        return orders;
    }

    @Override
    public Order update() {
        LOGGER.info("Please enter a Order ID you would like to add an item to:  ");
        long orderId = utils.getLong();
        LOGGER.info("Please enter the item ID for the items you wish you add to the order");
        long itemId = utils.getLong();
        LOGGER.info("Please enter quantity of items");
        int quantity = utils.getInteger();
        Order addItem = orderDAO.update(new Order(quantity, orderId, itemId));
        return addItem;
    }

    /**
     * Deletes an order and order_items by the id of the order And also option to
     * delete an item from an existing order by id
     * 
     * @return
     */
    @Override
    public int delete() {
        LOGGER.info("Please select which the appropriate option\nDELETE ORDER\nDELETE ITEM");
        String action = utils.getString();
        action = action.toUpperCase();
        switch (action) {
            case "DELETE ORDER":
                LOGGER.info("Select the order you wish to delete");
                Long delete = utils.getLong();
                orderDAO.delete(delete);
                LOGGER.info("Order has been deleted");
                break;
            case "DELETE ITEM":
                LOGGER.info("enter the orderId you wish to delete and item from");
                Long orderItem = utils.getLong();
                LOGGER.info("enter the itemId you wish to delete from the order");
                Long itemDelete = utils.getLong();
                orderDAO.itemDelete(orderItem, itemDelete);
                break;
            default:
                LOGGER.info("Incorrect input, please try again");
                return 0;
        }
        return 0;

    }

}
