package com.database;

import java.util.List;

import com.core.Cashier;

import android.content.Context;

public class CashierBookDB extends GenericDao implements CashierBookDao{

	public CashierBookDB(Context ctx) {
		super(ctx, GenericDao.dName , Cashier.TABLE_CREATE, Cashier.DATABASE_TABLE, Cashier.DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cashier insert(Cashier c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cashier remove(Cashier c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cashier> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cashier findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cashier findByID(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cashier update(Cashier c) {
		// TODO Auto-generated method stub
		return null;
	}

}
