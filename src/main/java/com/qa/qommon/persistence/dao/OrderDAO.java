package com.qa.qommon.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.qommon.persistence.domain.Order;
import com.qa.qommon.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long orderId = resultSet.getLong("orders_id");
        Long customerId = resultSet.getLong("fk_customer_id");
        // int quantity = resultSet.getInt("item_quantity");
        return new Order(orderId, customerId);
    }

    public Order readLatest() {
        try (Connection connection = DBUtils.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY orders_id DESC LIMIT 1");) {
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Creates a customer in the database
     * 
     * @param customer - takes in a customer object. id will be ignored
     */
    @Override
    public Order create(Order order) {
        try (Connection connection = DBUtils.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement(
                                "INSERT INTO orders(fk_customer_id) VALUES (?)");) {
            statement.setLong(1, order.getCustomerId());
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        try (Connection connection = DBUtils.getInstance().getConnection();
                PreparedStatement statement2 = connection
                        .prepareStatement(
                                "INSERT INTO item_orders(fk_item_id, fk_order_id, item_quantity) VALUES (?, ?, ?)");) {
            statement2.setLong(1, order.getItemId());
            statement2.setLong(2, readLatest().getOrderId());
            statement2.setInt(3, order.getQuantity());
            statement2.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public Order total(ResultSet resultSet) throws SQLException {
        float totalAmount = resultSet.getFloat("Total_amount");
        return new Order(totalAmount);
    }

    @Override
    public List<Order> readAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DBUtils.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM orders o"
                        + " join item_orders oi"
                        + " on o.orders_id=oi.fk_order_id"
                        + " join items i"
                        + " on i.item_id=fk_item_id"
                        + " join customers c"
                        + " on o.fk_customer_id");) {
            while (resultSet.next()) {
                orders.add(modelFromResultSet(resultSet));
                LOGGER.info("\n----------- " + "Order ID: " + resultSet.getLong("o.orders_id") + " -----------\n"
                        + " | Customer name: "
                        + resultSet.getString("c.first_name") + " " + resultSet.getString("c.surname")
                        + " ----- Item ID: "
                        + resultSet.getLong("i.item_id") + " ----- Item Name: "
                        + resultSet.getString("i.item_name") + " ----- Price: "
                        + resultSet.getDouble("i.price") + " ----- Quantity: "
                        + resultSet.getInt("oi.item_quantity")
                        + " ----- item_order ID: " + resultSet.getLong("oi.fk_order_id") + " |");
            }
        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }

        try (Connection connection = DBUtils.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT items.price*item_orders.item_quantity as Total_Amount " +
                                "FROM items,item_orders " +
                                "WHERE items.item_id=item_orders.fk_item_id;");) {
            while (resultSet.next()) {
                orders.add(total(resultSet));
                LOGGER.info("Total cost of order: " + resultSet.getFloat("Total_amount"));
            }
        } catch (SQLException e) {
            LOGGER.info("test");
        }
        return new ArrayList<>();
    }

    @Override
    public Order read(Long id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT * FROM orders o" + " join item_orders oi"
                                + " on o.orders_id=oi.fk_order_id" + " join items i" + " on i.item_id=fk_item_id");) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery();) {
                resultSet.next();
                return modelFromResultSet(resultSet);
            }
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Order update(Order t) {
        try (Connection connection = DBUtils.getInstance().getConnection();
                PreparedStatement statement2 = connection
                        .prepareStatement(
                                "INSERT INTO item_orders(fk_item_id, fk_order_id, item_quantity) VALUES (?, ?, ?)");) {
            statement2.setLong(1, t.getItemId());
            statement2.setLong(2, t.getOrderId());
            statement2.setInt(3, t.getQuantity());
            statement2.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public int delete(long id) {

        try (Connection connection = DBUtils.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("DELETE FROM item_orders WHERE fk_order_id = ?");) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        try (Connection connection = DBUtils.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("DELETE FROM orders WHERE orders_id = ?");) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }

        return 0;
    }

    public int itemDelete(Long orderItem, Long itemDelete) {
        try (Connection connection = DBUtils.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement(
                                "DELETE FROM item_orders WHERE fk_order_id = ? AND fk_item_id = ? LIMIT 1");) {
            statement.setLong(1, orderItem);
            statement.setLong(2, itemDelete);
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }
}
