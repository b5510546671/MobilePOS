package com.database;

import android.content.Context;

import com.core.SaleLineItem;

public class SaleLineItemsDB extends GenericDao implements SaleLineItemDao {

	public SaleLineItemsDB(Context ctx){
		super(ctx, null, null, null, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public long insert(SaleLineItem sli) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(SaleLineItem sli) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int sliId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(SaleLineItem sli) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SaleLineItem[] findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SaleLineItem findBy(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
