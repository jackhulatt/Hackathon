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

import com.qa.qommon.persistence.domain.Login;
import com.qa.qommon.utils.DBUtils;

public class LoginDAO implements Dao<Login> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Login modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("admin_id");
		String username = resultSet.getString("username");
		String password = resultSet.getString("password");
		return new Login();
	}


	@Override
	public List<Login> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM administrator");) {
			List<Login> logins = new ArrayList<>();
			while (resultSet.next()) {
				logins.add(modelFromResultSet(resultSet));
			}
			return logins;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Login readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM administrator ORDER BY admin_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}


	@Override
	public Login read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM administrator WHERE username=? AND password=?");) {
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
    public Login create(Login t) {
        return null;
    }


    @Override
    public Login update(Login t) {
        return null;
    }


    @Override
    public int delete(long id) {
        return 0;
    }




}
