package com.core;

import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Sale {
	public static final String DATABASE_TABLE = "SaleLadger";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists SaleLadger (_id integer primary key autoincrement , customer_id integer, date long not null, sale_line_items text not null);";
   
    public static final String COL_CUSTOMER_ID = "customer_id";
    public static final String COL_DATE = "date";
    public static final String COL_SALE_LINE_ITEMS = "sale_line_items";
    public static final String COL_PAYMENT = "payment";
    
    private int id;
    private int customer;
    private long date;
    private ArrayList<Integer> saleLineItemsId;
    private String payment;
    
    public Sale(Customer customer){
    	saleLineItemsId = new ArrayList<Integer>();
    	this.setCustomer(customer);
    }
    
    public Sale(){
    	saleLineItemsId = new ArrayList<Integer>();
    }
    
    public Sale(int customerId){
    	saleLineItemsId = new ArrayList<Integer>();
    	customer = customerId;
    }
    
    public boolean addSaleLineItem(SaleLineItem sli){
    	saleLineItemsId.add(sli.getId());
    	return true;
    }
    
    public boolean addSaleLineItemBy(int id){
    	saleLineItemsId.add(id);
    	return true;
    }

    public String getSaleLineItemString(){
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0 ; i < saleLineItemsId.size() ; i++)
    		sb.append(saleLineItemsId.get(i)).append(" ");
    	return sb.toString();
    }

	public Customer getCustomer() {
		// TODO : get Customer form customer ID.
		return null;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer.getId();
	}

	public long getDateAsLong() {
		return date;
	}
	
	public Date getDate() {
		Date d = new Date();
		d.setTime(date);
		return d;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Payment getPayment() {
		return new Payment(payment);
	}

	public void setPayment(Payment payment) {
		this.payment = payment.toString();
	}
	
	public void setPayment(String payment) {
		this.payment = payment;
	}
}
