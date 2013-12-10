package com.controller;

import java.util.List;

import android.content.Context;

import com.core.Cashier;
import com.core.Store;
import com.core.database.CashierBook;

/**
 * CashierBookController is the controller of the CashierBook.
 * 
 * @author Sikarin Larnamwong 5510546174
 * 
 */
public class CashierBookController {
	/**
	 * store is the instance of the Store.
	 */
	private Store store;
	/**
	 * cashierBookController is the instance of CashierBookController.
	 */
	private static CashierBookController cashierBookController;

	/**
	 * CashierBookController is the constructor method
	 */
	public CashierBookController() {
		store = Store.getInstance();
	}

	/**
	 * getInstance is the method to get instance of CshierBookController.
	 * @return instance of CshierBookController.
	 */
	public static CashierBookController getInstance() {
		if (cashierBookController == null)
			cashierBookController = new CashierBookController();
		return cashierBookController;
	}

	/**
	 * getAllCashierFromCashierBook is the method to get all Cashier from CashierBook.
	 * @param con is the Context of the Activity.
	 * @return List of all Cashier in CashierBook.
	 */
	public List<Cashier> getAllCashierFromCashierBook(Context con) {
		return store.getCashierBook().getAllCashier(con);
	}

	/**
	 * editCashierInCashierBook is the method to edit Cashier in CashierBook.
	 * @param con is the Context of the Activity.
	 * @param cashier to edit.
	 * @return editted Cashier.
	 */
	public Cashier editCashierInCashierBook(Context con, Cashier cashier) {
		return store.getCashierBook().editCashier(con, cashier);
	}

	/**
	 * addCashierToCashierBook is the method to add Cashier to CashierBook.
	 * @param con is the Context of the Activity.
	 * @param cashier to add to CashierBook.
	 * @return added Cashier.
	 */
	public Cashier addCashierToCashierBook(Context con, Cashier cashier) {
		return store.getCashierBook().addCashier(con, cashier.getName(),
				cashier.getUsername(), cashier.getPassword());
	}

}
