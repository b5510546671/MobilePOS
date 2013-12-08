package com.database;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.core.Cashier;
import com.core.InventoryLineItem;
import com.core.Item;
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
		cv.put(InventoryLineItem.COL_CASHIER_ID, ili.getCashier().getId());
		return new InventoryLineItem((int)super.insert(InventoryLineItem.DATABASE_TABLE , cv), ili.getItems(), ili.getDate() ,ili.getCashier());
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
		String[] columns = new String[]{GenericDao.KEY_ID , InventoryLineItem.COL_DATE , InventoryLineItem.COL_CASHIER_ID};
		Cursor cursor = super.get(InventoryLineItem.DATABASE_TABLE , columns , id);
		InventoryLineItem inventoryLineItem = null;
		if(cursor!= null){
			int _date = cursor.getColumnIndex(InventoryLineItem.COL_DATE);
			int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
			int _cash = cursor.getColumnIndex(InventoryLineItem.COL_CASHIER_ID);
			if(cursor.moveToFirst()){
				InventoryDB inventoryDB = new InventoryDB(getContext());
				List<Item> list = inventoryDB.findByInventoryLineItemID(cursor.getInt(_id));
				inventoryDB.close();
				CashierBookDB cashDB = new CashierBookDB(getContext());
				Cashier cashier = cashDB.findBy(cursor.getInt(_cash));
				cashDB.close();
				inventoryLineItem = new InventoryLineItem(cursor.getInt(_id),list , new Date(cursor.getInt(_date)) , cashier);
				
			}
		}
		return inventoryLineItem;
	}

	@Override
	public List<InventoryLineItem> findAll() {
		String[] columns = new String[]{GenericDao.KEY_ID , InventoryLineItem.COL_DATE , InventoryLineItem.COL_CASHIER_ID};
		Cursor cursor = super.get(InventoryLineItem.DATABASE_TABLE , columns);
		ArrayList<InventoryLineItem> inventoryLineItems = new ArrayList<InventoryLineItem>();
		if(cursor!= null){
			int _date = cursor.getColumnIndex(InventoryLineItem.COL_DATE);
			int _cash = cursor.getColumnIndex(InventoryLineItem.COL_CASHIER_ID);
			int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
			if(cursor.moveToFirst()){
				InventoryDB inventoryDB = new InventoryDB(getContext());
				HashMap<Integer, Cashier> cashMap = new HashMap<Integer, Cashier>();
				for(int i = 0 ; i < cursor.getCount() ; i++){
					int cashierId = cursor.getInt(_id);
					Cashier cashier;
					if(cashMap.containsKey(cursor.getInt(cashierId))) cashier = cashMap.get(cashierId);
					else {
						CashierBookDB cashDB = new CashierBookDB(getContext());
						cashier = cashDB.findBy(cashierId);
						cashDB.close();
						cashMap.put(cashierId, cashier);
					}
					inventoryLineItems.add(
							new InventoryLineItem(cursor.getInt(_id), inventoryDB.findByInventoryLineItemID(cursor.getInt(_id)) , new Date(cursor.getInt(_date)) , cashier)
					);
					cursor.moveToNext();
				}
				inventoryDB.close();
			}
		}
		return inventoryLineItems;
	}
}
