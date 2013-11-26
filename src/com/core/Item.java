package com.core;

import java.io.Serializable;

/**
 * The product item.
 * @version 2013.11.26
 */
public class Item implements Serializable {
    public static final String DATABASE_TABLE = "Inventory";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists Inventory (_id integer primary key autoincrement , sale_id integer, description_id integer not null, inventorylineitem_id integer not null);";
   
    public static final String COL_INVENTORYLINEITEM_ID = "inventorylineitem_id";
    public static final String COL_DESCRIPTION_ID = "description_id";
    public static final String COL_SALE_ID = "sale_id";
    
    public static final int SALE_STOCK_ID = -1;
	
    /** The id of Item. */
	private int id;
	 /** The description of Item. */
	private ItemDescription itemDesciption;
	
	/**
	 * Creates an Item and initializes id and description.
	 * @param id the initial id of Item
	 * @param itemDescription the initial description of Item
	 */
	public Item(int id,ItemDescription itemDesription) {
		this.itemDesciption = itemDesription;
		this.id= id;
	}
	
	/**
	 * Get the id of the item.
	 * @return id the item's id
	 */
	public int getID() {
		return this.id;
	}
	
	/**
	 * Get the description of the item.
	 * @return itemDescription the item's description
	 */
	public ItemDescription getItemDescription()
	{
		return this.itemDesciption;
	}
	
	/**
	 * Sets the ItemDescription of the item.
	 * @param itemDescription the description to be set
	 */
	public void setItemDescription(ItemDescription itemDescription){
		this.itemDesciption = itemDescription;
	}
	
	/**
	 * Get the price of the item.
	 * @return price the item's price
	 */
	public float getPrice(){
		return itemDesciption.getPrice();
	
	}
	
	/**
	 * Returns string.
	 * @return name a string representation of item
	 */
	public String toString()
	{
		return itemDesciption.getName();
	}
}