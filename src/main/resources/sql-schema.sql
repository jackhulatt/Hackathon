drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) NOT NULL,
    `surname` VARCHAR(40) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items` (
	`item_id` INT(11) NOT NULL auto_increment,
    `item_name` VARCHAR(50) NOT NULL,
    `price` DECIMAL NOT NULL,
    `stock` INT,
    PRIMARY KEY (`item_id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders`(
	`orders_id` INT(11) NOT NULL auto_increment,
    `fk_customer_id` INT NOT NULL,
    PRIMARY KEY (`orders_id`),
	FOREIGN KEY (`fk_customer_id`) REFERENCES customers(`id`)
);
CREATE TABLE IF NOT EXISTS `ims`.`item_orders`(
    `fk_item_id` INT NOT NULL,
	`fk_order_id` INT NOT NULL,
    `item_quantity` INT NOT NULL,
    PRIMARY KEY (`fk_item_id`,`fk_order_id`),
	FOREIGN KEY (`fk_item_id`) REFERENCES items(`item_id`),
	FOREIGN KEY (`fk_order_id`) REFERENCES orders(`orders_id`)
);