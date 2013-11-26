package com.core;

import com.core.database.CustomerBook;
import com.core.database.Inventory;
import com.core.database.ItemDescriptionBook;
import com.core.database.PaymentBook;
import com.core.database.SaleLadger;

/*
 * Main store of this application.
 * @version 2013.11.26
 */

public class Store {

	
	private static Store instance;
	
	private CustomerBook customerBook;
	private Inventory inventory;
	private ItemDescriptionBook itemDescriptionBook;
	private PaymentBook paymentBook;
	private SaleLadger saleLadger;
	
	/**
	 * Creates a store and other items in the store.
	 *      
	 */
	public Store() {
		customerBook = new CustomerBook();
		inventory = new Inventory();
		itemDescriptionBook = new ItemDescriptionBook();
		paymentBook = new PaymentBook();
		saleLadger = new SaleLadger();
	}
	
	/**
	 * Get the store instance if the instance was created
	 *       create store instance otherwise.
	 * @return store instance
	 *
	 */
	public static Store getInstance() {
		if (instance == null)
			instance = new Store();
		return instance;
	}
	
	/**
	 * Get SaleLedger object.
	 * @return SaleLadger of the store
	 */
	public SaleLadger getSaleLedLadger() {
		return this.saleLadger;
	}
	
	/**
	 * Get PaymentBook object.
	 * @return PaymentBook of the store
	 *
	 */
	public PaymentBook getPaymentBook() {
		return this.paymentBook;
	}
	
	/**
	 * Get ItemDescriptionBook object.
	 * @return ItemDescriptionBook of the store
	 */
	public ItemDescriptionBook getItemDescriptionBook() {
		return this.itemDescriptionBook;
	}
	
	/**
	 * Get CustomerBook object.
	 * @return CustomerBook of the store
	 */
	public CustomerBook getCustomerBook() {
		return this.customerBook;
	}
	
	/**
	 * Get Inventory object.
	 * @return Inventory of the store
	 *
	 */
	public Inventory getInventory() {
		return this.inventory;
	}

}
