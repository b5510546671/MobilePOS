package com.core.database;

import java.util.ArrayList;
import java.util.List;

import android.R.string;
import android.content.Context;

import com.core.ItemDescription;
import com.database.ItemDescriptionBookDB;
/**
 *Access to the ItemDescription that in the database and return the useable information
 */
public class ItemDescriptionBook {
	/**
	 * the ItemDescription database
	 */
	private ItemDescriptionBookDB db;

	/**
	 * get the item's description which base on the input id
	 * @param con is the context that received
	 * @param id is the id that will be searching for
	 * @return x is the description of that item
	 */
	public ItemDescription getByID(Context con,int id) {
		db = new ItemDescriptionBookDB(con);
		ItemDescription x = db.findBy(id);
		db.close();
		return x;
	}

	/**
	 * add description the item which all the information will be saved in to the database
	 * @param con is the context
	 * @param name is the name of the item that will be add
	 * @param description is the description of that item
	 * @param price is the price of the item which will be store in from of float 
	 * @param barcode is the bar code of the item 
	 * @return itemDescription is the description of that item
	 */
	public ItemDescription add(Context con, String name , String description , float price , int barcode) {
		ItemDescription itemDescription = new ItemDescription(0, name, description, price, barcode);
		db = new ItemDescriptionBookDB(con);
		itemDescription = db.insert(itemDescription);
		db.close();
		return itemDescription;
	}

	/**
	 * get all items's description that contain in the database
	 * @param con is the context that received
	 * @return x is the list of all items's description that in database
	 */
	public ArrayList<ItemDescription> getAllItemDescriptions(Context con) {
		db = new ItemDescriptionBookDB(con);
		ArrayList<ItemDescription> x = db.findAll();
		db.close();
		return x;
	}

	/**
	 * remove the description of the item which base on the ItemDescription
	 * @param con is the context that received
	 * @param itemDescription is the item that contain the description
	 * @return true 
	 */
	public boolean remove(Context con,ItemDescription itemDescription) {
		if(itemDescription.getItemDescription() == null || itemDescription.getName() == null) return false;
		db = new ItemDescriptionBookDB(con);
		db.deleteByID(itemDescription.getId());
		db.close();
		return true;
	}

	/**
	 * find that item description is still in the database or not 
	 * @param con is the context 
	 * @param itemDescription is the item description that show which item will be searching for
	 * @return true if that item description had been removed from the database
	 */
	public boolean isContains(Context con , ItemDescription itemDescription){
		ItemDescription i = getByID(con, itemDescription.getId());
		return i != null;
	}
	
	/**
	 * find the number of item in the database
	 * @param con is the context 
	 * @return the number of all items in the ItemDescriptionBook
	 */
	public int getQuantity(Context con){
		db = new ItemDescriptionBookDB(con);
		int i = db.findAll().size();
		db.close();
		return i;
	}
	
	/**
	 * get the itemDescription which base on the barcode number
	 * @param con is the context
	 * @param barcode is the id of the barcode
	 * @return i is the itemDescription that contain that barcode
	 */
	public ItemDescription getItemDescriptionByBarcode(Context con , int barcode){
		db = new ItemDescriptionBookDB(con);
		ItemDescription i = db.findByBarcode(barcode);
		db.close();
		return i;
	}
}
