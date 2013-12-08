package com.database;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.core.InventoryLineItem;
import com.core.Item;
import com.core.ItemDescription;
import com.core.database.Inventory;

public class InventoryDB extends GenericDao implements InventoryDao{
	
	private Context context;
	
	public InventoryDB(Context context){
		super(context, GenericDao.dName, Item.TABLE_CREATE, Item.DATABASE_TABLE, Item.DATABASE_VERSION);
		this.context = context;
	}
	
	//checked
	@Override
	public InventoryLineItem insert(InventoryLineItem inventoryLineItem) {
		InventoryLineItemBookDB inventoryLineItemDB = new InventoryLineItemBookDB(context);
		inventoryLineItem = inventoryLineItemDB.insert(inventoryLineItem);
		inventoryLineItemDB.close();
		List<Item> items = inventoryLineItem.getItems();
		for(int i = 0 ; i < items.size() ; i++){
			items.set(i, insert(inventoryLineItem.getID() , items.get(i)));
		}
		
		return new InventoryLineItem(inventoryLineItem.getID(), inventoryLineItem.getItems(), inventoryLineItem.getDate() , inventoryLineItem.getCashier());
	}

	//checked
	@Override
	public Item insert(int inventoryLineItem_id , Item item) {
		ContentValues cv = new ContentValues();
        cv.put(Item.COL_SALE_ID, Item.SALE_STOCK_ID);
        cv.put(Item.COL_COST, item.getCost());
        cv.put(Item.COL_IMEI, item.getImei());
        cv.put(Item.COL_INVENTORYLINEITEM_ID, inventoryLineItem_id);
        cv.put(Item.COL_DESCRIPTION_ID, item.getItemDescription().getId());
        
		return new Item((int)super.insert(Item.DATABASE_TABLE, cv) , item.getItemDescription() ,item.getCost() , item.getImei());
	}

	//checked
	@Override
	public int deleteItemByID(int id) {
		return super.delete(Item.DATABASE_TABLE,GenericDao.KEY_ID + " = " + id, null);
	}
	
	@Override
	public ArrayList<Item> findAll() {
		String[] columns = new String[]{GenericDao.KEY_ID , Item.COL_DESCRIPTION_ID};
		return getItemFromCursor(super.get(Item.DATABASE_TABLE, columns));
	}
	
	private ArrayList<Item> getItemFromCursor(Cursor cursor){
		ArrayList<Item> itds = new ArrayList<Item>();
		if(cursor != null){
			if(cursor.moveToFirst()){
				int count = cursor.getCount();
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
				int desId = cursor.getColumnIndex(Item.COL_DESCRIPTION_ID);
				//int invId = cursor.getColumnIndex(Item.COL_INVENTORYLINEITEM_ID);
				int _cost = cursor.getColumnIndex(Item.COL_COST);
				int _imei = cursor.getColumnIndex(Item.COL_IMEI);
				//int statusId = cursor.getColumnIndex(Item.COL_SALE_ID);
				for(int i = 0 ; i < count ; i++){
					ItemDescriptionBookDB _db = new ItemDescriptionBookDB(context);
					ItemDescription des = _db.findBy(cursor.getInt(desId));
					_db.close();
					itds.add(new Item(cursor.getInt(_id), des , cursor.getFloat(_cost) , cursor.getString(_imei)));
					cursor.moveToNext();
				}
			}
		} 
		return itds;
	}

	@Override
	public int findQuantity(int descId) {
		String[] columns = new String[]{GenericDao.KEY_ID ,Item.COL_INVENTORYLINEITEM_ID};
		Cursor cursor = super.get(Item.DATABASE_TABLE, columns , Item.COL_DESCRIPTION_ID + "=" + descId);
		if(cursor != null){
			cursor.moveToFirst();
			return cursor.getCount();
		}
		return 0;
	}
	
	@Override
	public int updateSaleID(int sale_id , Item item) {
		ContentValues cv = new ContentValues();
        cv.put(GenericDao.KEY_ID, item.getID());
        cv.put(Item.COL_SALE_ID, sale_id);
        return super.update(Item.DATABASE_TABLE, GenericDao.KEY_ID + " = " + item.getID(), cv);
		//return super.update(Item.DATABASE_TABLE, item.getID(), cv);
	}

	@Override
	public Item findByID(int id) {
		String[] columns = new String[]{GenericDao.KEY_ID ,Item.COL_DESCRIPTION_ID };
		Cursor cursor = super.get(Item.DATABASE_TABLE, columns ,id);
		Item item = null;
		if(cursor != null){
			int itdID = cursor.getColumnIndex(Item.COL_DESCRIPTION_ID);
			int _cost = cursor.getColumnIndex(Item.COL_COST);
			int _imei = cursor.getColumnIndex(Item.COL_IMEI);
			cursor.moveToFirst();
			ItemDescriptionBookDB itemDescriptionBookDB = new ItemDescriptionBookDB(getContext());
			ItemDescription itemDescription = itemDescriptionBookDB.findBy(cursor.getInt(itdID));
			itemDescriptionBookDB.close();
			item = new Item(id, itemDescription , cursor.getFloat(_cost) , cursor.getString(_imei));
		}
		return item;
	}

	@Override
	public int deleteItemsAndInventoryLineItemByInventoryLineItemID(int inventoryLineItem_id) {
		InventoryLineItemBookDB inventoryLineItemBookDB = new InventoryLineItemBookDB(getContext());
		inventoryLineItemBookDB.delete(inventoryLineItem_id);
		inventoryLineItemBookDB.close();
		return super.delete(Item.DATABASE_TABLE, Item.COL_INVENTORYLINEITEM_ID +"="+ inventoryLineItem_id , null);
	}

	@Override
	public ArrayList<Item> findByDescriptionID(int itemDescription_id) {
		String[] columns = new String[]{GenericDao.KEY_ID , Item.COL_DESCRIPTION_ID};
		Cursor cursor = super.get(Item.DATABASE_TABLE , columns , Item.COL_DESCRIPTION_ID +"="+ itemDescription_id);
		ArrayList<Item> items = new ArrayList<Item>();
		if(cursor != null){
			int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
			int _desId = cursor.getColumnIndex(Item.COL_DESCRIPTION_ID);
			int _cost = cursor.getColumnIndex(Item.COL_COST);
			int _imei = cursor.getColumnIndex(Item.COL_IMEI);
			if(cursor.moveToFirst()){
				ItemDescriptionBookDB itemDescriptionBookDB = new ItemDescriptionBookDB(getContext());
				ItemDescription itemDescription = itemDescriptionBookDB.findBy(cursor.getInt(_desId));
				itemDescriptionBookDB.close();
				for(int i = 0 ; i < cursor.getCount() ; i++){
					items.add( 
							new Item(cursor.getInt(_id) , new ItemDescription(itemDescription.getId() ,itemDescription.getName() , itemDescription.getItemDescription() , itemDescription.getCost() , itemDescription.getPrice() ,itemDescription.getBarcode()) , cursor.getFloat(_cost) , cursor.getString(_imei))
					);
					cursor.moveToNext();
				}
			}
		}
		return items;
	}

	
	@Override
	public List<Item> findByInventoryLineItemID(int inventoryLineItem_id) {
		String[] columns = new String[]{GenericDao.KEY_ID , Item.COL_DESCRIPTION_ID};
		Cursor cursor = super.get(Item.DATABASE_TABLE , columns , Item.COL_INVENTORYLINEITEM_ID +"="+ inventoryLineItem_id);
		HashMap<Integer , ItemDescription> itemDescriptionsMap = new HashMap<Integer, ItemDescription>();
		ArrayList<Item> items = new ArrayList<Item>();
		if(cursor != null){
			int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
			int _desId = cursor.getColumnIndex(Item.COL_DESCRIPTION_ID);
			int _cost = cursor.getColumnIndex(Item.COL_COST);
			int _imei = cursor.getColumnIndex(Item.COL_IMEI);
			if(cursor.moveToFirst()){
				ItemDescriptionBookDB itemDescriptionBookDB = new ItemDescriptionBookDB(getContext());
				ItemDescription itemDescription;
				for(int i = 0 ; i < cursor.getCount() ; i++){
					if(!itemDescriptionsMap.containsKey(cursor.getInt(_desId))){
						itemDescriptionsMap.put(cursor.getInt(_desId), itemDescriptionBookDB.findBy(cursor.getInt(_desId)));
					}
					itemDescription = itemDescriptionsMap.get(cursor.getInt(_desId));
					items.add( 
						new Item(cursor.getInt(_id) , new ItemDescription(itemDescription.getId() ,itemDescription.getName() , itemDescription.getItemDescription() , itemDescription.getCost() , itemDescription.getPrice() ,itemDescription.getBarcode()) , cursor.getFloat(_cost) , cursor.getString(_imei))
					);
					cursor.moveToNext();
				}
				
				itemDescriptionBookDB.close();
			}
		}
		return items;
	}

	//Checked
	@Override
	public List<Item> findBySaleID(int id) {
		String[] columns = new String[]{GenericDao.KEY_ID , Item.COL_DESCRIPTION_ID};
		Cursor cursor = super.get(Item.DATABASE_TABLE , columns , Item.COL_SALE_ID +"="+ id);
		HashMap<Integer , ItemDescription> itemDescriptionsMap = new HashMap<Integer, ItemDescription>();
		ArrayList<Item> items = new ArrayList<Item>();
		if(cursor != null){
			int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
			int _desId = cursor.getColumnIndex(Item.COL_DESCRIPTION_ID);
			int _cost = cursor.getColumnIndex(Item.COL_COST);
			int _imei = cursor.getColumnIndex(Item.COL_IMEI);
			if(cursor.moveToFirst()){
				ItemDescriptionBookDB itemDescriptionBookDB = new ItemDescriptionBookDB(getContext());
				ItemDescription itemDescription;
				for(int i = 0 ; i < cursor.getCount() ; i++){
					if(!itemDescriptionsMap.containsKey(cursor.getInt(_desId))){
						itemDescriptionsMap.put(cursor.getInt(_desId), itemDescriptionBookDB.findBy(cursor.getInt(_desId)));
					}
					itemDescription = itemDescriptionsMap.get(cursor.getInt(_desId));
					items.add( 
						new Item(cursor.getInt(_id) , new ItemDescription(itemDescription.getId() ,itemDescription.getName() , itemDescription.getItemDescription() , itemDescription.getCost() , itemDescription.getPrice() ,itemDescription.getBarcode()) , cursor.getFloat(_cost) , cursor.getString(_imei))
					);
					cursor.moveToNext();
				}
				
				itemDescriptionBookDB.close();
			}
		}
		return items;
	}

	//Checked
	@Override
	public int moveToStockBySaleID(int sale_id) {
		ContentValues cv = new ContentValues();
		cv.put(Item.COL_SALE_ID, Item.SALE_STOCK_ID);
		return super.update( Item.DATABASE_TABLE , Item.COL_SALE_ID + "=" + sale_id , cv);
	}
}
