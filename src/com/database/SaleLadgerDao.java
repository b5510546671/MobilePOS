package com.database;

import java.util.Date;
import java.util.List;

import com.core.Sale;

/**
 * Standard SaleLadger DataAccessObject interface for any database.
 * @author Krittayot Techasombooranakit 5510545976
 */
public interface SaleLadgerDao {
	
	/**
	 * Insert Sale to database. (without id)
	 * @param sale that want to insert.
	 * @return Sale with id from table of SaleLadger store in database.
	 */
	public Sale insert(Sale sale);
	
	
	public int delete(Sale sale);
	
	/**
	 * Update Sale to database.
	 * @param sale that want to update, must have id (reference by id of cashier).
	 * @return Sale with fully items.
	 */
	public Sale update(Sale item);
	
	/**
	 * Find all sales in SaleLadger.
	 * @return all Sales with fully items.
	 **/
	public List<Sale> findAll();
	
	/**
	 * Find Sale by id.
	 * @param _id of sale(primary key in CashierBook table).
	 * @return Sale with fully items.
	 */
	public Sale findByID(int id);
	
	
	/**
	 * Find sales between dates.
	 * @param from date.
	 * @param to date.
	 * @return sales from date to date.
	 */
	public List<Sale> findByDate(Date from , Date to);
}
