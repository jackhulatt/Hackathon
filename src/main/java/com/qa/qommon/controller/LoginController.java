package com.qa.qommon.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.qommon.persistence.dao.LoginDAO;
import com.qa.qommon.persistence.domain.Login;
import com.qa.qommon.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class LoginController implements CrudController<Login> {

	public static final Logger LOGGER = LogManager.getLogger();

	private LoginDAO loginDAO;
	private Utils utils;

	public LoginController(LoginDAO loginDAO, Utils utils) {
		super();
		this.loginDAO = loginDAO;
		this.utils = utils;
	}

	
	@Override
	public List<Login> readAll() {
		List<Login> logins = loginDAO.readAll();
		for (Login login : logins) {
			LOGGER.info(login);
		}
		return logins;
	}

    @Override
    public Login create() {
        return null;
    }


    @Override
    public Login update() {
        return null;
    }


    @Override
    public int delete() {
        return 0;
    }


}
