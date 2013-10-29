package com.database;

import android.content.Context;

import com.core.Payment;

public class PaymentBookDB extends GenericDao implements PaymentBookDao {

	public PaymentBookDB(Context ctx){
		super(ctx, null, null, null, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public long insert(Payment payment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Payment payment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int saleId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Payment payment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Payment[] findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment findBy(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
