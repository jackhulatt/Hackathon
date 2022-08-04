package com.qa.qommon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {

	public static final Logger LOGGER = LogManager.getLogger();

	public static void main(String[] args) {
		App app = new App();  // initialise App class
		app.qommonSystem(); // start the qommon System interface
		//LOGGER.info("SO LONG!");
	}

}
