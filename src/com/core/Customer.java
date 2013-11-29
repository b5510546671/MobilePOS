package com.core;

import java.io.Serializable;
import java.util.Date;

public class Customer implements Serializable {
    public static final String DATABASE_TABLE = "CustomerBook";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists CustomerBook (_id integer primary key autoincrement , name text not null, email text not null, registerDate long not null);";
    
    public static final String COL_NAME = "name";
    public static final String COL_EMAIL = "email";
    public static final String COL_DATE = "registerDate";
	
	private int id=0;
	private String name="none";
	private Date registerDate;
	private String email;
	
	public Customer(int id,String name,Date registerDate, String email) {
		this.id = id;
		this.name = name;
		this.registerDate = registerDate;
		this.email = email;
	}
	
	public int getID(){
		return id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public Date getRegisterDate()
	{
		return registerDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}