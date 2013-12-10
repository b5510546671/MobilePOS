package com.database;

import java.util.List;

import com.core.Payment;

/**
* Standard Payment DataAccessObject interface for any database.
* @author Krittayot Techasombooranakit 5510545976
*/
public interface PaymentBookDao {
	
	/**
	 * Insert payment to database.
	 * @param payment without id.
	 * @return payment with id.
	 */
	public Payment insert(Payment payment);
	
	/**
	 * Find payment by id.
	 * @param id of {@link Payment}
	 * @return {@link Payment}.
	 */
	public Payment findByID(int id);
	
	/**
	 * Get All {@link Payment} in database.
	 * @return List of {@link Payment}.
	 */
	public List<Payment> findAll();
	
	/**
	 * Delete {@link Payment} by id.
	 * @param id of {@link Payment}
	 * @return id.
	 */
	public int deleteByID(int id);
	
	/**
	 * Update {@link Payment} to database.
	 * @param payment want to update (depends on id).
	 * @return {@link Payment} updated.
	 */
	public Payment update(Payment payment);
}
