package com.database;

import com.core.Cashier;

/**
 * Standard CashierBook DataAccessObject interface for any database.
 * @author Krittayot Techasombooranakit 5510545976
 */
public interface CashierBookDao {
	
	/**
	 * Insert Cashier to database.
	 * @param cashier that want to insert.
	 * @return _id in table of CashierBook store in database.
	 */
	public long insert(Cashier cashier);
	
	/**
	 * Update Cashier to database.
	 * @param cashier that want to update, must have id (reference by id of cashier).
	 * @return _id in table of CashierBook store in database.
	 */
	public int update(Cashier cashier);
	
	/**
	 * Find Cashier by name.
	 * @param name of cashier.
	 * @return a cashier or null if doesn't have cashier name(params).
	 */
	public Cashier findBy(String name);
	
	/**
	 * Delete Cashier by id.
	 * @param _id of cashier(primary key in CashierBook table).
	 * @return Cashier that deleted.
	 */
	public Cashier delete(int id);
	
	/**
	 * Delete Cashier by name.
	 * @param name of cashier.
	 * @return Cashier that deleted.
	 */
	public Cashier deleteBy(String name);
	
	/**
	 * Delete Cashier by name.
	 * @param name of cashier.
	 * @return Cashier that deleted.
	 */
	public Cashier[] findByContains(String name);
	
	/**
	 * Find All of cashier in the CashierBook Table.
	 * @param name of cashier.
	 * @return Cashier that deleted.
	 */
	public Cashier[] findAll();
	
	
	/**
	 * @param _id of cashier(primary key in CashierBook table).
	 * @return a cashier.
	 */
	public Cashier findBy(int id);
}
