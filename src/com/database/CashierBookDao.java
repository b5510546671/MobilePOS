package com.database;

import java.util.List;

import com.core.Cashier;

public interface CashierBookDao {
	public Cashier insert(Cashier c);
	public Cashier remove(Cashier c);
	public List<Cashier> findAll();
	public Cashier findByName(String name);
	public Cashier findByID(int i);
	public Cashier update(Cashier c);
}
