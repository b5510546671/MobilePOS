package com.controller;

import java.util.List;

import android.content.Context;

import com.core.Cashier;
import com.core.InventoryLineItem;
import com.core.Item;
import com.core.ItemDescription;
import com.core.Store;
/**
 * InventoryController is the controller of the Inventory.
 * @author Sikarin	Larnamwong	5510546174
 *
 */
public class InventoryController {
	/**
	 * inventoryController is the instance of InventoryController.
	 */
	private static InventoryController inventoryController;
	/**
	 * store is the instance of Store.
	 */
	private Store store;
	/**
	 * InventoryController is the constructor method.
	 */
	public InventoryController() {
		store = Store.getInstance();

	}

	/**
	 * getInstance is the method to get the instance of InventoryController.
	 * @return instance of InventoryController.
	 */
	public static InventoryController getInstance() {
		if (inventoryController == null)
			inventoryController = new InventoryController();
		return inventoryController;
	}

	/**
	 * getItemDescriptionByBarcode is the mehtod to get ItemDescription bt input barcode.
	 * @param con is the Context of Activity.
	 * @param barcode is the barcode to get ItemDescription.
	 * @return found ItemDescription.
	 */
	public ItemDescription getItemDescriptionByBarcode(Context con,
			String barcode) {
		return store.getItemDescriptionBook().getItemDescriptionByBarcode(con,
				barcode);
	}

	/**
	 * getAllInventoryLineItem is the method to get All InventoryLineItem.
	 * @param con is the Context of Activity.
	 * @return List of all InventoryLineItem.
	 */
	public List<InventoryLineItem> getAllInventoryLineItem(Context con) {
		return store.getInventory().getAllInventoryLineItem(con);
	}

	
	
	/**
	 * removeInvntoryLineItemFromInventory is the method to remove from InventoryLineItem.
	 * @param con is the Context of Activity.
	 * @param in is the InventoryLineItem to remove.
	 */
	public void removeInvntoryLineItemFromInventory(Context con,InventoryLineItem in){
		 store.getInventory().remove(con, in);
	}

	/**
	 * getItemByItemDescription is the method to get Item by ItemDescription.
	 * @param con is the Context of Activity.
	 * @param itemDes is the ItemDescription use to get the Item.
	 * @return found Item.
	 */
	public Item getItemByItemDescription(Context con, ItemDescription itemDes) {
		return store.getInventory().getItemsByItemDescription(con, itemDes)
				.get(0);
	}

	/**
	 * createNewItemDescription is the method to create ItemDescription from the data.
	 * @param con is the Context of Activity.
	 * @param name is name of ItemDesction.
	 * @param description is description of ItemDesction.
	 * @param price is price of ItemDesction.
	 * @param barcode is barcode of ItemDesction.
	 * @param cost is cost of ItemDesction.
	 * @return create ItemDescription
	 */
	public ItemDescription createNewItemDescription(Context con, String name,
			String description, float price, String barcode, float cost) {
		return store.getItemDescriptionBook().add(con, name, description,
				price, barcode, cost);
	}

	/**
	 * removeItemDescriptionByBarcode is the method use to remove ItemDescription bt barcode
	 * @param con is the Context of Activity.
	 * @param barcode is the barcode use to to remove ItemDescription.
	 */
	public void removeItemDescriptionByBarcode(Context con, String barcode) {

		ItemDescription itemDescription = store.getItemDescriptionBook()
				.getItemDescriptionByBarcode(con, barcode);
		store.getItemDescriptionBook().remove(con, itemDescription);
	}

	/**
	 * addInventoryLineItemToInventory is the method to add InventoryLineItem to Inventory.
	 * @param con is the Context of Activity.
	 * @param inventoryLineItem is the InventoryLineItem to add.
	 * @return added InventoryLineItem.
	 */
	public InventoryLineItem addInventoryLineItemToInventory(Context con,
			InventoryLineItem inventoryLineItem) {
		return store.getInventory()
				.addInventoryLineItem(con, inventoryLineItem);
	}

	/**
	 * editCashier is the method to edit Cashier.
	 * @param con is the Context of Activity.
	 * @param cashier is Cashier to edit.
	 * @return editted Cashier
	 */
	public Cashier editCashier(Context con, Cashier cashier) {
		return store.getCashierBook().editCashier(con, cashier);
	}
	
	/**
	 * @param con is the Context of Activity.
	 * @param i is Item to remove from Inventory
	 * @return true if sold, false if not sold.
	 */
	public boolean isSold(Context con, Item i){
		return store.getInventory().isSold(con, i);
	}
	
	/**
	 * @param con is the Context of Activity.
	 * @param i is Item to remove from Inventoryi
	 */
	public void removeItemFromInventory(Context con,Item i){
		store.getInventory().remove(con, i);
	}
	/**
	 * editItemDescriptionByID is the method to edit item description in the database.
	 * @param id is the key id to edit. 
	 * @param idesc new itemdescription.
	 * @param context context of the application.
	 */
	public void editProductDescriptionByID(int id,ItemDescription itemDesc,Context con){
		store.getItemDescriptionBook().editItemDescriptionByID(id,itemDesc, con);
	}

}
