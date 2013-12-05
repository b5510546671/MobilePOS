package com.database;

import java.util.List;

import com.core.Cashier;

public interface CashierBookDao {
	public Cashier insert(Cashier c);
	public int delete(Cashier c);
	public List<Cashier> findAll();
	public Cashier findBy(String name);
	public Cashier findBy(int id);
	public Cashier update(Cashier c);
}
