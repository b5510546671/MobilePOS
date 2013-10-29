package com.controller;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.core.Item;
import com.core.Sale;
import com.core.SaleLineItem;
import com.core.Store;
import com.database.InventoryDB;

public class SaleController {
	private Store store;
	private Sale sale;
	private List<SaleLineItem> saleLineItems;
	private List<Item> saleItems;
	private InventoryDB inventoryDB;
	private SaleController saleController;

	public SaleController() {
		this.store = Store.getInstance();
		saleItems = new ArrayList<Item>();

	}

	public void addItemToSale(Item item) {
		for (SaleLineItem s : saleLineItems) {
			if (s.getId() == item.get_id())
				s.addItem(item);
			return;
		}
		SaleLineItem add = new SaleLineItem();
		add.addItem(item);
		saleLineItems.add(add);
	}

	public void createNewSale() {
		sale = new Sale();
	}

	public void saveSaleToSaleLedger(Context con) {
		store.getSaleLedger().add(con, sale);
	}

	public SaleController getInstance() {
		if (saleController == null)
			saleController = new SaleController();
		return saleController;
	}

	public List<Item> getAllSaleItems() {
		return saleItems;
	}

	public Item[] getAllSaleLineItems() {
		return (Item[]) sale.getSaleLineItems().toArray();
	}

	public double getTotalSalePrice() {
		return sale.getTotalPrice();
	}

	public void removeFromDatabase(Context context) {
		inventoryDB = new InventoryDB(context);
		for (SaleLineItem s : saleLineItems) {
			Item[] items = s.getItems();
			int[] itemsID = new int[items.length];

			for (int i = 0; i < items.length; i++) {
				itemsID[i] = items[i].get_id();
			}
			inventoryDB.deleteAll(itemsID);
		}
		inventoryDB.close();

	}

}
