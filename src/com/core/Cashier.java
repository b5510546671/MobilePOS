package com.core;

import com.database.CashierBookDB;

import android.content.Context;

/**
 * The user who using the POS.
 * */
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
     * Returns the name of the Cashier.
     * @return name the name
     */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the Cashier.
	 * @param name the name for setting
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the user of the Cashier.
	 * @return user the user
	 */
	public String getUser() {
		return user;
	}
	
	/**
	 * Sets the user of the Cashier.
	 * @param user the user for setting
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Returns the password of the Cashier.
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the Cashier.
	 * @param password the password for setting
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns the id of the Cashier.
	 * @return id the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of Cashier.
	 * @param id the new id for setting
	 */
	public void setId(int id) {
		this.id = id;
	}
	
}
