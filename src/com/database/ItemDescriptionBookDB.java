package com.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.core.ItemDescription;

/**
* ItemDescription DataAccessObject for SQLite.
* @author Krittayot Techasombooranakit 5510545976
*/
public class ItemDescriptionBookDB extends GenericDao implements ItemDescriptionBookDao {
	
	/**
	 * Constructor of database.
	 * @param context of application.
	 */
	public ItemDescriptionBookDB(Context context) {
		super(context, GenericDao.dName, ItemDescription.TABLE_CREATE, ItemDescription.DATABASE_TABLE, ItemDescription.DATABASE_VERSION);
	}

	/**
	 * @see com.database.ItemDescriptionBookDao#insert(com.core.ItemDescription)
	 */
	@Override
	public ItemDescription insert(ItemDescription itd) {
		ContentValues cv = new ContentValues();
		cv.put(ItemDescription.COL_NAME, itd.getName());
		cv.put(ItemDescription.COL_BARCODE, itd.getBarcode());
		cv.put(ItemDescription.COL_DESCRIPTION, itd.getItemDescription());
		cv.put(ItemDescription.COL_PRICE, itd.getPrice());
		cv.put(ItemDescription.COL_COST, itd.getCost());
		long tmp = super.insert(ItemDescription.DATABASE_TABLE, cv);
		return new ItemDescription((int)tmp , itd.getName() , itd.getItemDescription() , itd.getCost() , itd.getPrice(), itd.getBarcode());
	}

	/**
	 * @see com.database.ItemDescriptionBookDao#update(int, com.core.ItemDescription)
	 */
	@Override
	public int update(int id, ItemDescription itd) {
		ContentValues cv = new ContentValues();
		cv.put(ItemDescription.COL_NAME, itd.getName());
		cv.put(ItemDescription.COL_BARCODE, itd.getBarcode());
		cv.put(ItemDescription.COL_DESCRIPTION, itd.getItemDescription());
		cv.put(ItemDescription.COL_PRICE, itd.getPrice());
		cv.put(ItemDescription.COL_COST, itd.getCost());
        return super.update(ItemDescription.DATABASE_TABLE, GenericDao.KEY_ID + "=" + id, cv);
	}

	/**
	 * @see com.database.ItemDescriptionBookDao#findAll()
	 */
	@Override
	public ArrayList<ItemDescription> findAll() {
		String[] columns = new String[]{ GenericDao.KEY_ID , ItemDescription.COL_DESCRIPTION ,ItemDescription.COL_BARCODE, ItemDescription.COL_NAME , ItemDescription.COL_PRICE , ItemDescription.COL_COST};
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
					itds.add(new ItemDescription(cursor.getInt(_id), cursor.getString(_name) , cursor.getString(desc), cursor.getFloat(_cost)  , cursor.getFloat(price) , cursor.getString(barcode)));
					cursor.moveToNext();
				}
			}
		}
		return itds;
	}

	/**
	 * @see com.database.ItemDescriptionBookDao#deleteByID(int)
	 */
	@Override
	public int deleteByID(int id) {
		return super.delete(ItemDescription.DATABASE_TABLE,(long)id);
	}
	
	/**
	 * @see com.database.ItemDescriptionBookDao#findByContains(java.lang.String)
	 */
	@Override
	public ArrayList<ItemDescription> findByContains(String name) {
		String[] cols = new String[]{ GenericDao.KEY_ID , ItemDescription.COL_DESCRIPTION ,ItemDescription.COL_BARCODE, ItemDescription.COL_NAME , ItemDescription.COL_PRICE , ItemDescription.COL_COST};
		return getItemDescriptionsFromCursor(super.get(ItemDescription.DATABASE_TABLE, cols , ItemDescription.COL_NAME + " like " + "'%" + name + "%'" ));
	}

	/**
	 * @see com.database.ItemDescriptionBookDao#findBy(int)
	 */
	@Override
	public ItemDescription findBy(int id) {
		Cursor cursor;
		String[] cols = new String[]{ GenericDao.KEY_ID , ItemDescription.COL_DESCRIPTION ,ItemDescription.COL_BARCODE, ItemDescription.COL_NAME , ItemDescription.COL_PRICE , ItemDescription.COL_COST};
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
				
				itd= new ItemDescription(cursor.getInt(_id) , cursor.getString(_name) , cursor.getString(desc), cursor.getFloat(_cost)  , cursor.getFloat(price) , cursor.getString(barcode));
			}
		}
		return itd;
	}

	/**
	 * @see com.database.ItemDescriptionBookDao#findBy(java.lang.String)
	 */
	@Override
	public ItemDescription findBy(String name) {
		Cursor cursor;
		String[] cols = new String[]{ GenericDao.KEY_ID , ItemDescription.COL_DESCRIPTION ,ItemDescription.COL_BARCODE, ItemDescription.COL_NAME , ItemDescription.COL_PRICE , ItemDescription.COL_COST};
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
				itd= new ItemDescription(cursor.getInt(_id) , cursor.getString(_name) , cursor.getString(desc), cursor.getFloat(_cost)  , cursor.getFloat(price) , cursor.getString(barcode));
			}
		}
		return itd;
	}

	/**
	 * @see com.database.ItemDescriptionBookDao#findByBarcode(java.lang.String)
	 */
	@Override
	public ItemDescription findByBarcode(String barcode) {
		Cursor cursor;
		String[] cols = new String[]{ GenericDao.KEY_ID , ItemDescription.COL_DESCRIPTION ,ItemDescription.COL_BARCODE, ItemDescription.COL_NAME , ItemDescription.COL_PRICE , ItemDescription.COL_COST};
		cursor = super.get(ItemDescription.DATABASE_TABLE , cols , ItemDescription.COL_BARCODE + " like " + "'" + barcode +"'");
		ItemDescription itd = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int _barcode = cursor.getColumnIndex(ItemDescription.COL_BARCODE);
				int desc = cursor.getColumnIndex(ItemDescription.COL_DESCRIPTION);
				int _name = cursor.getColumnIndex(ItemDescription.COL_NAME);
				int price = cursor.getColumnIndex(ItemDescription.COL_PRICE);
				int _cost = cursor.getColumnIndex(ItemDescription.COL_COST);
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID); 
				
				itd= new ItemDescription(cursor.getInt(_id), cursor.getString(_name) , cursor.getString(desc), cursor.getFloat(_cost)  , cursor.getFloat(price) , cursor.getString(_barcode));
			}
		}
		return itd;
	}

}
