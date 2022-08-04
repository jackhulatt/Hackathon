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

import com.qa.qommon.persistence.domain.Driver;
import com.qa.qommon.utils.DBUtils;

public class DriverDAO implements Dao<Driver> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Driver modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("driver_id");
		String firstName = resultSet.getString("first_name");
		String surname = resultSet.getString("surname");
		String vehicleReg = resultSet.getString("vehicle_reg");
		return new Driver(id, firstName, surname, vehicleReg);
	}

	/**
	 * Reads all customers from the database
	 * 
	 * @return A list of customers
	 */
	@Override
	public List<Driver> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM driver");) {
			List<Driver> drivers = new ArrayList<>();
			while (resultSet.next()) {
				drivers.add(modelFromResultSet(resultSet));
			}
			return drivers;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Driver readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM driver ORDER BY driver_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a customer in the database
	 * 
	 * @param customer - takes in a customer object. id will be ignored
	 */
	@Override
	public Driver create(Driver driver) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO driver(first_name, surname, vehicle_reg) VALUES (?, ?, ?)");) {
			statement.setString(1, driver.getFirstName());
			statement.setString(2, driver.getSurname());
			statement.setString(3, driver.getVehicleReg());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Driver read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM driver WHERE driver_id = ?");) {
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

	/**
	 * Updates a customer in the database
	 * 
	 * @param customer - takes in a customer object, the id field will be used to
	 *                 update that customer in the database
	 * @return
	 */
	@Override
	public Driver update(Driver driver) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE driver SET first_name = ?, surname = ? WHERE driver_id = ?");) {
			statement.setString(1, driver.getFirstName());
			statement.setString(2, driver.getSurname());
			statement.setLong(3, driver.getId());
			statement.executeUpdate();
			return read(driver.getId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes a customer in the database
	 * 
	 * @param id - id of the customer
	 */
	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM driver WHERE driver_id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
		return 1;
	}

}
