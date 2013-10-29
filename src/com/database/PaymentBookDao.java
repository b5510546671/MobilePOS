package com.database;

import com.core.Payment;

public interface PaymentBookDao {
	public long insert(Payment payment);
	public int delete(Payment payment);
	public int delete(int saleId);
	public int update(Payment payment);
	public Payment[] findAll();
	public Payment findBy(int id);
}
