package com.core;

import java.io.Serializable;
import java.io.StringWriter;


/**
 * @author Apiwat //TODO
 * payment data of each sale (for future include tax or more information).
 */
public class Payment implements Serializable {
	public static final String DATABASE_TABLE = "PaymentBook";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists PaymentBook (_id integer primary key autoincrement , input_money real not null, price real not null);";
   
    public static final String COL_INPUT_MONEY = "input_money";
    public static final String COL_PRICE = "price";
	
	private double input;
	private double price;
	private int id=0;
	
	public Payment(int id,double price,double input) {
		this.id = id;
		this.input = input;
		this.price = price;
	}

	public int getID()
	{
		return this.id;
	}
	public double getPrice() {
		return this.price;
	}


	public void setPrice(double price) {
		this.price = price;
	}

	public double getInput() {
		return input;
	}


	public void setInput(double input) {
		this.input = input;
	}
	
	public double getChange()
	{
		return input - price;
	}
}
