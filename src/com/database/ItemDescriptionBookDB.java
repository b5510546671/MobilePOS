package com.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.core.ItemDescription;

public class ItemDescriptionBookDB extends GenericDao implements ItemDescriptionBookDao {
	
	public ItemDescriptionBookDB(Context context) {
		super(context, GenericDao.dName, ItemDescription.TABLE_CREATE, ItemDescription.DATABASE_TABLE, ItemDescription.DATABASE_VERSION);
	}

	@Override
	public ItemDescription insert(ItemDescription itd) {
		ContentValues cv = new ContentValues();
		cv.put(ItemDescription.COL_NAME, itd.getName());
		cv.put(ItemDescription.COL_BARCODE, itd.getBarcode());
		cv.put(ItemDescription.COL_DESCRIPTION, itd.getItemDescription());
		cv.put(ItemDescription.COL_PRICE, itd.getPrice());
		cv.put(ItemDescription.COL_COST, itd.getPrice());
		long tmp = super.insert(ItemDescription.DATABASE_TABLE, cv);
		return new ItemDescription((int)tmp , itd.getName() , itd.getItemDescription() , itd.getCost() , itd.getPrice(), itd.getBarcode());
	}

	@Override
	public int update(int id, ItemDescription itd) {
		ContentValues cv = new ContentValues();
		cv.put(ItemDescription.COL_NAME, itd.getName());
		cv.put(ItemDescription.COL_BARCODE, itd.getBarcode());
		cv.put(ItemDescription.COL_DESCRIPTION, itd.getItemDescription());
		cv.put(ItemDescription.COL_PRICE, itd.getPrice());
        return super.update(ItemDescription.DATABASE_TABLE, GenericDao.KEY_ID + "=" + id, cv);
	}

	@Override
	public ArrayList<ItemDescription> findAll() {
		String[] columns = new String[]{ GenericDao.KEY_ID , ItemDescription.COL_DESCRIPTION ,ItemDescription.COL_BARCODE, ItemDescription.COL_NAME , ItemDescription.COL_PRICE};
		return getItemDescriptionsFromCursor(super.get(ItemDescription.DATABASE_TABLE, columns));
	}
	
	private ArrayList<ItemDescription> getItemDescriptionsFromCursor(Cursor cursor){
		ArrayList<ItemDescription> itds = new ArrayList<ItemDescription>();
		if(cursor != null){
			if(cursor.moveToFirst()){
				int count = cursor.getCount();
				for(int i = 0 ; i < count ; i++){
					int barcode = cursor.getColumnIndex(ItemDescription.COL_BARCODE);
					int desc = cursor.getColumnIndex(ItemDescription.COL_DESCRIPTION);
					int _name = cursor.getColumnIndex(ItemDescription.COL_NAME);
					int price = cursor.getColumnIndex(ItemDescription.COL_PRICE);
					int _cost = cursor.getColumnIndex(ItemDescription.COL_COST);
					int _id = cursor.getColumnIndex(GenericDao.KEY_ID); 
					itds.add(new ItemDescription(cursor.getInt(_id), cursor.getString(_name) , cursor.getString(desc), cursor.getFloat(_cost)  , cursor.getFloat(price) , cursor.getInt(barcode)));
					cursor.moveToNext();
				}
			}
		}
		return itds;
	}

	@Override
	public int deleteByID(int id) {
		return super.delete(ItemDescription.DATABASE_TABLE,(long)id);
	}
	
	@Override
	public ArrayList<ItemDescription> findByContains(String name) {
		String[] cols = new String[]{ GenericDao.KEY_ID , ItemDescription.COL_DESCRIPTION ,ItemDescription.COL_BARCODE, ItemDescription.COL_NAME , ItemDescription.COL_PRICE};
		return getItemDescriptionsFromCursor(super.get(ItemDescription.DATABASE_TABLE, cols , ItemDescription.COL_NAME + " like " + "'%" + name + "%'" ));
	}

	@Override
	public ItemDescription findBy(int id) {
		Cursor cursor;
		String[] cols = new String[]{ GenericDao.KEY_ID , ItemDescription.COL_DESCRIPTION ,ItemDescription.COL_BARCODE, ItemDescription.COL_NAME , ItemDescription.COL_PRICE};
		cursor = super.get(ItemDescription.DATABASE_TABLE , cols , GenericDao.KEY_ID + "=" + id);
		ItemDescription itd = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int barcode = cursor.getColumnIndex(ItemDescription.COL_BARCODE);
				int desc = cursor.getColumnIndex(ItemDescription.COL_DESCRIPTION);
				int _name = cursor.getColumnIndex(ItemDescription.COL_NAME);
				int price = cursor.getColumnIndex(ItemDescription.COL_PRICE);
				int _cost = cursor.getColumnIndex(ItemDescription.COL_COST);
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID); 
				
				itd= new ItemDescription(cursor.getInt(_id) , cursor.getString(_name) , cursor.getString(desc), cursor.getFloat(_cost)  , cursor.getFloat(price) , cursor.getInt(barcode));
			}
		}
		return itd;
	}

	@Override
	public ItemDescription findBy(String name) {
		Cursor cursor;
		String[] cols = new String[]{ GenericDao.KEY_ID , ItemDescription.COL_DESCRIPTION ,ItemDescription.COL_BARCODE, ItemDescription.COL_NAME , ItemDescription.COL_PRICE};
		cursor = super.get(ItemDescription.DATABASE_TABLE , cols , ItemDescription.COL_NAME + " like " + "'" +name +"'");
		ItemDescription itd = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int barcode = cursor.getColumnIndex(ItemDescription.COL_BARCODE);
				int desc = cursor.getColumnIndex(ItemDescription.COL_DESCRIPTION);
				int _name = cursor.getColumnIndex(ItemDescription.COL_NAME);
				int price = cursor.getColumnIndex(ItemDescription.COL_PRICE);
				int _cost = cursor.getColumnIndex(ItemDescription.COL_COST);
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID); 
				itd= new ItemDescription(cursor.getInt(_id) , cursor.getString(_name) , cursor.getString(desc), cursor.getFloat(_cost)  , cursor.getFloat(price) , cursor.getInt(barcode));
			}
		}
		return itd;
	}

	@Override
	public ItemDescription findByBarcode(int barcode) {
		Cursor cursor;
		String[] cols = new String[]{ GenericDao.KEY_ID , ItemDescription.COL_DESCRIPTION ,ItemDescription.COL_BARCODE, ItemDescription.COL_NAME , ItemDescription.COL_PRICE};
		cursor = super.get(ItemDescription.DATABASE_TABLE , cols , ItemDescription.COL_BARCODE + "=" + barcode);
		ItemDescription itd = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int _barcode = cursor.getColumnIndex(ItemDescription.COL_BARCODE);
				int desc = cursor.getColumnIndex(ItemDescription.COL_DESCRIPTION);
				int _name = cursor.getColumnIndex(ItemDescription.COL_NAME);
				int price = cursor.getColumnIndex(ItemDescription.COL_PRICE);
				int _cost = cursor.getColumnIndex(ItemDescription.COL_COST);
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID); 
				
				itd= new ItemDescription(cursor.getInt(_id), cursor.getString(_name) , cursor.getString(desc), cursor.getFloat(_cost)  , cursor.getFloat(price) , cursor.getInt(barcode));
			}
		}
		return itd;
	}

}
