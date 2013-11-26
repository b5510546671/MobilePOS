package com.core;

import java.io.Serializable;
import java.io.StringWriter;

/**
 * The Payment.
 */
public class Payment implements Serializable {
	public static final String DATABASE_TABLE = "PaymentBook";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists PaymentBook (_id integer primary key autoincrement , input_money real not null, price real not null);";
   
    public static final String COL_INPUT_MONEY = "input_money";
    public static final String COL_PRICE = "price";
	
    /** The input of Payment */
	private double input;
	/** The price of Payment */
	private double price;
	/** The id of Payment */
	private int id=0;
	
	/**
	 * Creates a Payment with initial id, price and input.
	 * @param id the initial id of the Payment
	 * @param price the initial price of the Payment
	 * @param input the initial input of the Payment
	 */
	public Payment(int id,double price,double input) {
		this.id = id;
		this.input = input;
		this.price = price;
	}

	/**
	 * Returns the id.
	 * @return id the id
	 */
	public int getID()
	{
		return this.id;
	}
	
	/**
	 * Returns the price.
	 * @return price the price
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * Sets the price.
	 * @param price the price for setting
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Returns the input.
	 * @return input the input
	 */
	public double getInput() {
		return input;
	}

	/**
	 * Sets the input.
	 * @param input the input for setting
	 */
	public void setInput(double input) {
		this.input = input;
	}
	
	/**
	 * Returns the change.
	 * @return change the change
	 */
	public double getChange()
	{
		return input - price;
	}
}
