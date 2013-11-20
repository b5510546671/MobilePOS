package com.core.database;

import java.util.List;

import android.content.Context;

import com.core.Payment;
import com.database.PaymentBookDB;

public class PaymentBook {
	PaymentBookDB db;

	public Payment getPaymentByID(Context context ,int id){
		db = new PaymentBookDB(context);
		Payment p = db.findByID(id);
		db.close();
		return p;
	}
	
	public List<Payment> getAllPayments(Context con){
		db = new PaymentBookDB(con);
		List<Payment> p = db.findAll();
		db.close();
		return p;
	}
	
	public int getQuantity(Context con){
		return getAllPayments(con).size();
	}
}
