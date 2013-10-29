package com.core.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.core.Sale;
import com.database.CustomerBookDB;
import com.database.SaleLadgerDB;

public class SaleLadger {
	private SaleLadgerDB db;
	private List<Sale> sales;
	private Context con;
	public SaleLadger(Context context){
		con = context;
		sales = new ArrayList<Sale>();
	}
	
	public void add(Sale sale){
		db = new SaleLadgerDB(con);
		db.insert(sale);
		db.close();
		sales.add(sale);
	}
	
	public boolean remove(Sale sale){
		db = new SaleLadgerDB(con);
		db.delete(sale);
		db.close();
		return sales.remove(sale);
	}
	
	public Sale[] getCurrentStory(){
		db = new SaleLadgerDB(con);
		Sale[] x = db.findAll();
		db.close();
		return x;
	}
}
