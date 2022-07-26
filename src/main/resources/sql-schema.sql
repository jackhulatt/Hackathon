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
    `total_ammount` DECIMAL NOT NULL,
    `date_order_was_made` datetime,
    PRIMARY KEY (`orders_id`),
	FOREIGN KEY (`fk_customer_id`) REFERENCES customers(`id`)
);