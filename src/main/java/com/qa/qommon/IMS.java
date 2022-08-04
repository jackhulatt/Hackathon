package com.qa.qommon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.qommon.controller.Action;
import com.qa.qommon.controller.CrudController;
import com.qa.qommon.controller.DriverController;
import com.qa.qommon.controller.OrderController;
import com.qa.qommon.persistence.dao.DriverDAO;
import com.qa.qommon.persistence.dao.OrderDAO;
import com.qa.qommon.persistence.domain.Domain;
import com.qa.qommon.utils.DBUtils;
import com.qa.qommon.utils.Utils;

public class IMS {

	public static final Logger LOGGER = LogManager.getLogger();

	private final DriverController drivers;
	
	private final OrderController orders;
	private final Utils utils;

	public IMS() {
		this.utils = new Utils();
		final DriverDAO drivDAO = new DriverDAO();
		this.drivers = new DriverController(drivDAO, utils);
		final OrderDAO ordDAO = new OrderDAO();
		this.orders = new OrderController(ordDAO, utils);
	}

	public void imsSystem() {
		LOGGER.info("Welcome to the QOMMON Management System!");
		DBUtils.connect();

		Domain domain = null;
		do {
			LOGGER.info("Which entity would you like to use?");
			Domain.printDomains();

			domain = Domain.getDomain(utils);

			domainAction(domain);

		} while (domain != Domain.STOP);
	}

	private void domainAction(Domain domain) {
		boolean changeDomain = false;
		do {

			CrudController<?> active = null;
			switch (domain) {
				case DRIVER:
					active = this.drivers;
					break;
				case ROUTE:
					break;
				case ORDER:
					active = this.orders;
					break;
				case STOP:
					return;
				default:
					break;
			}

			LOGGER.info(() -> "What would you like to do with " + domain.name().toLowerCase() + ":");

			Action.printActions();
			Action action = Action.getAction(utils);

			if (action == Action.RETURN) {
				changeDomain = true;
			} else {
				doAction(active, action);
			}
		} while (!changeDomain);
	}

	public void doAction(CrudController<?> crudController, Action action) {
		switch (action) {
			case CREATE:
				crudController.create();
				break;
			case READ:
				crudController.readAll();
				break;
			case UPDATE:
				crudController.update();
				break;
			case DELETE:
				crudController.delete();
				break;
			case RETURN:
				break;
			default:
				break;
		}
	}

}
