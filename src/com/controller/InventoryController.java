package com.controller;

import java.util.List;

import android.content.Context;

import com.core.InventoryLineItem;
import com.core.Item;
import com.core.ItemDescription;
import com.core.Store;

public class InventoryController {
	private static InventoryController inventoryController;
	
	private Store store;
	private InventoryLineItem inventoryLineItem ;
	
	public InventoryController() {
		store = Store.getInstance();
		
	}

	public static InventoryController getInstance()
	{
		if(inventoryController == null) inventoryController = new InventoryController();
		 return inventoryController;
	}
	
	public ItemDescription getItemDescriptionByBarcode(Context con, String barcode){
		return store.getItemDescriptionBook().getItemDescriptionByBarcode(con, barcode);
	}
	
	public List<InventoryLineItem> getAllInventoryLineItem(Context con){
		return store.getInventory().getAllInventoryLineItem(con);
	}
	
	
	
	
	public Item getItemByItemDescription(Context con,ItemDescription itemDes){
		return store.getInventory().getItemsByItemDescription(con, itemDes).get(0);
	}
	
	public ItemDescription createNewItemDescription(Context con,String name,String description,float price,String barcode,float cost){
		return store.getItemDescriptionBook().add(con, name, description, price, barcode, cost);
	}
	
	public void removeItemDescription(Context con,String barcode){
		
		ItemDescription itemDescription = store.getItemDescriptionBook().getItemDescriptionByBarcode(con, barcode);
		store.getItemDescriptionBook().remove(con, itemDescription);
	}
	
	public InventoryLineItem addinventoryLineItemToInventory(Context con,InventoryLineItem inventoryLineItem)
	{
		return store.getInventory().addInventoryLineItem(con, inventoryLineItem);
	}
	
	

}
