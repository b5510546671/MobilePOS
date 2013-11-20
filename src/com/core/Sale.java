 package com.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Sale implements Serializable {
	public static final String DATABASE_TABLE = "SaleLadger";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists SaleLadger (_id integer primary key autoincrement , customer_id integer, payment_id text not null, date long not null);";
   
    public static final String COL_CUSTOMER_ID = "customer_id";
    public static final String COL_DATE = "date";
    public static final String COL_PAYMENT_ID = "payment_id";
    
	
	private List<Item> items = new ArrayList<Item>();
	private int id;
	private Customer customer;
	private Date date;
	private Payment payment;
	
	public Sale(int id,List<Item> items,Customer customer,Payment payment , Date date) {
		this.id = id;
		this.setItems(items);
		this.date = date;
		this.customer = customer;
		payment.setPrice((float)getTotalPrice());
		this.payment = payment;
	}
	
	public int getID()
	{
		return id;
	}
	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}
	public Customer getCustomer()
	{
		return this.customer;
	}
	
	public Payment getPayment()
	{
		return this.payment;
	}
	
	public void setPayment(Payment payment)
	{
		this.payment = payment;
	}
	
	public Date getDate(){
		return this.date;
	}
	
	public double getTotalPrice()
	{
		int totalPrice = 0;
		for(Item i : getItems())
		{
			totalPrice+= i.getItemDescription().getPrice();
		}
		return totalPrice;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
}
