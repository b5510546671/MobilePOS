package com.core;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.database.CashierBookDB;

public class CashierBook {
	private List<Cashier> cashier;
	private Context context;
	private CashierBookDB db;

	public CashierBook(Context context) {
		this.context = context;
		cashier = new ArrayList<Cashier>();
	}
	
	public Cashier getCashier(int id) {
		db = new CashierBookDB(context);
		Cashier c = db.findBy(id);
		db.close();
		return c;
	}
	
	public void addCashier(Cashier c){
		db = new CashierBookDB(context);
		db.insert(c);
		db.close();
		cashier.add(c);
	}
	
	public boolean remove(Cashier c){
		db = new CashierBookDB(context);
		db.delete(c.getId());
		db.close();
		return cashier.remove(c);
	}
	
	public boolean remove(int id){
		db = new CashierBookDB(context);
		db.delete(id);
		db.close();
		for(Cashier c: cashier){
			if(c.getId() == id){
				
				return cashier.remove(c);
			}
				
		}
		return false;
	}
	
	public boolean isContains(Cashier c){
		return cashier.contains(c);
	}
}
