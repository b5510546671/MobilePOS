package com.database;

import java.util.List;

import com.core.Payment;

public interface PaymentBookDao {
	public Payment insert(Payment payment);
	public Payment findByID(int id);
	public List<Payment> findAll();
	public int deleteByID(int id);
	public Payment update(Payment payment);
}
