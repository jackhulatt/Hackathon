DROP TABLE item_orders;
DROP TABLE  orders;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS customers;


CREATE TABLE IF NOT EXISTS customers (
    id INT(11) NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `items` (
	`item_id` INT(11) NOT NULL auto_increment,
    `item_name` VARCHAR(50) DEFAULT NULL,
    `price` DECIMAL DEFAULT NULL,
    `stock` INT DEFAULT NULL,
    PRIMARY KEY (`item_id`)
);

CREATE TABLE IF NOT EXISTS orders(
	`orders_id` INT(11) NOT NULL auto_increment,
    `fk_customer_id` INT NOT NULL,
    PRIMARY KEY (`orders_id`),
	FOREIGN KEY (`fk_customer_id`) REFERENCES `customers`(`id`)
);

CREATE TABLE IF NOT EXISTS `item_orders`(
	`item_orders_id` INT(11) NOT NULL auto_increment,
    `fk_item_id` INT NOT NULL,
	`fk_order_id` INT NOT NULL,
    `item_quantity` INT NOT NULL,
    PRIMARY KEY (`item_orders_id`),
	FOREIGN KEY (`fk_item_id`) REFERENCES `items`(`item_id`),
	FOREIGN KEY (`fk_order_id`) REFERENCES `orders`(`orders_id`)
);
