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

/**
* Inventory DataAccessObject for SQLite.
* @author Krittayot Techasombooranakit 5510545976
*/
public class InventoryDB extends GenericDao implements InventoryDao{
	
	private Context context;
	
	/**
	 * Constructor of database.
	 * @param context od application.
	 */
	public InventoryDB(Context context){
		super(context, GenericDao.dName, Item.TABLE_CREATE, Item.DATABASE_TABLE, Item.DATABASE_VERSION);
		this.context = context;
	}

	/**
	 * @see com.database.InventoryDao#insert(com.core.InventoryLineItem)
	 */
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

	/**
	 * @see com.database.InventoryDao#insert(int, com.core.Item)
	 */
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

	/**
	 * @see com.database.InventoryDao#deleteItemByID(int)
	 */
	@Override
	public int deleteItemByID(int id) {
		return super.delete(Item.DATABASE_TABLE,GenericDao.KEY_ID + " = " + id, null);
	}
	
	/**
	 * @see com.database.InventoryDao#findAll()
	 */
	@Override
	public ArrayList<Item> findAll() {
		String[] columns = new String[]{GenericDao.KEY_ID ,Item.COL_DESCRIPTION_ID , Item.COL_IMEI , Item.COL_COST};
		return getItemFromCursor(super.get(Item.DATABASE_TABLE, columns));
	}
	
	private ArrayList<Item> getItemFromCursor(Cursor cursor){
		ArrayList<Item> itds = new ArrayList<Item>();
		HashMap<Integer, ItemDescription> maps = new HashMap<Integer, ItemDescription>();
		if(cursor != null){
			if(cursor.moveToFirst()){
				int count = cursor.getCount();
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
				int desId = cursor.getColumnIndex(Item.COL_DESCRIPTION_ID);
				int _cost = cursor.getColumnIndex(Item.COL_COST);
				int _imei = cursor.getColumnIndex(Item.COL_IMEI);
				ItemDescriptionBookDB _db = new ItemDescriptionBookDB(context);
				for(int i = 0 ; i < count ; i++){
					if(!maps.containsKey(cursor.getInt(desId))) maps.put(cursor.getInt(desId), _db.findBy(cursor.getInt(desId)));
					ItemDescription des = maps.get(cursor.getInt(desId));
					itds.add(new Item(cursor.getInt(_id), des , cursor.getFloat(_cost) , cursor.getString(_imei)));
					cursor.moveToNext();
				}
				_db.close();
			}
		} 
		return itds;
	}

	/**
	 * @see com.database.InventoryDao#findQuantity(int)
	 */
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
	
	/**
	 * @see com.database.InventoryDao#updateSaleID(int, com.core.Item)
	 */
	@Override
	public int updateSaleID(int sale_id , Item item) {
		ContentValues cv = new ContentValues();
        cv.put(GenericDao.KEY_ID, item.getID());
        cv.put(Item.COL_SALE_ID, sale_id);
        return super.update(Item.DATABASE_TABLE, GenericDao.KEY_ID + " = " + item.getID(), cv);
		//return super.update(Item.DATABASE_TABLE, item.getID(), cv);
	}

	/**
	 * @see com.database.InventoryDao#findByID(int)
	 */
	@Override
	public Item findByID(int id) {
		String[] columns = new String[]{GenericDao.KEY_ID ,Item.COL_DESCRIPTION_ID , Item.COL_IMEI , Item.COL_COST};
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

	/**
	 * @see com.database.InventoryDao#deleteItemsAndInventoryLineItemByInventoryLineItemID(int)
	 */
	@Override
	public int deleteItemsAndInventoryLineItemByInventoryLineItemID(int inventoryLineItem_id) {
		InventoryLineItemBookDB inventoryLineItemBookDB = new InventoryLineItemBookDB(getContext());
		inventoryLineItemBookDB.delete(inventoryLineItem_id);
		inventoryLineItemBookDB.close();
		return super.delete(Item.DATABASE_TABLE, Item.COL_INVENTORYLINEITEM_ID +"="+ inventoryLineItem_id , null);
	}

	/**
	 * @see com.database.InventoryDao#findByDescriptionID(int)
	 */
	@Override
	public ArrayList<Item> findByDescriptionID(int itemDescription_id) {
		String[] columns = new String[]{GenericDao.KEY_ID , Item.COL_DESCRIPTION_ID};
		Cursor cursor = super.get(Item.DATABASE_TABLE , columns , Item.COL_DESCRIPTION_ID +"="+ itemDescription_id);
		return getItemFromCursor(cursor);
	}

	
	/**
	 * @see com.database.InventoryDao#findByInventoryLineItemID(int)
	 */
	@Override
	public List<Item> findByInventoryLineItemID(int inventoryLineItem_id) {
		String[] columns = new String[]{GenericDao.KEY_ID ,Item.COL_DESCRIPTION_ID , Item.COL_IMEI , Item.COL_COST};
		Cursor cursor = super.get(Item.DATABASE_TABLE , columns , Item.COL_INVENTORYLINEITEM_ID +"="+ inventoryLineItem_id);
		return getItemFromCursor(cursor);
	}

	/**
	 * @see com.database.InventoryDao#findBySaleID(int)
	 */
	@Override
	public List<Item> findBySaleID(int id) {
		String[] columns = new String[]{GenericDao.KEY_ID ,Item.COL_DESCRIPTION_ID , Item.COL_IMEI , Item.COL_COST};
		Cursor cursor = super.get(Item.DATABASE_TABLE , columns , Item.COL_SALE_ID +"="+ id);
		return getItemFromCursor(cursor);
	}

	/**
	 * @see com.database.InventoryDao#moveToStockBySaleID(int)
	 */
	@Override
	public int moveToStockBySaleID(int sale_id) {
		ContentValues cv = new ContentValues();
		cv.put(Item.COL_SALE_ID, Item.SALE_STOCK_ID);
		return super.update( Item.DATABASE_TABLE , Item.COL_SALE_ID + "=" + sale_id , cv);
	}

	/**
	 * @see com.database.InventoryDao#findByImei(java.lang.String)
	 */
	@Override
	public Item findByImei(String id) {
		String[] columns = new String[]{GenericDao.KEY_ID ,Item.COL_DESCRIPTION_ID , Item.COL_IMEI , Item.COL_DESCRIPTION_ID};
		Cursor cursor = super.get(Item.DATABASE_TABLE, columns , Item.COL_IMEI + " like " + "'%" + id + "%'" );
		Item item = null;
		if(cursor != null){
			int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
			int itdID = cursor.getColumnIndex(Item.COL_DESCRIPTION_ID);
			int _cost = cursor.getColumnIndex(Item.COL_COST);
			int _imei = cursor.getColumnIndex(Item.COL_IMEI);
			cursor.moveToFirst();
			ItemDescriptionBookDB itemDescriptionBookDB = new ItemDescriptionBookDB(getContext());
			ItemDescription itemDescription = itemDescriptionBookDB.findBy(cursor.getInt(itdID));
			itemDescriptionBookDB.close();
			item = new Item(cursor.getInt(_id) , itemDescription , cursor.getFloat(_cost) , cursor.getString(_imei));
		}
		return item;
	}

	/**
	 * @see com.database.InventoryDao#getSaleId(com.core.Item)
	 */
	@Override
	public int getSaleId(Item item) {
		String[] columns = new String[]{Item.COL_SALE_ID};
		Cursor cursor = super.get(Item.DATABASE_TABLE , columns , GenericDao.KEY_ID + " = " + item.getID());
		if(cursor != null){
			int _saleId = cursor.getColumnIndex(Item.COL_SALE_ID);
			cursor.moveToFirst();
			return cursor.getInt(_saleId);
		}
		return 0;
	}
}
