package com.database;

import java.util.ArrayList;
import java.util.List;

import com.core.Cashier;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
* CashierBook DataAccessObject for SQLite.
* @author Krittayot Techasombooranakit 5510545976
*/
public class CashierBookDB extends GenericDao implements CashierBookDao{

	/**
	 * Constructor of database.
	 * @param context of application.
	 */
	public CashierBookDB(Context ctx) {
		super(ctx, GenericDao.dName , Cashier.TABLE_CREATE, Cashier.DATABASE_TABLE, Cashier.DATABASE_VERSION);
	}

	/**
	 * @see com.database.CashierBookDao#insert(com.core.Cashier)
	 */
	@Override
	public Cashier insert(Cashier c) {
		ContentValues cv = new ContentValues();
        cv.put(Cashier.COL_NAME , c.getName());
        cv.put(Cashier.COL_PASSWORD , c.getPassword());
        cv.put(Cashier.COL_USERNAME , c.getUsername());
        c = new Cashier((int)super.insert(Cashier.DATABASE_TABLE, cv), c.getName() ,c.getUsername() , c.getPassword());
        return c;
	}

	/**
	 * @see com.database.CashierBookDao#delete(com.core.Cashier)
	 */
	@Override
	public int delete(Cashier c) {
		return (int)super.delete(Cashier.DATABASE_TABLE , GenericDao.KEY_ID + "=" + c.getId(), null);
	}

	/**
	 * @see com.database.CashierBookDao#findAll()
	 */
	@Override
	public List<Cashier> findAll() {
		String[] columns = new String[]{GenericDao.KEY_ID , Cashier.COL_NAME , Cashier.COL_USERNAME , Cashier.COL_PASSWORD};
		Cursor cursor = super.get(Cashier.DATABASE_TABLE, columns );
		List<Cashier> c = new ArrayList<Cashier>();
		if(cursor != null){
			int count = cursor.getCount();
			int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
			int _name = cursor.getColumnIndex(Cashier.COL_NAME);
			int _pas = cursor.getColumnIndex(Cashier.COL_PASSWORD);
			int _user = cursor.getColumnIndex(Cashier.COL_USERNAME);
			if(cursor.moveToFirst()){
				for(int i = 0 ; i < count ; i++){
					c.add(new Cashier(cursor.getInt(_id), cursor.getString(_name), cursor.getString(_user) , cursor.getString(_pas)));
					cursor.moveToNext();
				}
			}
		} 
		return c;
	}

	/**
	 * @see com.database.CashierBookDao#findBy(java.lang.String)
	 */
	@Override
	public Cashier findBy(String name) {
		String[] columns = new String[]{GenericDao.KEY_ID , Cashier.COL_NAME , Cashier.COL_USERNAME , Cashier.COL_PASSWORD};
		Cursor cursor = super.get(Cashier.DATABASE_TABLE, columns , Cashier.COL_NAME + " like " + "'" +name +"'");
		Cashier c = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
				int _name = cursor.getColumnIndex(Cashier.COL_NAME);
				int _pas = cursor.getColumnIndex(Cashier.COL_PASSWORD);
				int _user = cursor.getColumnIndex(Cashier.COL_USERNAME);
				c = new Cashier(cursor.getInt(_id), cursor.getString(_name), cursor.getString(_user) , cursor.getString(_pas));
			}
		} 
		return c;
	}

	/**
	 * @see com.database.CashierBookDao#findBy(int)
	 */
	@Override
	public Cashier findBy(int id) {
		String[] columns = new String[]{GenericDao.KEY_ID , Cashier.COL_NAME , Cashier.COL_USERNAME , Cashier.COL_PASSWORD};
		Cursor cursor = super.get(Cashier.DATABASE_TABLE, columns , GenericDao.KEY_ID + "=" + id);
		Cashier c = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
				int _name = cursor.getColumnIndex(Cashier.COL_NAME);
				int _pas = cursor.getColumnIndex(Cashier.COL_PASSWORD);
				int _user = cursor.getColumnIndex(Cashier.COL_USERNAME);
				c = new Cashier(cursor.getInt(_id), cursor.getString(_name), cursor.getString(_user) , cursor.getString(_pas));
			}
		} 
		return c;
	}

	/**
	 * @see com.database.CashierBookDao#update(com.core.Cashier)
	 */
	@Override
	public Cashier update(Cashier c) {
		ContentValues cv = new ContentValues();
        cv.put(Cashier.COL_NAME , c.getName());
        cv.put(Cashier.COL_PASSWORD , c.getPassword());
        cv.put(Cashier.COL_USERNAME , c.getUsername());
        super.update(Cashier.DATABASE_TABLE, c.getId(), cv);
        return c;
	}

}
