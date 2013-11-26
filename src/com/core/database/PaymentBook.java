package com.core.database;

import java.util.List;

import android.content.Context;

import com.core.Payment;
import com.database.PaymentBookDB;
/**
 * Access to the payment database which in the PaymentBookDB
 */
public class PaymentBook {
	/**
	 * PaymentBookDB is the payment in the database
	 */
	PaymentBookDB db;

	/**
	 * get the payment which relate to the input id
	 * @param context is the context that received
	 * @param id is the id of the payment
	 * @return p is the payment that have the correct id
	 */
	public Payment getPaymentByID(Context context ,int id){
		db = new PaymentBookDB(context);
		Payment p = db.findByID(id);
		db.close();
		return p;
	}
	
	/**
	 * get all the payment in the database
	 * @param con is the context
	 * @return p is the list of all payments in the database
	 */
	public List<Payment> getAllPayments(Context con){
		db = new PaymentBookDB(con);
		List<Payment> p = db.findAll();
		db.close();
		return p;
	}
	
	/**
	 * get the number of payment in database
	 * @param con is the context
	 * @return the number of payments that contain in the database
	 */
	public int getQuantity(Context con){
		return getAllPayments(con).size();
	}
}
