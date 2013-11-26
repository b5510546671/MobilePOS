package com.core;

import java.io.Serializable;
import java.io.StringWriter;

/**
 * The Payment class.
 * @version 2013.11.26
 */
public class Payment implements Serializable {
	public static final String DATABASE_TABLE = "PaymentBook";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists PaymentBook (_id integer primary key autoincrement , input_money real not null, price real not null);";
   
    public static final String COL_INPUT_MONEY = "input_money";
    public static final String COL_PRICE = "price";
	
    /** The input of Payment. */
	private double input;
	/** The price of Payment. */
	private double price;
	/** The id of Payment. */
	private int id=0;
	
	/**
	 * Creates a Payment and initializes id, price and input.
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
	 * Get the payment id.
	 * @return id the payment's id
	 */
	public int getID()
	{
		return this.id;
	}
	
	/**
	 * Get the price.
	 * @return price the price in payment
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * Sets the price.
	 * @param price the price to be set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Get the input.
	 * @return input the input in payment
	 */
	public double getInput() {
		return input;
	}

	/**
	 * Sets the input.
	 * @param input the input to be set
	 */
	public void setInput(double input) {
		this.input = input;
	}
	
	/**
	 * Get the change.
	 * @return change the change amount in payment
	 */
	public double getChange()
	{
		return input - price;
	}
}
