package com.core;

import com.core.database.CashierBook;
import com.core.database.CustomerBook;
import com.core.database.Inventory;
import com.core.database.ItemDescriptionBook;
import com.core.database.PaymentBook;
import com.core.database.SaleLadger;

/**
 * @author //TODO
 *	Singleton Store in POS system. Contains all core.database package classes as variables.
 */
public class Store {
	private static Store instance;

	private CustomerBook customerBook;
	private Inventory inventory;
	private ItemDescriptionBook itemDescriptionBook;
	private PaymentBook paymentBook;
	private SaleLadger saleLadger;
	private CashierBook cashierBook;

	private Store() {
		customerBook = new CustomerBook();
		inventory = new Inventory();
		itemDescriptionBook = new ItemDescriptionBook();
		paymentBook = new PaymentBook();
		saleLadger = new SaleLadger();
		cashierBook = new CashierBook();
	}

	/**
	 * Get Store.
	 * @return Store.
	 */
	public static Store getInstance() {
		if (instance == null)
			instance = new Store();
		return instance;
	}
	
	public CashierBook getCashierBook(){
		return this.cashierBook;
	}

	public SaleLadger getSaleLedLadger() {
		return this.saleLadger;
	}

	public PaymentBook getPaymentBook() {
		return this.paymentBook;
	}

	public ItemDescriptionBook getItemDescriptionBook() {
		return this.itemDescriptionBook;
	}

	public CustomerBook getCustomerBook() {
		return this.customerBook;
	}

	public Inventory getInventory() {
		return this.inventory;
	}

}
