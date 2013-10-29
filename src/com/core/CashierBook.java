package com.core;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.database.CashierBookDB;

public class CashierBook {
	private CashierBookDB db;
	private List<Cashier> cashier;

	public CashierBook(Context context) {
		db = new CashierBookDB(context);
		cashier = new ArrayList<Cashier>();
	}

	public Cashier getCashier(int id) {
		return db.findBy(id);
	}
	
	public void addCashier(Cashier c){
		db.insert(c);
		cashier.add(c);
	}
	
	public boolean remove(Cashier c){
		db.delete(c.getId());
		return cashier.remove(c);
	}
	
	public boolean remove(int id){
		db.delete(id);
		for(Cashier c: cashier){
			if(c.getId() == id)
				return cashier.remove(c);
		}
		return false;
	}
	
	public boolean isContains(Cashier c){
		return cashier.contains(c);
	}
}
