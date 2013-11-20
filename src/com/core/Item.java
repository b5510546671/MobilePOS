package com.core;

import java.io.Serializable;

public class Item implements Serializable {
    public static final String DATABASE_TABLE = "Inventory";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists Inventory (_id integer primary key autoincrement , sale_id integer, description_id integer not null, inventorylineitem_id integer not null);";
   
    public static final String COL_INVENTORYLINEITEM_ID = "inventorylineitem_id";
    public static final String COL_DESCRIPTION_ID = "description_id";
    public static final String COL_SALE_ID = "sale_id";
    
    public static final int SALE_STOCK_ID = -1;
	
	
	private int id;
	private ItemDescription itemDesciption;
	
	public Item(int id,ItemDescription itemDesription) {
		this.itemDesciption = itemDesription;
		this.id= id;
	}
	
	public int getID() {
		return this.id;
	}
	
	public ItemDescription getItemDescription()
	{
		return this.itemDesciption;
	}
	
	public void setItemDescription(ItemDescription itemDescription){
		this.itemDesciption = itemDescription;
	}
	
	public float getPrice(){
		return itemDesciption.getPrice();
	
	}
	
	public String toString()
	{
		return itemDesciption.getName();
	}
}