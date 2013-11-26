package com.core;

import java.io.Serializable;

/**
 * Description of item. 
 * @version 2013.11.26
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
   
    /** The id of ItemDescription. */
	private int id;
	 /** The name of ItemDescription. */
	private String name;
	 /** The price of ItemDescription. */
	private float price;
	 /** The bar code of ItemDescription. */
	private int barcode;
	 /** The description of ItemDescription. */
	private String itemDescription;
	
	/**
	 * Creates a ItemDescription and initializes id, name, description, price and barcode.
	 * @param id the initial id of ItemDescription
	 * @param name the initial name of ItemDescription
	 * @param itemDescription the initial description of ItemDescription
	 * @param price the initial price of ItemDescription
	 * @param barcode the initial barcode of ItemDescription
	 */
	public ItemDescription(int id,String name, String itemDescription, float price, int barcode){
		this.id = id;
		this.itemDescription = itemDescription;
		this.name = name;
		this.price = price;
		this.barcode = barcode;
	}

	/**
	 * Get the id from the item description.
	 * @return id the itemdescription's id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get the name from the item description.
	 * @return name the itemdescription's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name to the item description.
	 * @param name the name to be set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the price from the item description.
	 * @return price the itemdescription's price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Sets the price to the description.
	 * @param price the price to be set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * Get the barcode from the item description.
	 * @return barcode the itemdescription's barcode
	 */
	public int getBarcode() {
		return barcode;
	}

	/**
	 * Set the barcode to the item description.
	 * @param barcode the barcode to be set
	 */
	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}

	/**
	 * Get the item description.
	 * @return itemDescription the itemdescription
	 */
	public String getItemDescription() {
		return itemDescription;
	}

	/**
	 * Sets the new item description.
	 * @param itemDescription the item description to be set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
}
