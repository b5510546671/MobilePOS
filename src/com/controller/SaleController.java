package com.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.ui.CashierCustomArrayAdapter;
import com.core.Cashier;
import com.core.Customer;
import com.core.InventoryLineItem;
import com.core.Item;
import com.core.ItemDescription;
import com.core.Payment;
import com.core.Sale;
import com.core.Store;
import com.core.database.CashierBook;
import com.utils.DateManager;

/**
 * SaleController is the controller of the Sale system.
 * 
 * @author Sikarin Larnamwong 5510546174
 * 
 */
public class SaleController {
	/**
	 * store is the instance of the Store.
	 */
	private Store store;
	/**
	 * items is the current ArrayList of Item.
	 */
	private List<Item> items;
	/**
	 * saleController is the instance of SaleController.
	 */
	private static SaleController saleController;
	/**
	 * customer is thr Current Customer.
	 */
	private Customer customer;
	/**
	 * payment is the current Payment.
	 */
	private Payment payment;
	/**
	 * cashier is the current Cashier.
	 */
	private Cashier cashier;
	/**
	 * totalPrice is the total price of the current Item List.
	 */
	private double totalPrice = 0;
	/**
	 * itemsMap is the HashMap that contains Item.
	 */
	private static HashMap<Integer, Item> itemsMap = new HashMap<Integer, Item>();

	/**
	 * getInstance is the method to get the instance of the SalController.
	 * 
	 * @return instance of the SalController.
	 */
	public static SaleController getInstance() {

		if (saleController == null)
			saleController = new SaleController();
		return saleController;
	}

	/*
	 * SaleController is the constructor method.
	 */
	public SaleController() {
		items = new ArrayList<Item>();
		store = Store.getInstance();
	}

	/**
	 * getCurrentPayment is the method to get the current Payment in make sale
	 * Process.
	 * 
	 * @return currentPayment.
	 */
	public Payment getCurrentPayment() {
		return this.payment;
	}

	/**
	 * getCurrentCashier is the method to get the current Cashier in make sale
	 * Process.
	 * 
	 * @return currentCashier.
	 */
	public Cashier getCurrentCashier() {
		return this.cashier;
	}

	/**
	 * setCurrentCashier is the method to set the current Cashier.
	 * 
	 * @param cashier
	 *            is the Cashier to set.
	 */
	public void setCurrentCashier(Cashier cashier) {
		this.cashier = cashier;
	}

	/**
	 * @param con
	 *            is the Context from the Activity.
	 * @return ArrayList of All Sale history.
	 */
	public List<Sale> getAllSaleFromSaleLadger(Context con) {
		return store.getSaleLedLadger().getAllSales(con);
	}

	/**
	 * removeSaleFromSaleLadger is the method to remove Sale from SaleLadger.
	 * 
	 * @param con
	 *            is the Context from the Activity.
	 * @param sale
	 *            is the Sale to remove from SaleLadger.
	 */
	public void removeSaleFromSaleLadger(Context con, Sale sale) {
		store.getSaleLedLadger().remove(con, sale);
	}

	/**
	 * getTotalPrice is the method to get total price from current Sale.
	 * 
	 * @return totalPrice of current Sale.
	 */
	public double getTotalPrice() {
		totalPrice = 0;
		for (Item i : this.items) {
			this.totalPrice += i.getItemDescription().getPrice();
		}
		return this.totalPrice;
	}

	/**
	 * getItemDescriptionByBarcode is the method to find ItemDescription by
	 * barcode number.
	 * 
	 * @param con
	 *            is the Context from the Activity.
	 * @param barcode
	 *            is the barcode to get ItemDescription.
	 * @return founded ItemDescription.
	 */
	public ItemDescription getItemDescriptionByBarcode(Context con,
			String barcode) {
		return store.getItemDescriptionBook().getItemDescriptionByBarcode(con,
				barcode);
	}

	/**
	 * setCurrentItemList is the method to set the current Item List when making
	 * Sale.
	 * 
	 * @param items
	 *            is the Item ArrayList to set.
	 */
	public void setCurrentItemList(List<Item> items) {
		this.items = items;
		itemsMap.clear();
		for (Item i : items) {
			if (!itemsMap.containsKey(i.getID()))
				itemsMap.put(i.getID(), i);
		}

	}

	/**
	 * getCurrentItemsList when making sale.
	 * 
	 * @return current ItemList when making Sale.
	 */
	public List<Item> getCurrentItemsList() {
		return this.items;
	}

	/**
	 * createPayment is the method to create the Payment.
	 * 
	 * @param price
	 *            of the Payment
	 * @param input
	 *            money of the Payment.
	 * @return created Payment
	 */
	public Payment createPayment(double price, double input) {
		this.payment = new Payment(0, price, input);
		return payment;
	}

	/**
	 * getCustomerByID id the method to get Customer by input ID.
	 * 
	 * @param con
	 *            con is the Context from the Activity.
	 * @param id
	 *            to get the Customer
	 * @return found Customer
	 */
	public Customer getCustomerByID(Context con, int id) {
		Customer c = store.getCustomerBook().getCustomerByID(con, id);

		return c;
	}

	/**
	 * addCustomerToCustomerBook is the method to add Customer to CustomerBook.
	 * 
	 * @param con
	 *            is the Context from the Activity.
	 * @param customer
	 *            to add to CustomerBook.
	 * @return CustomerBook added Customer.
	 */
	public Customer addCustomerToCustomerBook(Context con, Customer customer) {
		return store.getCustomerBook().addCutomer(con, customer);
	}

	/**
	 * setCurrentCustomer is the method to set current Customer.
	 * 
	 * @param customer
	 *            to set current
	 */
	public void setCurrentCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * getCurrentSale is the method to create Sale.
	 * 
	 * @param con
	 *            is the Context from the Activity.
	 * @param date
	 *            is the Date of current Sale.
	 * @return created Sale.
	 */
	public Sale getCurrentSale(Context con, Date date) {
		if (items == null || customer == null || payment == null
				|| cashier == null) {
			items = null;
			customer = null;
			payment = null;
			return null;
		} else {
			Sale sale = new Sale(-1, items, cashier, customer, payment, date);
			items = null;
			customer = null;
			payment = null;

			return sale;
		}

	}

	/**
	 * getItemfromInventoryByIMEI is the method from Inventory by input IMEI.
	 * 
	 * @param con
	 *            is the Context from the Activity.
	 * @param imei
	 *            is the IMEI to get from Inventory.
	 * @return found Item.
	 */
	public Item getItemfromInventoryByIMEI(Context con, String imei) {

		List<Item> itemList = getAllItemFromInventory(con);

		for (Item i : itemList) {
			if (!itemsMap.containsKey(i.getID()) && i.getImei().equals(imei)) {
				return i;
			}
		}
	
		
		return null;

	}
	
	/**
	 * getItemfromInventoryByBarcode is the method from Inventory by input barcode.
	 * 
	 * @param con
	 *            is the Context from the Activity.
	 * @param barcode
	 *            is the barcode to get from Inventory.
	 * @return found Item.
	 */
	public Item getItemfromInventoryByBarcode(Context con, String barcode) {

		List<Item> itemList = getAllItemFromInventory(con);

		for (Item i : itemList) {
			if (!itemsMap.containsKey(i.getID()) && i.getItemDescription().getBarcode().equals(barcode)) {
				return i;
			}
		}
		return null;

	}

	/**
	 * addSaleToSaleLadger is the method to add Sale to SaleLadger.
	 * 
	 * @param con
	 *            is the Context from the Activity.
	 * @param sale
	 *            is the Sale to add to SaleLadger.
	 * @return added Sale in SaleLadger.
	 */
	public Sale addSaleToSaleLadger(Context con, Sale sale) {
		return store.getSaleLedLadger().add(con, sale);
	}

	/**
	 * getAllItemFromInventory is the method to get All Item from Inventory.
	 * 
	 * @param con
	 *            is the Context from the Activity.
	 * @return List of all Item fromInventory
	 */
	public List<Item> getAllItemFromInventory(Context con) {
		return store.getInventory().getAllStock(con);

	}

	/**
	 * getAllItemDescriptionFromItemDescriptionBook is the method to get All
	 * ItemDescription from ItemDescriptionBook.
	 * 
	 * @param con
	 *            is the Context from the Activity.
	 * @return List of all ItemDescription from ItemDescriptionBook.
	 */
	public List<ItemDescription> getAllItemDescriptionFromItemDescriptionBook(
			Context con) {
		return store.getItemDescriptionBook().getAllItemDescriptions(con);
	}

	/**
	 * getAllCashierFromCashierBook is the method to get All Cashier from
	 * CashierBook.
	 * 
	 * @param con
	 *            is the Context from the Activity.
	 * @return List of all Cashier from CashierBook.
	 */
	public List<Cashier> getAllCashierFromCashierBook(Context con) {
		return store.getCashierBook().getAllCashier(con);

	}

}
