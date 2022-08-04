package com.qa.qommon.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.qommon.persistence.dao.DriverDAO;
import com.qa.qommon.persistence.domain.Driver;
import com.qa.qommon.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class DriverController implements CrudController<Driver> {

	public static final Logger LOGGER = LogManager.getLogger();

	private DriverDAO driverDAO;
	private Utils utils;

	public DriverController(DriverDAO driverDAO, Utils utils) {
		super();
		this.driverDAO = driverDAO;
		this.utils = utils;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Driver> readAll() {
		List<Driver> drivers = driverDAO.readAll();
		for (Driver driver : drivers) {
			LOGGER.info(driver);
		}
		return drivers;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Driver create() {
		LOGGER.info("Please enter a first name");
		String firstName = utils.getString();
		LOGGER.info("Please enter a surname");
		String surname = utils.getString();
		LOGGER.info("Please enter the vehicle registration");
		String vehicleReg = utils.getString();
		Driver driver = driverDAO.create(new Driver(firstName, surname, vehicleReg));
		LOGGER.info("Driver created");
		return driver;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Driver update() {
		LOGGER.info("Please enter the id of the driver you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter a first name");
		String firstName = utils.getString();
		LOGGER.info("Please enter a surname");
		String surname = utils.getString();
		LOGGER.info("Please enter the vehicle reg");
		String vehicleReg = utils.getString();
		Driver driver = driverDAO.update(new Driver(id, firstName, surname, vehicleReg));
		LOGGER.info("driver Updated");
		return driver;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the driver you would like to delete");
		Long id = utils.getLong();
		return driverDAO.delete(id);
	}

}
