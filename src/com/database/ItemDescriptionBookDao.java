package com.database;

import java.util.List;
import com.core.ItemDescription;

/**
* Standard ItemDescription DataAccessObject interface for any database.
* @author Krittayot Techasombooranakit 5510545976
*/
public interface ItemDescriptionBookDao {
	
	/**
	 * Insert an {@link ItemDescription} to database.
	 * @param itd as {@link ItemDescription} without id.
	 * @return {@link ItemDescription} with id.
	 */
	public ItemDescription insert(ItemDescription itd);
	
	/**
	 * Update itemDescription.
	 * @param id want to update.
	 * @param itd as replacement information.
	 * @return
	 */
	public int update(int id, ItemDescription itd);
	
	/**
	 * Get All {@link ItemDescription}.
	 * @return List of all {@link ItemDescription} in database.
	 */
	public List<ItemDescription> findAll();
	
	/**
	 * Get {@link ItemDescription} by barcode.
	 * @param barcode that want to get.
	 * @return {@link ItemDescription}.
	 */
	public ItemDescription findByBarcode(String barcode);
	
	/**
	 * Delete {@link ItemDescription} by id.
	 * @param id of {@link ItemDescription}.
	 * @return id.
	 */
	public int deleteByID(int id);
	
	/**
	 * Search of contain name.
	 * @param name that want to search.
	 * @return List of {@link ItemDescription}.
	 */
	public List<ItemDescription> findByContains(String name);
	
	
	/**
	 * Find {@link ItemDescription} by id.
	 * @param id of {@link ItemDescription}.
	 * @return {@link ItemDescription}.
	 */
	public ItemDescription findBy(int id);
	
	/**
	 * Find {@link ItemDescription} by name.
	 * @param name of {@link ItemDescription}.
	 * @return {@link ItemDescription}.
	 */
	public ItemDescription findBy(String name);
}