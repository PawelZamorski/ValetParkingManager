package com.vpm;

import java.time.LocalDate;
import java.util.Date;

public class ValetParking {
	private int id;
	private String name;
	private String registration;
	private LocalDate arrivalDate;
	
	public ValetParking() {
		super();
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

	public void setId(int id) {
		this.id = id;
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

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	@Override
	public String toString() {
		return "ValetParking [id=" + id + ", name=" + name + ", registration=" + registration + ", arrivalDate="
				+ arrivalDate + "]";
	}
		
}
