package com.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InventoryLineItem implements Serializable{
	 public static final String DATABASE_TABLE = "InventoryLineItemBook";
	 public static final int DATABASE_VERSION = 1;
	 public static final String TABLE_CREATE =
			 "create table if not exists InventoryLineItemBook (_id integer primary key autoincrement , date long not null);";
	 
	 public static final String COL_DATE = "date";
	 
	 private int id;
	 private Date date;
	 private List<Item> items = new ArrayList<Item>();
	 public InventoryLineItem(int id,List<Item> items,Date date) {
		this.id = id;
		this.date = date;
		this.items = items;
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
}
