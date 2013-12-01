package com.database;

import java.util.List;

import com.core.Customer;

/**
 * Standard SaleLadger DataAccessObject interface for any database.
 * @author Krittayot Techasombooranakit 5510545976
 */
public interface CustomerBookDao {
	
	public Customer insert(Customer customer);
	public int update(Customer customer);
	public Customer findBy(String name);
	public Customer deleteBy(String name);
	public List<Customer> findByContains(String name);
	public int deleteByID(int id);
	public List<Customer> findAll();
	public void close();
	public Customer findBy(int id);
}