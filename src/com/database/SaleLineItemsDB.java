package com.database;

import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.core.Customer;
import com.core.Item;
import com.core.ItemDescription;
import com.core.SaleLineItem;

public class SaleLineItemsDB extends GenericDao implements SaleLineItemDao {

	public SaleLineItemsDB(Context ctx){
		super(ctx, GenericDao.dName, SaleLineItem.TABLE_CREATE, SaleLineItem.DATABASE_TABLE, SaleLineItem.DATABASE_VERSION);
	}

	@Override
	public long insert(SaleLineItem sli) {
		ContentValues cv = new ContentValues();
        cv.put(SaleLineItem.COL_ITEMS , sli.getItemsString());
        return super.insert(SaleLineItem.DATABASE_TABLE, cv);
	}

	@Override
	public int delete(SaleLineItem sli) {
		return delete(sli.getId());
	}

	@Override
	public int delete(int sliId) {
		return super.delete(SaleLineItem.DATABASE_TABLE, sliId);
	}

	@Override
	public int update(SaleLineItem sli) {
		ContentValues cv = new ContentValues();
        cv.put(SaleLineItem.COL_ITEMS , sli.getItemsString());
        return super.update(SaleLineItem.DATABASE_TABLE, sli.getId() ,cv);
	}

	@Override
	public SaleLineItem[] findAll() {
		String[] columns = new String[]{ GenericDao.KEY_ID , SaleLineItem.COL_ITEMS };
		Cursor cursor = super.get(ItemDescription.DATABASE_TABLE, columns);
		return null;
				// TODO;
	}

	@Override
	public SaleLineItem findBy(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
