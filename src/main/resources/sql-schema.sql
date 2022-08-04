drop database if exists qommon;
CREATE DATABASE IF NOT EXISTS qommon;

USE qommon;

CREATE TABLE IF NOT EXISTS administrator(
	admin_id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(40) NOT NULL,
    pass VARCHAR(40) NOT NULL,
    accessLevel VARCHAR(40) NOT NULL,
	PRIMARY KEY (admin_id)
);
INSERT INTO administrator (username,pass,accessLevel) VALUES ("Admin","password","top");

CREATE TABLE IF NOT EXISTS driver(
	driver_id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(40) NOT NULL,
    surname VARCHAR(40) NOT NULL,
    vehicle_reg varchar(15),
    PRIMARY KEY (driver_id)
);
CREATE TABLE IF NOT EXISTS orders(
	order_id INT NOT NULL AUTO_INCREMENT,
    product_type varchar(10),
    quantity int,
    PRIMARY KEY (order_id)
);

CREATE TABLE IF NOT EXISTS routes(
	route_id INT NOT NULL AUTO_INCREMENT,
    distance int,
    location varchar(100),
    PRIMARY KEY (route_id)
);

CREATE TABLE IF NOT EXISTS driver_routes(
    driver_route_id int not null auto_increment,
    fk_driver_id int not null,
    fk_route_id int not null, 
    FOREIGN KEY(fk_driver_id) REFERENCES driver(driver_id),
    FOREIGN KEY(fk_route_id) REFERENCES routes(route_id),
    PRIMARY KEY (driver_route_id)
);
CREATE TABLE IF NOT EXISTS driver_orders(
    driver_order_id int not null auto_increment,
    fk_driver_id int not null,
    fk_order_id int not null, 
    FOREIGN KEY(fk_driver_id) REFERENCES driver(driver_id),
    FOREIGN KEY(fk_order_id) REFERENCES orders(order_id),
    PRIMARY KEY (driver_order_id)
);
