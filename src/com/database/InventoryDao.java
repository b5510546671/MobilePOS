package com.database;
import java.util.List;

import com.core.InventoryLineItem;
import com.core.Item;

public interface InventoryDao {
	
	public InventoryLineItem insert(InventoryLineItem inventoryLineItem);
	public Item insert(int inventoryLineItem_id , Item item); //in side
	public int deleteItemByID(int id);
	public int deleteItemsAndInventoryLineItemByInventoryLineItemID(int inventoryLineItem_id);
	public int moveToStockBySaleID(int sale_id);
	public int updateSaleID(int sale_id , Item item); // if sale_id = 0 then item is stock
	public List<Item> findAll();
	public List<Item> findBySaleID(int sale_id);
	public List<Item> findByInventoryLineItemID(int inventoryLineItem_id);
	public List<Item> findByDescriptionID(int itemDescription_id);
	public Item findByID(int id);
	public Item findByImei(String id);
	public int findQuantity(int descId);
	public int getSaleId(Item item);
}