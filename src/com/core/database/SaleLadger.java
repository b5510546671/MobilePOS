package com.core.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.core.InventoryLineItem;
import com.core.Sale;
import com.database.SaleLadgerDB;

public class SaleLadger {
	private SaleLadgerDB db;
	
	public Sale getByID(Context context , int id){
		db = new SaleLadgerDB(context);
		Sale s = db.findByID(id);
		db.close();
		return s;
	}
	
	public Sale add(Context con,Sale sale){
		db = new SaleLadgerDB(con);
		Sale s = db.insert(sale);
		db.close();
		return s;
	}
	
	/*
	 * have to have payment id items customer.
	 */
	public boolean remove(Context con,Sale sale){
		if(sale.getPayment() == null || sale.getItems() == null || sale.getCustomer() == null) return false;
		db = new SaleLadgerDB(con);
		db.delete(sale);
		db.close();
		return true;
	}
	
	public List<Sale> getAllSales(Context con){
		db = new SaleLadgerDB(con);
		List<Sale> x = db.findAll();
		db.close();
		return x;
	}
	
	public List<Sale> getSaleBetween(Context con, Date from , Date to){
		db = new SaleLadgerDB(con);
		List<Sale> x = db.findByDate(from, to);
		db.close();
		return x;
	}
	
	public boolean isContains(Context con , Sale sale){
		db = new SaleLadgerDB(con);
		Sale s = db.findByID(sale.getID());
		db.close();
		return s !=null;
	}
	
	public int getQuantity(Context con){
		db = new SaleLadgerDB(con);
		int s = db.findAll().size();
		db.close();
		return s;
	}
}
