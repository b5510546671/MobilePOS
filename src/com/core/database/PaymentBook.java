package com.core.database;

import java.util.List;

import android.content.Context;

import com.core.Payment;
import com.database.PaymentBookDB;

/**
 * @author //TODO
 * PaymentBook contact directly to {@link PaymentBookDB}
 */
public class PaymentBook {
	PaymentBookDB db;

	/**
	 * @param con as context of application.text
	 * @param id of {@link Payment}
	 * @return {@link Payment}
	 */
	public Payment getPaymentByID(Context context ,int id){
		db = new PaymentBookDB(context);
		Payment p = db.findByID(id);
		db.close();
		return p;
	}
	
	/**
	 * @param con as context of application.
	 * @return List of all {@link Payment}.
	 */
	public List<Payment> getAllPayments(Context con){
		db = new PaymentBookDB(con);
		List<Payment> p = db.findAll();
		db.close();
		return p;
	}
	
	/**
	 * @param con as context of application.
	 * @return quantity of {@link Payment}
	 */
	public int getQuantity(Context con){
		return getAllPayments(con).size();
	}
}
