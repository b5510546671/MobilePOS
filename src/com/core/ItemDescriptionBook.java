package com.core;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.database.ItemDescriptionBookDB;

public class ItemDescriptionBook {
	private ItemDescriptionBookDB db;
	private List<ItemDescription> itemDescription;

	public ItemDescriptionBook(Context context) {
		db = new ItemDescriptionBookDB(context);
		itemDescription = new ArrayList<ItemDescription>();
	}

	public ItemDescription get(int id) {
		return db.findBy(id);
	}

	public void add(ItemDescription item) {
		db.insert(item);
		itemDescription.add(item);
	}

	public int getAmount() {
		return db.findAll().length;
	}

	public boolean remove(int id) {
		db.delete(id);
		for (ItemDescription i : itemDescription) {
			if (i.getId() == id)
				return itemDescription.remove(i);
		}
		return false;
	}

}
