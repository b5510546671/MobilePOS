package com.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.ui.CustomArrayAdapter;
import com.core.Customer;
import com.core.InventoryLineItem;
import com.core.Item;
import com.core.ItemDescription;
import com.core.Payment;
import com.core.Sale;
import com.core.Store;

public class SaleController {
	private Store store;
	private List<Item> items;
	private static SaleController saleController;
	private Customer customer;
	private Payment payment;
	private double totalPrice = 0;
	private static HashMap<Integer, Item> itemsMap = new HashMap<Integer, Item>();

	public static SaleController getInstance() {

		if (saleController == null)
			saleController = new SaleController();
		return saleController;
	}

	public SaleController() {
		items = new ArrayList<Item>();
		store = Store.getInstance();
	}

	public Payment getPayment() {
		return this.payment;
	}

	public int getItemQuantity(Context con, int barcode) {
		// TODO get amount of item from DB
		ItemDescription itemDesc = store.getItemDescriptionBook().getItemDescriptionByBarcode(con, barcode);
		Log.d("SaleController", itemDesc.getName());

		return store.getInventory().getItemsByItemDescription(con, itemDesc).size();
	}

	public double getTotalPrice() {
		totalPrice = 0;
		for (Item i : this.items) {
			this.totalPrice += i.getItemDescription().getPrice();
		}
		return this.totalPrice;
	}
	
	public ItemDescription getItemDescriptionByBarcode(Context con,int barcode){
		return store.getItemDescriptionBook().getItemDescriptionByBarcode(con, barcode);
	}

	public void setItemList(List<Item> items) {
		this.items = items;
		itemsMap.clear();
		for (Item i : items)
			{
				itemsMap.put(i.getID(), i);
			}
	}
	
	public HashMap<Integer, Item> getItemsMap()
	{
		return this.itemsMap;
	}

	public List<Item> getItemsList() {
		return this.items;
	}

	public Payment createPayment(double price, double input) {
		this.payment = new Payment(0, price, input);
		return payment;
	}

	public boolean isMembered(Customer customer) {
		return true;
	}

	public Customer getCustomerByID(Context con,int id) {
		Customer c =store.getCustomerBook().addCutomer(con,new Customer(-1, "Sikarin", new Date(0,0,0),"Deknaew_bws@hotamil.com"));

		return c;
		// store.getCustomerBook().getCustomerByID(id);
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Sale getSale(Context con, Date date) {
		if (items == null || customer == null || payment == null) {
			items = null;
			customer = null;
			payment = null;
			return null;
		} else {
			Sale sale = new Sale(0, items, customer, payment, date);
			items = null;
			customer = null;
			payment = null;

			

			return sale;
		}

	}

	public int getAmountInList(int barcode) {
		int count = 0;
		for (Item i : items) {
			if (i.getItemDescription().getBarcode() == barcode)
				count++;
		}
		return count;
	}
	
	public Item forceAddItemToInventory(Context con,Item item,Date date)
	{
		
		List<Item> forceAddItemList = new  ArrayList<Item>();
		forceAddItemList.add(item);
		return store.getInventory().addInventoryLineItem(con, new InventoryLineItem(-10,forceAddItemList, date)).getItems().get(0);
	}

	public Item getItemfromInventory(Context con, int barcode) {

		ItemDescription itemDescription = store.getItemDescriptionBook().getItemDescriptionByBarcode(con, barcode);
		
		List<Item> itemList  = store.getInventory().getItemsByItemDescription(con, itemDescription);
		
		for(int i=0;i<itemList.size();i++){
			if(!itemsMap.containsKey(itemList.get(i).getID())){
				itemsMap.put(itemList.get(i).getID(), itemList.get(i));
				return itemList.get(i);
			}
		}
		
		return null;
		
		
		
		//return store.getInventory().getItemsByItemDescription(con, itemDescription).get(0);

	}
	
	public Sale addSaleToSaleLadger(Context con,Sale sale){
		return store.getSaleLedLadger().add(con, sale);
	}

	

}
