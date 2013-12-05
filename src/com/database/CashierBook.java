package com.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.core.Cashier;


public class CashierBook {
	private String FILENAME = "cashierBook.txt";
	private FileInputStream fileInputStream;
	private FileOutputStream fileOutputStream;
	private String content = "1,Sikarin Larnamwong,b5510546174,sikarin1993\n2,Krittayout Techasombooranakit,b5510545976,benzsk130";
	
	private List<Cashier> cashiers = new ArrayList<Cashier>();

	
	public CashierBook() {
		
		
	}
	
	public void addNewCashierToCashierBook(){
		
	}
	
	public void removeCashierByID(){
		
	}
	
	public Cashier getCashierByUsername(){
		return null;
	}
	
	public Cashier getCashierByID(){
		return null;
	}
	
	
	
}
