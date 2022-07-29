INSERT INTO `customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `items` (`item_name`, `price`, `stock`) VALUES ('bat', 3.50, 1 );
INSERT INTO `orders` (`fk_customer_id`) VALUES (1);
INSERT INTO `item_orders` (`fk_item_id`, `fk_order_id`) VALUES (1,1);