package com.database;

import java.util.List;

import com.core.InventoryLineItem;

/**
* Standard InventoryLineItem DataAccessObject interface for any database.
* @author Krittayot Techasombooranakit 5510545976
*/
public interface InventoryLineItemBookDao {
	
	/**
	 * Insert inventoryLineItem to database.
	 * @param inventoryLineItem want to insert(without id) which can contains contains any items(without id).
	 * @return inventoryLineItem with id contains items with id.
	 */
	public InventoryLineItem insert(InventoryLineItem ili);
	
	/**
	 * Find inventoryLineItem to database.
	 * @param id of {@link InventoryLineItem}
	 * @return inventoryLineItem with id contains items with id.
	 */
	public InventoryLineItem findByID(int id);
	
	/**
	 * @param id of {@link InventoryLineItem}
	 * @return id.
	 */
	public int delete(int inventoryLineItemId);
	
	/**
	 * @return List of all {@link InventoryLineItem} with items.
	 */
	public List<InventoryLineItem> findAll();
}