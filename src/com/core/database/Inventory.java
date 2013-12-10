package com.core.database;

import java.util.List;

import android.content.Context;
import android.util.Log;

import com.core.InventoryLineItem;
import com.core.Item;
import com.core.ItemDescription;
import com.database.InventoryDB;
import com.database.InventoryLineItemBookDB;

/**
 * @author //TODO 
 * Inventory contact directly to {@link InventoryDB} and {@link InventoryLineItemBookDB}
 */
public class Inventory {
	private InventoryDB db;
	
	/**
	 * @param con as context of application.text of application 
	 * @param id of item.
	 * @return item.
	 */
	public Item getByID(Context context , int id){
		db = new InventoryDB(context);
		Item i = db.findByID(id);
		db.close();
		return i;
	}
	
	/**
	 * @param con as context of application.
	 * @return List of stock items.
	 */
	public List<Item> getAllStock(Context con){
		db = new InventoryDB(con);
		List<Item> i = db.findBySaleID(Item.SALE_STOCK_ID);
		db.close();
		return i;
	}
	
	/**
	 * @param con as context of application.
	 * @param inventoryLineItem want to find.
	 * @return List of item which have been inserted by {@link InventoryLineItem}.
	 */
	public List<Item> getItemsByInventoryLineItem(Context con,InventoryLineItem inventoryLineItem)
	{
		db = new InventoryDB(con);
		List<Item> i = db.findByInventoryLineItemID(inventoryLineItem.getID());
		db.close();
		return i;
	}
	
	/**
	 * @param con as context of application.
	 * @param inventoryLineItem want to add.
	 * @return inventoryLineItem with id, contains items with id.
	 */
	public InventoryLineItem addInventoryLineItem(Context con , InventoryLineItem inventoryLineItem){
		db = new InventoryDB(con);
		InventoryLineItem i = db.insert(inventoryLineItem);
		db.close();
		return i;
	}
	
	
	
	/**
	 * @param con as context of application.
	 * @param itemDescription want to find.
	 * @return List of item that is {@link ItemDescription}.
	 */
	public List<Item> getItemsByItemDescription(Context con , ItemDescription itemDescription){
		db = new InventoryDB(con);
		List<Item> i = db.findByDescriptionID(itemDescription.getId());
		db.close();
		return i;
	}
	
	/**
	 * @param con as context of application.
	 * @param id of {@link InventoryLineItem}
	 * @return {@link InventoryLineItem}
	 */
	public InventoryLineItem getInventoryLineItemByID(Context con , int id){
		InventoryLineItemBookDB inventoryLineItemBookDB = new InventoryLineItemBookDB(con);
		InventoryLineItem inventoryLineItem = inventoryLineItemBookDB.findByID(id);
		inventoryLineItemBookDB.close();
		return inventoryLineItem;
	}
	
	/**
	 * @param con as context of application.
	 * @param inventoryLineItem want to remove.
	 * @return true if success.
	 */
	public boolean remove(Context con , InventoryLineItem inventoryLineItem){
		InventoryLineItemBookDB inventoryLineItemBookDB = new InventoryLineItemBookDB(con);
		inventoryLineItemBookDB.delete(inventoryLineItem.getID());
		inventoryLineItemBookDB.close();
		return true;
	}
	
	/**
	 * @param con as context of application.
	 * @param item want to remove.
	 * @return true if success.
	 */
	public boolean remove(Context con , Item item){
		db = new InventoryDB(con);
		db.deleteItemByID(item.getID());
		db.close();
		return true;
	}
	
	/**
	 * @param con as context of application.
	 * @param item want to check.
	 * @return true if contains item.
	 */
	public boolean isContains(Context con , Item item){
		Item i = getByID(con, item.getID());
		return i != null;
	}
	
	/**
	 * @param con as context of application.
	 * @return quantity of all items (stock and sold). 
	 */
	public int getQuantity(Context con){
		return getAllItem(con).size();
	}
	
	/**
	 * @param con as context of application.
	 * @return List of all items (stock and sold).
	 */
	public List<Item> getAllItem(Context con){
		InventoryDB inventoryDB = new InventoryDB(con);
		List<Item> i = inventoryDB.findAll();
		inventoryDB.close();
		return i;
	}
	
	/**
	 * @param con as context of application.
	 * @return List of all {@link InventoryLineItem} contains all of items (stock and sold).
	 */
	public List<InventoryLineItem> getAllInventoryLineItem(Context con){
		InventoryLineItemBookDB inventoryLineItemBookDB = new InventoryLineItemBookDB(con);
		List<InventoryLineItem> i = inventoryLineItemBookDB.findAll();
		inventoryLineItemBookDB.close();
		return i;
	}
	
	/**
	 * @param con as context of application.
	 * @param item that want to check.
	 * @return true if sold.
	 */
	public boolean isSold(Context con , Item item){
		InventoryDB inventoryDB = new InventoryDB(con);
		int i = inventoryDB.getSaleId(item);
		inventoryDB.close();
		return i != Item.SALE_STOCK_ID;
	}
	
}
