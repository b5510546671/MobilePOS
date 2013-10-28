package com.core;

import java.util.ArrayList;

public class Sale {
	public static final String DATABASE_TABLE = "SaleLadger";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists SaleLadger (_id integer primary key autoincrement , customer_id integer, date long not null, sale_line_items text not null);";
   
    public static final String COL_CUSTOMER_ID = "customer_id";
    public static final String COL_DATE = "date";
    public static final String COL_SALE_LINE_ITEMS = "sale_line_items";
    
    private int id;
    private Customer customer;
    private long date;
    private ArrayList<SaleLineItem> saleLineItems;
    
    public Sale(Customer customer){
    	saleLineItems = new ArrayList<SaleLineItem>();
    	this.setCustomer(customer);
    }
    
    public boolean addSaleLineItem(SaleLineItem sli){
    	saleLineItems.add(sli);
    	return true;
    }

    public String getSaleLineItemString(){
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0 ; i < saleLineItems.size() ; i++)
    		sb.append(saleLineItems.get(i)).append(" ");
    	return sb.toString();
    }

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public long getDate() {
		return date;
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
}
