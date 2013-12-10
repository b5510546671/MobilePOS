package com.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.core.Customer;
import com.core.Payment;

/**
* Payment DataAccessObject for SQLite.
* @author Krittayot Techasombooranakit 5510545976
*/
public class PaymentBookDB extends GenericDao implements PaymentBookDao {

	/**
	 * Constructor of database.
	 * @param context of application.
	 */
	public PaymentBookDB(Context ctx) {
		super(ctx, GenericDao.dName, Payment.TABLE_CREATE, Payment.DATABASE_TABLE, Payment.DATABASE_VERSION);
	}

	/**
	 * @see com.database.PaymentBookDao#insert(com.core.Payment)
	 */
	@Override
	public Payment insert(Payment payment) {
		ContentValues cv = new ContentValues();
		cv.put(Payment.COL_INPUT_MONEY , payment.getInput());
		cv.put(Payment.COL_PRICE , payment.getPrice());
		return new Payment((int)super.insert(Payment.DATABASE_TABLE , cv) , payment.getInput() , payment.getPrice());
	}

	/**
	 * @see com.database.PaymentBookDao#findByID(int)
	 */
	@Override
	public Payment findByID(int id) {
		String[] columns = new String[]{GenericDao.KEY_ID , Payment.COL_INPUT_MONEY , Payment.COL_PRICE};
		Cursor cursor = super.get(Payment.DATABASE_TABLE , columns , id);
		Payment payment = null;
		if(cursor != null){
			int _input = cursor.getColumnIndex(Payment.COL_INPUT_MONEY);
			int _price = cursor.getColumnIndex(Payment.COL_PRICE);
			if(cursor.moveToFirst()){
				payment = new Payment(id , cursor.getDouble(_input) , cursor.getDouble(_price));
			}
		}
		return payment;
	}

	/**
	 * @see com.database.PaymentBookDao#deleteByID(int)
	 */
	@Override
	public int deleteByID(int id) {
		return (int)super.delete(Payment.DATABASE_TABLE, id);
	}

	/**
	 * @see com.database.PaymentBookDao#update(com.core.Payment)
	 */
	@Override
	public Payment update(Payment payment) {
		ContentValues cv = new ContentValues();
		cv.put(Payment.COL_INPUT_MONEY, payment.getInput());
		cv.put(Payment.COL_PRICE, payment.getPrice());
		super.update(Payment.DATABASE_TABLE , payment.getID() , cv);
		return payment;
	}

	/**
	 * @see com.database.PaymentBookDao#findAll()
	 */
	@Override
	public List<Payment> findAll() {
		String[] columns = new String[]{GenericDao.KEY_ID , Payment.COL_INPUT_MONEY , Payment.COL_PRICE};
		Cursor cursor = super.get(Payment.DATABASE_TABLE , columns );
		ArrayList<Payment> payment = new ArrayList<Payment>();
		if(cursor != null){
			int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
			int _input = cursor.getColumnIndex(Payment.COL_INPUT_MONEY);
			int _price = cursor.getColumnIndex(Payment.COL_PRICE);
			if(cursor.moveToFirst()){
				for(int i = 0 ; i < cursor.getCount() ; i++){
					payment.add( new Payment(cursor.getInt(_id) , cursor.getDouble(_input) , cursor.getDouble(_price)) );
					cursor.moveToNext();
				}
			}
		}
		return payment;
	}

}
