package com.core.database;

import java.util.ArrayList;
import java.util.List;

import android.R.string;
import android.content.Context;

import com.core.ItemDescription;
import com.database.ItemDescriptionBookDB;

public class ItemDescriptionBook {
	private ItemDescriptionBookDB db;

	public ItemDescription getByID(Context con,int id) {
		db = new ItemDescriptionBookDB(con);
		ItemDescription x = db.findBy(id);
		db.close();
		return x;
	}

	public ItemDescription add(Context con, String name , String description , float price , int barcode ,float cost) {
		ItemDescription itemDescription = new ItemDescription(0, name, description,cost ,  price, barcode);
		db = new ItemDescriptionBookDB(con);
		itemDescription = db.insert(itemDescription);
		db.close();
		return itemDescription;
	}

	public ArrayList<ItemDescription> getAllItemDescriptions(Context con) {
		db = new ItemDescriptionBookDB(con);
		ArrayList<ItemDescription> x = db.findAll();
		db.close();
		return x;
	}

	public boolean remove(Context con,ItemDescription itemDescription) {
		if(itemDescription.getItemDescription() == null || itemDescription.getName() == null) return false;
		db = new ItemDescriptionBookDB(con);
		db.deleteByID(itemDescription.getId());
		db.close();
		return true;
	}

	public boolean isContains(Context con , ItemDescription itemDescription){
		ItemDescription i = getByID(con, itemDescription.getId());
		return i != null;
	}
	
	public int getQuantity(Context con){
		db = new ItemDescriptionBookDB(con);
		int i = db.findAll().size();
		db.close();
		return i;
	}
	
	public ItemDescription getItemDescriptionByBarcode(Context con , int barcode){
		db = new ItemDescriptionBookDB(con);
		ItemDescription i = db.findByBarcode(barcode);
		db.close();
		return i;
	}
}
