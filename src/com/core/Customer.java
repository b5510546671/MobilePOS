package com.core;

import java.io.Serializable;
import java.util.Date;

/**
 * The customer who buy product.
 * @version 2013.11.26
 *
 */
public class Customer implements Serializable {
    public static final String DATABASE_TABLE = "CustomerBook";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists CustomerBook (_id integer primary key autoincrement , name text not null, email text not null, registerDate long not null);";
    
    public static final String COL_NAME = "name";
    public static final String COL_EMAIL = "email";
    public static final String COL_DATE = "registerDate";
	
    /** The id of Customer */
	private int id=0;
	/** The name of Customer */
	private String name="none";
	/** The register date of Customer */
	private Date registerDate;
	/** The email of Customer */
	private String email;
	
	/**
	 * Creates a Customer and initializes id, name, register date and email.
	 * @param id the initial id of Customer
	 * @param name the initial name of Customer
	 * @param regsterDate the register date of Customer
	 * @param email the initial email of Customer
	 * */
	public Customer(int id,String name,Date registerDate, String email) {
		this.id = id;
		this.name = name;
		this.registerDate = registerDate;
		this.email = email;
	}
	
	/**
	 * Get the id of Customer.
	 * @return id the customer's id
	 */
	public int getID(){
		return id;
	}
	
	/**
	 * Get the name of Customer.
	 * @return name the customer's name
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Sets the name of Customer.
	 * @param name the name to be set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Get the register date.
	 * @return registerDate the date of register as Date object
	 */
	public Date getRegisterDate()
	{
		return registerDate;
	}

	/**
	 * Get the email of Customer.
	 * @return email the customer's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of Customer.
	 * @param email the email to be set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
}