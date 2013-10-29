package com.database;
import com.core.Item;

public interface InventoryDao {
	public long insert(Item item);
	public int delete(int itemCode);
	public void deleteAll(int[] itemCode);
	public int update( Item item);
	public Item[] findAll();
	public Item[] findContainsBy(String name);
	public Item findBy(String name);
	public Item[] findByBarcode(int barcode);
	public Item[] findStatus(int status);
	public Item[] findStatus(int descId , int status);
	public void close();
	public int findQuantity(int descId);
}