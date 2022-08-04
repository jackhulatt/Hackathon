package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Driver {

	private Long id;
	private String firstName;
	private String surname;

	public Driver(String firstName, String surname) {
		this.setFirstName(firstName);
		this.setSurname(surname);
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

	@Override
	public String toString() {
		return "{" +
				" id='" + getId() + "'" +
				", firstName='" + getFirstName() + "'" +
				", surname='" + getSurname() + "'" +
				"}";
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Driver)) {
			return false;
		}
		Driver customer = (Driver) o;
		return Objects.equals(id, customer.id) && Objects.equals(firstName, customer.firstName)
				&& Objects.equals(surname, customer.surname);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, surname);
	}

}
