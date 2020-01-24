package com.vpm;

import java.time.LocalDate;

/**
 * POJO class
 * The fields data type should correspond with the data type in DB table
 * The constructor has the required fields
 * The id field is required and is a primary key in a database
 * The arrivalDate is required in a database
 * 
 * @author MoioM
 */
public class ValetParking {
	private int id;
	private String name;
	private String registration;
	private LocalDate arrivalDate;
	
	/**
	 * 
	 * @param id
	 * @param arrivalDate
	 */
	public ValetParking(int id, LocalDate arrivalDate) {
		super();
		this.id = id;
		this.arrivalDate = arrivalDate;
	}
		
	/**
	 * @param id
	 * @param name
	 * @param registration
	 * @param arrivalDate
	 */
	public ValetParking(int id, String name, String registration, LocalDate arrivalDate) {
		super();
		this.id = id;
		this.name = name;
		this.registration = registration;
		this.arrivalDate = arrivalDate;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	@Override
	public String toString() {
		return "ValetParking [id=" + id + ", name=" + name + ", registration=" + registration + ", arrivalDate="
				+ arrivalDate + "]";
	}
		
}
