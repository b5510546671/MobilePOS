 package com.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Sale
 * @version 2013.11.26
 */
public class Sale implements Serializable {
	public static final String DATABASE_TABLE = "SaleLadger";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists SaleLadger (_id integer primary key autoincrement , customer_id integer, payment_id text not null, date long not null);";
   
    public static final String COL_CUSTOMER_ID = "customer_id";
    public static final String COL_DATE = "date";
    public static final String COL_PAYMENT_ID = "payment_id";
    
    /** The list of items in Sale. */
	private List<Item> items = new ArrayList<Item>();
	/** The id of Sale. */
	private int id;
	/** The customer of Sale. */
	private Customer customer;
	/** The date of Sale. */
	private Date date;
	/** The payment of Sale. */
	private Payment payment;
	
	/**
	 * Creates a Sale and initilzes id, list of items, customer, payment and date.
	 * @param id the initial id of Sale
	 * @param items the initial list of items of Sale
	 * @param customer the initial customer of Sale
	 * @param payment the initial payment of Sale
	 * @param date the initial date of Sale
	 */
	public Sale(int id,List<Item> items,Customer customer,Payment payment , Date date) {
		this.id = id;
		this.setItems(items);
		this.date = date;
		this.customer = customer;
		payment.setPrice((float)getTotalPrice());
		this.payment = payment;
	}
	
	/**
	 * Get the id of Sale.
	 * @return id the sale id
	 */
	public int getID()
	{
		return id;
	}
	
	/**
	 * Sets the customer of Sale.
	 * @param customer the customer to be set
	 */
	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}
	
	/**
	 * Get the customer of Sale.
	 * @return customer the customer in that sale
	 */
	public Customer getCustomer()
	{
		return this.customer;
	}
	
	/**
	 * Get the payment of Sale.
	 * @return payment the payment in that sale
	 */
	public Payment getPayment()
	{
		return this.payment;
	}
	
	/**
	 * Sets the payment of Sale.
	 * @param payment the payment to be set
	 */
	public void setPayment(Payment payment)
	{
		this.payment = payment;
	}
	
	/**
	 * Get the date of Sale.
	 * @return date the sale date
	 */
	public Date getDate(){
		return this.date;
	}
	
	/**
	 * Get the total price of Sale.
	 * @return totalPrice the total price of that sale
	 */
	public double getTotalPrice()
	{
		int totalPrice = 0;
		for(Item i : getItems())
		{
			totalPrice+= i.getItemDescription().getPrice();
		}
		return totalPrice;
	}

	/**
	 * Get the list of items in Sale.
	 * @return items the list of items in the sale
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * Sets the list of items in Sale.
	 * @param items the list of item to be set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
}
