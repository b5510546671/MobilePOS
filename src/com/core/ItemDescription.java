package com.core;

import java.io.Serializable;

/**
 * Description of item. 
 */
public class ItemDescription implements Serializable {
    public static final String DATABASE_TABLE = "ItemDescriptionBook";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists ItemDescriptionBook (_id integer primary key autoincrement , name text not null , barcode integer not null , description text not null, price real not null);";
    
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_PRICE= "price";
    public static final String COL_NAME = "name";
    public static final String COL_BARCODE = "barcode";
   
    /** The id of ItemDescription */
	private int id;
	 /** The name of ItemDescription */
	private String name;
	 /** The price of ItemDescription */
	private float price;
	 /** The bar code of ItemDescription */
	private int barcode;
	 /** The description of ItemDescription */
	private String itemDescription;
	
	/**
	 * Creates a ItemDescription with initial id, name, description, price and bar code.
	 * @param id the initial id of ItemDescription
	 * @param name the initial name of ItemDescription
	 * @param itemDescription the initial description of ItemDescription
	 * @param price the initial id of ItemDescription
	 * @param barcode the initial id of ItemDescription
	 */
	public ItemDescription(int id,String name, String itemDescription, float price, int barcode){
		this.id = id;
		this.itemDescription = itemDescription;
		this.name = name;
		this.price = price;
		this.barcode = barcode;
	}

	/**
	 * Returns the id of the description.
	 * @return id the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the name of the description.
	 * @return name the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the description.
	 * @param name the name for setting
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the price of the description.
	 * @return price the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Sets the price of the description.
	 * @param price the price for setting
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * Returns the bar code of the description.
	 * @return barcode the bar code
	 */
	public int getBarcode() {
		return barcode;
	}

	/**
	 * Sets the bar code of the description.
	 * @param barcode the bar code for setting
	 */
	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}

	/**
	 * Returns the description.
	 * @return itemDescription the description
	 */
	public String getItemDescription() {
		return itemDescription;
	}

	/**
	 * Sets the new description.
	 * @param itemDescription the description for setting
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
}
