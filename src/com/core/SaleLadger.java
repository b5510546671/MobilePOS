package com.core;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.database.CustomerBookDB;
import com.database.SaleLadgerDB;

public class SaleLadger {
	private SaleLadgerDB db;
	private List<Sale> sales;
	
	public SaleLadger(Context context){
		db = new SaleLadgerDB(context);
		sales = new ArrayList<Sale>();
	}
	
	public void add(Sale sale){
		db.insert(sale);
		sales.add(sale);
	}
	
	public boolean remove(Sale sale){
		db.delete(sale);
		return sales.remove(sale);
	}
	
	
}
