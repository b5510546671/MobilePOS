package com.core.database;

import java.util.Date;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.core.InventoryLineItem;
import com.core.Item;
import com.core.ItemDescription;
import com.database.InventoryDB;
import com.database.InventoryLineItemBookDB;
/**
 * Access to the inventory that contain in the database
 */
public class Inventory {
	/**
	 * Object of the inventory database 
	 */
	private InventoryDB db;
	
	/**
	 * get the item in the database which will base on the id that user put in
	 * @param context is the context that receive from the user
	 * @param id is the id of the item that need to find
	 * @return i is the item that have correct id
	 */
	public Item getByID(Context context , int id){
		db = new InventoryDB(context);
		Item i = db.findByID(id);
		db.close();
		return i;
	}
	
	/**
	 * get the list of items that are in the stock which relate to the database
	 * @param con is the context the receive from the user
	 * @return the list of item that in the database
	 */
	public List<Item> getAllStock(Context con){
		db = new InventoryDB(con);
		List<Item> i = db.findByDescriptionID(Item.SALE_STOCK_ID);
		db.close();
		return i;
	}
	
	/**
	 * get the list of items which base on the InventoryLinItem
	 * @param con is the context which received
	 * @param inventoryLineItem is the inventoryLine item
	 * @return i is the list of items 
	 */
	public List<Item> getItemsByInventoryLineItem(Context con,InventoryLineItem inventoryLineItem)
	{
		db = new InventoryDB(con);
		List<Item> i = db.findByInventoryLineItemID(inventoryLineItem.getID());
		db.close();
		return i;
	}
	
	/**
	 * add new InventoryLineItem 
	 * @param con is the context that received
	 * @param inventoryLineItem is the inventoryLineItem that will be add
	 * @return i is the new InventoryLineItem
	 */
	public InventoryLineItem addInventoryLineItem(Context con , InventoryLineItem inventoryLineItem){
		db = new InventoryDB(con);
		InventoryLineItem i = db.insert(inventoryLineItem);
		db.close();
		return i;
	}
	
	/**
	 * get the item that base on the description
	 * @param con is the context that received
	 * @param itemDescription is the description of the item that need to find
	 * @return the list of item that contain the same description
	 */
	public List<Item> getItemsByItemDescription(Context con , ItemDescription itemDescription){
		db = new InventoryDB(con);
		List<Item> i = db.findByDescriptionID(itemDescription.getId());
		db.close();
		return i;
	}
	
	/**
	 * get the item in inventory by searching the id of that item
	 * @param con is the context that received
	 * @param id is the id that received from the user
	 * @return inventoryLineItem is the line item that search from the inventory
	 */
	public InventoryLineItem getInventoryLineItemByID(Context con , int id){
		InventoryLineItemBookDB inventoryLineItemBookDB = new InventoryLineItemBookDB(con);
		InventoryLineItem inventoryLineItem = inventoryLineItemBookDB.findByID(id);
		inventoryLineItemBookDB.close();
		return inventoryLineItem;
	}
	
	/**
	 * remove the exist InventoryLineItem 
	 * @param con is the context that received
	 * @param inventoryLineItem is the InventoryLineItem that will be remove
	 * @return true for show the complete process of removing
	 */
	public boolean remove(Context con , InventoryLineItem inventoryLineItem){
		InventoryLineItemBookDB inventoryLineItemBookDB = new InventoryLineItemBookDB(con);
		inventoryLineItemBookDB.delete(inventoryLineItem.getID());
		inventoryLineItemBookDB.close();
		return true;
	}
	
	/**
	 * remove the item in the database which the item will be delete will be on the item id
	 * @param con is the context that received
	 * @param item is the item that will remove from the database
	 * @return true
	 */
	public boolean remove(Context con , Item item){
		db = new InventoryDB(con);
		db.deleteItemByID(item.getID());
		db.close();
		return true;
	}
	
	/**
	 * find that the item is in the database or not
	 * @param con is the received context
	 * @param item is the item that will be searching for in the database
	 * @return true if the item is already contain in the database
	 */
	public boolean isContains(Context con , Item item){
		Item i = getByID(con, item.getID());
		return i != null;
	}
	
	/**
	 * find the number of  item that in the database
	 * @param con is the context
	 * @return i is the number of items that in the database
	 */
	public int getQuantity(Context con){
		InventoryDB inventoryDB = new InventoryDB(con);
		int i = inventoryDB.findAll().size();
		inventoryDB.close();
		return i;
	}
	
	/**
	 * find the list of item in a specific range 
	 * @param con is the context that received
	 * @param from is the start item that will be starting from
	 * @param to is the last item that will be stop searching from
	 * @return inventroyLineItems is the list of items that found on the rage given
	 */
	public List<InventoryLineItem> getInventoryLineItemBetween(Context con , Date from , Date to){

		InventoryLineItemBookDB inventoryLineItemBookDB = new InventoryLineItemBookDB(con);
		List<InventoryLineItem> inventoryLineItems = inventoryLineItemBookDB.findByDate(from, to);
		inventoryLineItemBookDB.close();
		return inventoryLineItems;
	}
}
