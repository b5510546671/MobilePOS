package com.database;

import java.util.List;

import com.core.Cashier;

/**
* Standard CashierBook DataAccessObject interface for any database.
* @author Krittayot Techasombooranakit 5510545976
*/
public interface CashierBookDao {
	
	/**
	 * Insert Cashier to database.
	 * @param cashier that want to insert.
	 * @return cashier with id.
	 */
	public Cashier insert(Cashier c);
	
	/**
	 * Delete Cashier by id.
	 * @param _id of cashier(primary key in CashierBook table).
	 * @return id of cashier.
	 */
	public int delete(Cashier c);
	
	/**
	 * Find All of cashier in the CashierBook Table.
	 * @return List of cashiers.
	 */
	public List<Cashier> findAll();
	
	/**
	 * Find Cashier by name.
	 * @param name of cashier.
	 * @return cashier or null if doesn't have cashier name(params).
	 */
	public Cashier findBy(String name);
	
	/**
	 * Find Cashier by name.
	 * @param id of cashier.
	 * @return cashier or null if doesn't have cashier name(params).
	 */
	public Cashier findBy(int id);
	
	/**
	 * Update Cashier to database.
	 * @param cashier that want to update, must have id (reference by id of cashier).
	 * @return cashier updated.
	 */
	public Cashier update(Cashier c);
}
