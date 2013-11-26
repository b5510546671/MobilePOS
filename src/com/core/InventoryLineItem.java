package com.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * List of items in the inventory.
 */
public class InventoryLineItem implements Serializable{
	 public static final String DATABASE_TABLE = "InventoryLineItemBook";
	 public static final int DATABASE_VERSION = 1;
	 public static final String TABLE_CREATE =
			 "create table if not exists InventoryLineItemBook (_id integer primary key autoincrement , date long not null);";
	 
	 public static final String COL_DATE = "date";
	 
	 /** The id of LineItem */
	 private int id;
	 /** The date of LineItem */
	 private Date date;
	 /** The list of items of LineItem */
	 private List<Item> items = new ArrayList<Item>();
	 
	 /**
	  * Creates a InventoryLineItem with initial id, list of items and date.
	  * @param id the initial id of LineItem
	  * @param items the initial list of items of LineItem
	  * @param date the initial date of LineItem
	  */
	 public InventoryLineItem(int id,List<Item> items,Date date) {
		this.id = id;
		this.date = date;
		this.items = items;
	}
	
	 /**
	  * Returns the date of LineItem.
	  * @return date the date
	  */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Sets the date of LineItem.
	 * @param date the date for setting
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * Returns the id of LineItem.
	 * @return id the id*/
	public int getID() {
		return id;
	}
	
	/**
	 * Returns the list of item in LineItem
	 * @return items the list of items
	 * */
	public List<Item> getItems()
	{
		return this.items;
	}
	
	/**
	 * Sets the list of items in LineItem.
	 * @param items the list of items for setting
	 */
	public void setItems(List<Item> items) {
		this.items = items;
		
	}
}