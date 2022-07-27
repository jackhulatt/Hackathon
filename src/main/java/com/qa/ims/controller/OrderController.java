package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

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
        Order order = orderDAO.create(new Order(customerId, quantity, itemId));
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
        return null;
    }

    /**
     * Deletes an order and order_items by the id of the order
     * 
     * @return
     */
    @Override
    public int delete() {
        LOGGER.info("Please enter the id of the order you would like to delete");
        Long id = utils.getLong();
        return orderDAO.delete(id);
    }

}
