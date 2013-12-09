package com.controller;

import java.util.List;

import android.content.Context;

import com.core.Cashier;
import com.core.Store;
import com.core.database.CashierBook;

public class CashierBookController {
	private Store store;
	private static CashierBookController cashierBookController;
	
	
	
	
	public CashierBookController() {
		store = Store.getInstance();
	}
	
	public static CashierBookController getInstance(){
		if(cashierBookController == null) cashierBookController = new CashierBookController();
		return cashierBookController;
	}
	
	public List<Cashier> getAllCashier(Context con){
		return store.getCashierBook().getAllCashier(con);
	}
	
	public Cashier editCashier(Context con,Cashier cashier){
		return store.getCashierBook().editCashier(con, cashier);
	}
	
	public Cashier addCashier(Context con,Cashier cashier){
		return store.getCashierBook().addCashier(con, cashier.getName(), cashier.getUsername(), cashier.getPassword());
	}
	
	
	
	
	
	

}
