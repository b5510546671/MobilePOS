package com.database;
import java.util.List;

import com.core.InventoryLineItem;
import com.core.Item;

/**
* Standard Inventory DataAccessObject interface for any database.
* @author Krittayot Techasombooranakit 5510545976
*/
public interface InventoryDao {
	
	/**
	 * Insert inventoryLineItem to database.
	 * @param inventoryLineItem want to insert(without id) which can contains contains any items(without id).
	 * @return inventoryLineItem with id contains items with id.
	 */
	public InventoryLineItem insert(InventoryLineItem inventoryLineItem);
	
	/**
	 * inner use for access item object to refer inventoryLineItem. (do not call).
	 */
	public Item insert(int inventoryLineItem_id , Item item); //in side
	
	
	/**
	 * Delete an items from database.
	 * @param id of item.
	 * @return id of item.
	 */
	public int deleteItemByID(int id);
	
	
	/**
	 * Delete an inventoryLineItem and Items inside.
	 * @param id of InventoryLineItem.
	 * @return id of InventoryLineItem.
	 */
	public int deleteItemsAndInventoryLineItemByInventoryLineItemID(int inventoryLineItem_id);
	
	/**
	 * Move items that have this saleId to Stocked item(use when customer refund a sale).
	 * @param id of sale.
	 * @return id of sale.
	 */
	public int moveToStockBySaleID(int sale_id);
	
	/**
	 * Update Sale id to an Item.
	 * @param id of sale.
	 * @param item want to update.
	 * @return id of item.
	 */
	public int updateSaleID(int sale_id , Item item);
	
	/**
	 * @return List of all item in database (includes all sold and stock).
	 */
	public List<Item> findAll();
	
	/**
	 * Find items by sale id.
	 * @param id of sale.
	 * @return List of item.
	 */
	public List<Item> findBySaleID(int sale_id);
	
	/**
	 * Find items by inventoryLineItem id.
	 * @param id of inventoryLineItem.
	 * @return List of item.
	 */
	public List<Item> findByInventoryLineItemID(int inventoryLineItem_id);
	
	
	/**
	 * Find items by itemDescription id.
	 * @param id of itemDescription.
	 * @return List of item.
	 */
	public List<Item> findByDescriptionID(int itemDescription_id);
	
	/**
	 * Find items by id.
	 * @param id of item.
	 * @return item.
	 */
	public Item findByID(int id);
	
	/**
	 * Find items by imei of items.
	 * @param imei code of item.
	 * @return item.
	 */
	public Item findByImei(String id);
	
	/**
	 * Get quantity of itemDescription.
	 * @param itemDescription id.
	 * @return quantity.
	 */
	public int findQuantity(int descId);
	
	/**
	 * @param item
	 * @return sale id of item (-1 if stock).
	 */
	public int getSaleId(Item item);
}