package com.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.core.InventoryLineItem;
import com.core.Payment;
import com.core.Sale;

public class InventoryLineItemBookDB extends GenericDao implements
		InventoryLineItemBookDao {

	public InventoryLineItemBookDB(Context ctx) {
		super(ctx, GenericDao.dName, InventoryLineItem.TABLE_CREATE, InventoryLineItem.DATABASE_TABLE, InventoryLineItem.DATABASE_VERSION);
	}

	@Override
	public InventoryLineItem insert(InventoryLineItem ili) {
		ContentValues cv = new ContentValues();
		cv.put(InventoryLineItem.COL_DATE, ili.getDate().getTime());
		return new InventoryLineItem((int)super.insert(InventoryLineItem.DATABASE_TABLE , cv), ili.getItems(), ili.getDate());
	}

	@Override
	public int delete(int inventoryLineItemId) {
		return (int)super.delete(InventoryLineItem.DATABASE_TABLE , inventoryLineItemId);
	}

	/*@Override
	public int update(InventoryLineItem ili) {
		// TODO Auto-generated method stub
		return 0;
	}*/

	@Override
	public InventoryLineItem findByID(int id) {
		String[] columns = new String[]{GenericDao.KEY_ID , InventoryLineItem.COL_DATE};
		Cursor cursor = super.get(InventoryLineItem.DATABASE_TABLE , columns , id);
		InventoryLineItem inventoryLineItem = null;
		if(cursor!= null){
			int _date = cursor.getColumnIndex(InventoryLineItem.COL_DATE);
			int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
			if(cursor.moveToFirst()){
				InventoryDB inventoryDB = new InventoryDB(getContext());
				inventoryLineItem = new InventoryLineItem(cursor.getInt(_id), inventoryDB.findByInventoryLineItemID(cursor.getInt(_id)) , new Date(cursor.getInt(_date)) );
				inventoryDB.close();
			}
		}
		return inventoryLineItem;
	}

	@Override
	public List<InventoryLineItem> findAll() {
		String[] columns = new String[]{GenericDao.KEY_ID , InventoryLineItem.COL_DATE};
		Cursor cursor = super.get(InventoryLineItem.DATABASE_TABLE , columns);
		ArrayList<InventoryLineItem> inventoryLineItems = new ArrayList<InventoryLineItem>();
		if(cursor!= null){
			int _date = cursor.getColumnIndex(InventoryLineItem.COL_DATE);
			int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
			if(cursor.moveToFirst()){
				InventoryDB inventoryDB = new InventoryDB(getContext());
				for(int i = 0 ; i < cursor.getCount() ; i++){
					inventoryLineItems.add(
							new InventoryLineItem(cursor.getInt(_id), inventoryDB.findByInventoryLineItemID(cursor.getInt(_id)) , new Date(cursor.getInt(_date)) )
					);
					cursor.moveToNext();
				}
				inventoryDB.close();
			}
		}
		return inventoryLineItems;
	}

	@Override
	public List<InventoryLineItem> findByDate(Date from, Date to) {
		String[] columns = new String[]{GenericDao.KEY_ID , InventoryLineItem.COL_DATE};
		Cursor cursor = super.get(InventoryLineItem.DATABASE_TABLE , columns , InventoryLineItem.COL_DATE + " BETWEEN " + from.getTime() + " AND " + to.getTime());
		ArrayList<InventoryLineItem> inventoryLineItems = new ArrayList<InventoryLineItem>();
		if(cursor!= null){
			int _date = cursor.getColumnIndex(InventoryLineItem.COL_DATE);
			int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
			if(cursor.moveToFirst()){
				InventoryDB inventoryDB = new InventoryDB(getContext());
				for(int i = 0 ; i < cursor.getCount() ; i++){
					inventoryLineItems.add(
							new InventoryLineItem(cursor.getInt(_id), inventoryDB.findByInventoryLineItemID(cursor.getInt(_id)) , new Date(cursor.getInt(_date)) )
					);
					cursor.moveToNext();
				}
				inventoryDB.close();
			}
		}
		return inventoryLineItems;
	}
}
