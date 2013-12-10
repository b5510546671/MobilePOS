package com.database;

import java.util.List;

import com.core.Customer;

/**
 * Standard CustomerBook DataAccessObject interface for any database.
 * @author Krittayot Techasombooranakit 5510545976
 */
public interface CustomerBookDao {
	
	/**
	 * Insert a customer to database.
	 * @param customer want to insert(without id).
	 * @return customer with id.
	 */
	public Customer insert(Customer customer);
	
	/**
	 * Update a customer to database.
	 * @param customer want to update(depend on id).
	 * @return customer with id.
	 */
	public int update(Customer customer);
	
	/**
	 * Find customer by name.
	 * @param name of customer.
	 * @return customer with id.
	 */
	public Customer findBy(String name);
	
	/**
	 * Delete customer by name.
	 * @param name of customer.
	 * @return customer with id.
	 */
	public Customer deleteBy(String name);
	
	/**
	 * Get customer that contain this name
	 * @param name that customer contains.
	 * @return List of customer.
	 */
	public List<Customer> findByContains(String name);
	
	/**
	 * Delete customer by name.
	 * @param id of customer.
	 * @return customer id.
	 */
	public int deleteByID(int id);
	
	/**
	 * Get all customers in database.
	 * @return List of customer.
	 */
	public List<Customer> findAll();
	
	/**
	 * Close database.
	 */
	public void close();
	
	/**
	 * Get customer by id.
	 * @param id of customer.
	 * @return customer with id.
	 */
	public Customer findBy(int id);
}