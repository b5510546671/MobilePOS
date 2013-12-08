package com.core;

import java.io.Serializable;
import java.util.Date;


import android.content.Context;

public class Cashier implements Serializable{
	
	  public static final String DATABASE_TABLE = "CashierBook";
	  public static final int DATABASE_VERSION = 1;
	public static final String TABLE_CREATE =
	     "create table if not exists CashierBook (_id integer primary key autoincrement , name text not null, user text not null, password text not null);";
	    
	public static final String COL_NAME = "name";
	public static final String COL_USERNAME = "user";
	public static final String COL_PASSWORD = "password";
    
    private int id;
    private String name;
    private String username;
    private String password;
    
    public Cashier(int id,String name,String userName,String passWord) {
    	this.id  =id;
    	this.name = name;
    	this.username = userName;
    	this.password = passWord;
	}
    
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String user) {
		this.username = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
