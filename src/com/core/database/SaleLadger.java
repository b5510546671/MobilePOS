package com.core.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.core.InventoryLineItem;
import com.core.Sale;
import com.database.SaleLadgerDB;
/**
 * Access SaleLadger in the database
 */
public class SaleLadger {
	/**
	 * the SaleLadgerDB is the SaleLadeger data base
	 */
	private SaleLadgerDB db;
	
	/**
	 * find the Saleladger by the input id
	 * @param context is the context that received
	 * @param id is the id that will be searching for
	 * @return s is the sale that found in the database
	 */
	public Sale getByID(Context context , int id){
		db = new SaleLadgerDB(context);
		Sale s = db.findByID(id);
		db.close();
		return s;
	}
	
	/**
	 * add sale to the dataBase in SaleLadgerDB
	 * @param con is the context
	 * @param sale is the sale that will be add to the database
	 * @return s is the sale that had been added
	 */
	public Sale add(Context con,Sale sale){
		db = new SaleLadgerDB(con);
		Sale s = db.insert(sale);
		db.close();
		return s;
	}
	
	/**
	 * remove the sale which need to have payment id items customer.
	 * @param con is the context
	 * @param sale is the sale that will be removed
	 * @return true
	 */
	public boolean remove(Context con,Sale sale){
		if(sale.getPayment() == null || sale.getItems() == null || sale.getCustomer() == null) return false;
		db = new SaleLadgerDB(con);
		db.delete(sale);
		db.close();
		return true;
	}
	
	/**
	 * get all the sale in SaleLadgerDB
	 * @param con is the context
	 * @return x is the list of sale that in the database
	 */
	public List<Sale> getAllSales(Context con){
		db = new SaleLadgerDB(con);
		List<Sale> x = db.findAll();
		db.close();
		return x;
	}
	
	/**
	 * get list the sales in a specific rage 
	 * @param con is the context
	 * @param from is the sale that will be started to get from
	 * @param to is the sale that will be stop of the list
	 * @return x is the list that have specific length of sales
	 */
	public List<Sale> getSaleBetween(Context con, Date from , Date to){
		db = new SaleLadgerDB(con);
		List<Sale> x = db.findByDate(from, to);
		db.close();
		return x;
	}
	
	/**
	 * find that the sale is contain in the database or not
	 * @param con is the context
	 * @param sale is the the sale that will be searching for
	 * @return true if the sale is found in the database
	 */
	public boolean isContains(Context con , Sale sale){
		db = new SaleLadgerDB(con);
		Sale s = db.findByID(sale.getID());
		db.close();
		return s !=null;
	}
	
	/**
	 * get the number of all sale in the SaleLadgerDB
	 * @param con is the context
	 * @return s is the number of all the sales that SaleLadgerDB have 
	 */
	public int getQuantity(Context con){
		db = new SaleLadgerDB(con);
		int s = db.findAll().size();
		db.close();
		return s;
	}
}
