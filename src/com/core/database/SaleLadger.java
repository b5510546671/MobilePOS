package com.core.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.core.Sale;
import com.database.SaleLadgerDB;

/**
 * @author //TODO
 * Sale contact directly with {@link SaleLadgerDao}.
 */
public class SaleLadger {
	private SaleLadgerDB db;
	
	/**
	 * @param con as context of application.
	 * @param id of sale.
	 * @return Sale includes items.
	 */
	public Sale getByID(Context context , int id){
		db = new SaleLadgerDB(context);
		Sale s = db.findByID(id);
		db.close();
		return s;
	}
	
	/**
	 * @param con as context of application.
	 * @param sale without id.
	 * @return sale with id.
	 */
	public Sale add(Context con,Sale sale){
		db = new SaleLadgerDB(con);
		Sale s = db.insert(sale);
		db.close();
		return s;
	}
	
	/**
	 * @param con as context of application.
	 * @param sale want to remove.
	 * @return true id success.
	 */
	public boolean remove(Context con,Sale sale){
		if(sale.getPayment() == null || sale.getItems() == null || sale.getCustomer() == null) return false;
		db = new SaleLadgerDB(con);
		db.delete(sale);
		db.close();
		return true;
	}
	
	/**
	 * @param con as context of application.
	 * @return List of all {@link Sale}
	 */
	public List<Sale> getAllSales(Context con){
		db = new SaleLadgerDB(con);
		List<Sale> x = db.findAll();
		db.close();
		if(x==null) return new ArrayList<Sale>();
		return x;
	}
	
	/**
	 * @param con as context of application.
	 * @param sale want to check.
	 * @return true if contains that sale in db.
	 */
	public boolean isContains(Context con , Sale sale){
		db = new SaleLadgerDB(con);
		Sale s = db.findByID(sale.getID());
		db.close();
		return s !=null;
	}
	
	/**
	 * @param con as context of application.
	 * @return quantity of {@link Sale}.
	 */
	public int getQuantity(Context con){
		db = new SaleLadgerDB(con);
		int s = db.findAll().size();
		db.close();
		return s;
	}
}
