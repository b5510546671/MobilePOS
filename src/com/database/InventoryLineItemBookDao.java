package com.database;

import java.util.List;

import com.core.InventoryLineItem;

public interface InventoryLineItemBookDao {
	public InventoryLineItem insert(InventoryLineItem ili);
	public InventoryLineItem findByID(int id);
	public int delete(int inventoryLineItemId);
	public List<InventoryLineItem> findAll();
}