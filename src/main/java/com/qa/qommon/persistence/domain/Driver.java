package com.qa.qommon.persistence.domain;

import java.util.Objects;

public class Driver {

	private Long id;
	private String firstName;
	private String surname;
	private String vehicleReg;
	

	public Driver(Long id, String firstName, String surname, String vehicleReg) {
		this.id = id;
		this.firstName = firstName;
		this.surname = surname;
		this.vehicleReg = vehicleReg;
	}
	public Driver(String firstName, String surname, String vehicleReg) {
		this.setFirstName(firstName);
		this.setSurname(surname);
		this.setVehicleReg(vehicleReg);
	}

	public Driver(Long id, String firstName, String surname) {
		this.setId(id);
		this.setFirstName(firstName);
		this.setSurname(surname);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getVehicleReg() {
		return this.vehicleReg;
	}

	public void setVehicleReg(String vehicleReg) {
		this.vehicleReg = vehicleReg;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Driver)) {
			return false;
		}
		Driver driver = (Driver) o;
		return Objects.equals(id, driver.id) && Objects.equals(firstName, driver.firstName) && Objects.equals(surname, driver.surname) && Objects.equals(vehicleReg, driver.vehicleReg);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, surname, vehicleReg);
	}

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", firstName='" + getFirstName() + "'" +
			", surname='" + getSurname() + "'" +
			", vehicleReg='" + getVehicleReg() + "'" +
			"}";
	}

}
