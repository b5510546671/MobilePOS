package com.database;

import java.util.List;

import com.core.ItemDescription;

public interface ItemDescriptionBookDao {
	public ItemDescription insert(ItemDescription itd);
	public int update(int id, ItemDescription itd);
	public List<ItemDescription> findAll();
	public ItemDescription findByBarcode(String barcode);
	public int deleteByID(int id);
	public List<ItemDescription> findByContains(String name);
	public ItemDescription findBy(int id);
	public ItemDescription findBy(String name);
}