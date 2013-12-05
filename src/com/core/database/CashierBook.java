package com.core.database;

import java.util.List;

import android.content.Context;

import com.core.Cashier;
import com.core.Customer;
import com.database.CashierBookDB;
import com.database.CustomerBookDB;

public class CashierBook {
	private CashierBookDB db;
	
	public Cashier getCashierByID(Context con,int id) {
		db = new CashierBookDB(con);
		Cashier c  = db.findBy(id);
		db.close();
		return c;
	}
	
	public List<Cashier> getAll(Context con) {
		db = new CashierBookDB(con);
		List<Cashier> c  = db.findAll();
		db.close();
		return c;
	}
	
	public Cashier getCashierByName(Context con,String name) {
		db = new CashierBookDB(con);
		Cashier c  = db.findBy(name);
		db.close();
		return c;
	}
	
	public Cashier getCashierById(Context con,int id) {
		db = new CashierBookDB(con);
		Cashier c  = db.findBy(id);
		db.close();
		return c;
	}
	
	public Cashier editCashier(Context con,Cashier cashier) {
		db = new CashierBookDB(con);
		Cashier c  = db.update(cashier);
		db.close();
		return c;
	}
	
}
