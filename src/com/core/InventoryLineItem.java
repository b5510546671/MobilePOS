package com.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Apiwat //TODO
 *	Lot of items create for put it together in to stock inventory at once.
 */
public class InventoryLineItem implements Serializable{
	 public static final String DATABASE_TABLE = "InventoryLineItemBook";
	 public static final int DATABASE_VERSION = 1;
	 public static final String TABLE_CREATE =
			 "create table if not exists InventoryLineItemBook (_id integer primary key autoincrement , cashier_id integer , date long not null);";
	 
	 public static final String COL_DATE = "date";
	 public static final String COL_CASHIER_ID = "cashier_id";
	 
	 private int id;
	 private Date date;
	 private Cashier cashier;
	 private List<Item> items = new ArrayList<Item>();
	 public InventoryLineItem(int id,List<Item> items,Date date, Cashier cashier) {
		this.id = id;
		this.date = date;
		this.items = items;
		this.cashier = cashier;
	}
	 
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getID() {
		return id;
	}
	
	public List<Item> getItems()
	{
		return this.items;
	}
	
	public void setItems(List<Item> items) {
		this.items = items;
		
	}

	public Cashier getCashier() {
		return cashier;
	}

	public void setCashier(Cashier cashier) {
		this.cashier = cashier;
	}
}
