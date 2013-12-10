package com.database;

import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.core.Customer;

/**
 * Customer DataAccessObject for SQLite.
 * @author Krittayot Techasombooranakit 5510545976
 */
public class CustomerBookDB extends GenericDao implements CustomerBookDao {
	Context context;

	/**
	 * Constructor of database.
	 * @param context of application.
	 */
	public CustomerBookDB(Context context) {
		super(context, GenericDao.dName, Customer.TABLE_CREATE, Customer.DATABASE_TABLE, Customer.DATABASE_VERSION);
		this.context = context;
	}

	/**
	 * @see com.database.CustomerBookDao#insert(com.core.Customer)
	 */
	@Override
	public Customer insert(Customer customer) {
		ContentValues cv = new ContentValues();
        cv.put(Customer.COL_NAME , customer.getName());
        cv.put(Customer.COL_DATE , new Date().getTime());
        cv.put(Customer.COL_EMAIL , customer.getEmail());
        customer = new Customer((int)super.insert(Customer.DATABASE_TABLE, cv), customer.getName() , customer.getRegisterDate() , customer.getEmail());
        return customer;
	}

	/**
	 * @see com.database.CustomerBookDao#findBy(java.lang.String)
	 */
	@Override
	public Customer findBy(String name) {
		String[] columns = new String[]{GenericDao.KEY_ID , Customer.COL_NAME , Customer.COL_DATE , Customer.COL_EMAIL};
		Cursor cursor = super.get(Customer.DATABASE_TABLE, columns , Customer.COL_NAME + " like " + "'" +name +"'");
		Customer customer = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
				int cus_name = cursor.getColumnIndex(Customer.COL_NAME);
				int invId = cursor.getColumnIndex(Customer.COL_DATE);
				int email = cursor.getColumnIndex(Customer.COL_EMAIL);
				customer = new Customer(cursor.getInt(_id), cursor.getString(cus_name), new Date(cursor.getLong(invId)) , cursor.getString(email));
			}
		} 
		return customer;
	}
	
	/**
	 * @see com.database.CustomerBookDao#findBy(int)
	 */
	@Override
	public Customer findBy(int id) {
		String[] columns = new String[]{GenericDao.KEY_ID , Customer.COL_NAME , Customer.COL_DATE , Customer.COL_EMAIL};
		Cursor cursor = super.get(Customer.DATABASE_TABLE, columns , GenericDao.KEY_ID + "=" + id);
		Customer customer = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
				int cus_name = cursor.getColumnIndex(Customer.COL_NAME);
				int invId = cursor.getColumnIndex(Customer.COL_DATE);
				int email = cursor.getColumnIndex(Customer.COL_EMAIL);
				customer = new Customer(cursor.getInt(_id), cursor.getString(cus_name), new Date(cursor.getLong(invId)) , cursor.getString(email));
			}
		} 
		return customer;
	}

	/**
	 * @see com.database.CustomerBookDao#deleteBy(java.lang.String)
	 */
	@Override
	public Customer deleteBy(String name) {
		Customer customer = this.findBy(name);
		super.delete(Customer.DATABASE_TABLE , Customer.COL_NAME + " like " + "'" + name + "'", null);
		return customer;
	}

	/**
	 * @see com.database.CustomerBookDao#findByContains(java.lang.String)
	 */
	@Override
	public ArrayList<Customer> findByContains(String name) {
		String[] columns = new String[]{GenericDao.KEY_ID , Customer.COL_NAME , Customer.COL_DATE , Customer.COL_EMAIL};
		Cursor cursor = super.get(Customer.TABLE_CREATE ,columns ,Customer.COL_NAME + " like " + "'%" + name + "%'");
		ArrayList<Customer> customers = new ArrayList<Customer>();
		if(cursor != null){
			if(cursor.moveToFirst()){
				int count = cursor.getCount();
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
				int cus_name = cursor.getColumnIndex(Customer.COL_NAME);
				int invId = cursor.getColumnIndex(Customer.COL_DATE);
				int email = cursor.getColumnIndex(Customer.COL_EMAIL);
				
				for(int i = 0 ; i < count ; i++){
					customers.add(new Customer(cursor.getInt(_id), cursor.getString(cus_name), new Date(cursor.getLong(invId)) , cursor.getString(email)));
					cursor.moveToNext();
				}
			}
		} 
		return customers;
	}

	/**
	 * @see com.database.CustomerBookDao#deleteByID(int)
	 */
	@Override
	public int deleteByID(int id) {
		return (int)super.delete(Customer.DATABASE_TABLE , GenericDao.KEY_ID + "=" + id, null);
	}

	/**
	 * @see com.database.CustomerBookDao#findAll()
	 */
	@Override
	public ArrayList<Customer> findAll() {
		String[] columns = new String[]{GenericDao.KEY_ID , Customer.COL_NAME , Customer.COL_DATE , Customer.COL_EMAIL};
		Cursor cursor = super.get(Customer.DATABASE_TABLE, columns);
		ArrayList<Customer> customers = new ArrayList<Customer>();
		if(cursor != null){
			if(cursor.moveToFirst()){
				int count = cursor.getCount();
				//customers = new Customer[count];
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
				int cus_name = cursor.getColumnIndex(Customer.COL_NAME);
				int invId = cursor.getColumnIndex(Customer.COL_DATE);
				int email = cursor.getColumnIndex(Customer.COL_EMAIL);
				for(int i = 0 ; i< count ; i++){
					customers.add(new Customer(cursor.getInt(_id), cursor.getString(cus_name), new Date(cursor.getLong(invId)) , cursor.getString(email)));
					cursor.moveToNext();
				}
			}
		} 
		return customers;
	}

	/**
	 * @see com.database.CustomerBookDao#update(com.core.Customer)
	 */
	@Override
	public int update(Customer customer) {
		ContentValues cv = new ContentValues();
        cv.put(Customer.COL_NAME , customer.getName());
        cv.put(Customer.COL_DATE , new Date().getTime());
        cv.put(Customer.COL_EMAIL , customer.getEmail());
        return super.update(Customer.DATABASE_TABLE, customer.getID(), cv);
	}

}
