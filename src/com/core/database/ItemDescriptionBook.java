 package com.core.database;

import java.util.ArrayList;
import java.util.List;

import android.R.string;
import android.content.Context;

import com.core.ItemDescription;
import com.database.ItemDescriptionBookDB;
import com.database.ItemDescriptionBookDao;

/**
 * @author //TODO
 * ItemDescriptionBook contact directly to {@link ItemDescriptionBookDao}.
 */
public class ItemDescriptionBook {
	private ItemDescriptionBookDB db;

	/**
	 * @param con as context of application.
	 * @param id of {@link ItemDescription}
	 * @return {@link ItemDescription}.
	 */
	public ItemDescription getByID(Context con,int id) {
		db = new ItemDescriptionBookDB(con);
		ItemDescription x = db.findBy(id);
		db.close();
		return x;
	}

	/**
	 * @param con as context of application.
	 * @param name
	 * @param description to describe the product.
	 * @param price
	 * @param barcode
	 * @param cost
	 * @return {@link ItemDescription} with id.
	 */
	public ItemDescription add(Context con, String name , String description , float price , String barcode ,float cost) {
		ItemDescription itemDescription = new ItemDescription(0, name, description,cost ,  price, barcode);
		db = new ItemDescriptionBookDB(con);
		itemDescription = db.insert(itemDescription);
		db.close();
		return itemDescription;
	}

	/**
	 * @param con as context of application.
	 * @return List of all {@link ItemDescription} in db.
	 */
	public ArrayList<ItemDescription> getAllItemDescriptions(Context con) {
		db = new ItemDescriptionBookDB(con);
		ArrayList<ItemDescription> x = db.findAll();
		db.close();
		return x;
	}

	/**
	 * @param con as context of application.
	 * @param itemDescription want to remove.
	 * @return true if success.
	 */
	public boolean remove(Context con,ItemDescription itemDescription) {
		if(itemDescription.getItemDescription() == null || itemDescription.getName() == null) return false;
		db = new ItemDescriptionBookDB(con);
		db.deleteByID(itemDescription.getId());
		db.close();
		return true;
	}

	/**
	 * @param con as context of application.
	 * @param itemDescription want to check.
	 * @return ture if contains {@link ItemDescription} in db.
	 */
	public boolean isContains(Context con , ItemDescription itemDescription){
		ItemDescription i = getByID(con, itemDescription.getId());
		return i != null;
	}
	
	/**
	 * @param con as context of application.
	 * @return quantity of all {@link ItemDescription}.
	 */
	public int getQuantity(Context con){
		db = new ItemDescriptionBookDB(con);
		int i = db.findAll().size();
		db.close();
		return i;
	}
	
	/**
	 * @param con as context of application.
	 * @param barcode
	 * @return {@link ItemDescription}.
	 */
	public ItemDescription getItemDescriptionByBarcode(Context con , String barcode){
		db = new ItemDescriptionBookDB(con);
		ItemDescription i = db.findByBarcode(barcode);
		db.close();
		return i;
	}
	
	/**
	 * editItemDescriptionByID is the method to edit item description in the database.
	 * @param id is the key id to edit. 
	 * @param idesc new itemdescription.
	 * @param context context of the application.
	 */
	public void editItemDescriptionByID(int id,ItemDescription idesc,Context context){
		db = new ItemDescriptionBookDB(context);
		db.update(id, idesc);
		db.close();
		
	}
}
