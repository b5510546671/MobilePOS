package com.database;

import java.util.Date;

import android.content.ContentValues;
import android.content.Context;

import com.core.Customer;
import com.core.Sale;

public class SaleLadgerDB extends GenericDao implements SaleLadgerDao{

	public SaleLadgerDB(Context context, String dbName, String sql, String tableName, int ver) {
		super(context, GenericDao.dName, Sale.TABLE_CREATE, Sale.DATABASE_TABLE, Sale.DATABASE_VERSION);
	}
	
	@Override
	public long insert(Sale sale) {
		ContentValues cv = new ContentValues();
        cv.put(Sale.COL_CUSTOMER_ID , sale.getCustomer().getId());
        cv.put(Sale.COL_DATE , sale.getDate());
        cv.put(Sale.COL_SALE_LINE_ITEMS , sale.getSaleLineItemString());
        return super.insert(Customer.DATABASE_TABLE, cv);
	}

	@Override
	public int delete(Sale sale) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int saleId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Sale item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Sale[] findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sale findBy(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
