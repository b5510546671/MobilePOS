package com.core;

import java.io.Serializable;

/**
 * @author Apiwat //TODO
 * Item of pos system.
 */
public class Item implements Serializable {
    public static final String DATABASE_TABLE = "Inventory";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists Inventory (_id integer primary key autoincrement , imei_id text not null, sale_id integer, description_id integer not null, inventorylineitem_id integer not null , cost real not null);";
   
    public static final String COL_INVENTORYLINEITEM_ID = "inventorylineitem_id";
    public static final String COL_DESCRIPTION_ID = "description_id";
    public static final String COL_SALE_ID = "sale_id";
    public static final String COL_IMEI = "imei_id";
    public static final String COL_COST = "cost";
    
    public static final int SALE_STOCK_ID = -1;
	
	
	private int id;
	private ItemDescription itemDesciption;
	private float cost;
	private String imei;
	
	public Item(int id,ItemDescription itemDesription , String imei) {
		this.itemDesciption = itemDesription;
		this.id= id;
		this.cost = itemDesciption.getCost();
		this.setImei(imei);
	}
	
	public Item(int id,ItemDescription itemDesription , float cost , String imei) {
		this.itemDesciption = itemDesription;
		this.id= id;
		this.cost = cost;
		this.setImei(imei);
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

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
}