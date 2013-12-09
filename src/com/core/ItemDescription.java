package com.core;

import java.io.Serializable;

public class ItemDescription implements Serializable {
    public static final String DATABASE_TABLE = "ItemDescriptionBook";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists ItemDescriptionBook (_id integer primary key autoincrement , name text not null , barcode text not null , description text not null, cost real not null , price real not null);";
    
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_PRICE = "price";
    public static final String COL_COST = "cost";
    public static final String COL_NAME = "name";
    public static final String COL_BARCODE = "barcode";
   
	private int id;
	private String name;
	private float price;
	private String barcode;
	private String itemDescription;
	private float cost;
	
	public ItemDescription(int id,String name, String itemDescription,float cost, float price, String barcode){
		this.id = id;
		this.itemDescription = itemDescription;
		this.name = name;
		this.price = price;
		this.barcode = barcode;
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
	public String toString(){
		return this.name;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}
	
}
