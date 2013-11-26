package com.core;

import com.database.CashierBookDB;

import android.content.Context;

/**
 * The user who using the POS.
 * @version 2013.11.26
 * 
 */
public class Cashier {
    public static final String DATABASE_TABLE = "CashierBook";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists CashierBook (_id integer primary key autoincrement , name text not null, user text not null , password text not null);";
    
    public static final String COL_NAME = "name";
    public static final String COL_USER = "user";
    public static final String COL_PASS = "password";
    
    /** The id of Cashier */
    private int id;
    /** The name of Cashier */
    private String name;
    /** The user of Cashier */
    private String user;
    /** The password of Cashier */
    private String password;
    
    /** Create a new Cashier. */
    public Cashier() {	}
    
    /**
     * Get the name of the Cashier.
     * @return name name of cashier
     */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the name of the Cashier.
	 * @param name name to be set to cashier
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the user of the Cashier.
	 * @return user the user
	 */
	public String getUser() {
		return user;
	}
	
	/**
	 * Sets the user of the Cashier.
	 * @param user the user to be set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Get the password of the Cashier.
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the Cashier.
	 * @param password the password to be set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get the id of the Cashier.
	 * @return id of cashier
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of Cashier.
	 * @param id the id to be set to cashier
	 */
	public void setId(int id) {
		this.id = id;
	}
	
}
