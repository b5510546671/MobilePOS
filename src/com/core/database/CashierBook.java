package com.core.database;

import java.util.List;

import android.content.Context;

import com.core.Cashier;
import com.core.Customer;
import com.database.CashierBookDB;
import com.database.CustomerBookDB;
import com.database.GenericDao;

/**
 * @author //TODO
 * CashierBook contact directly to CashierBookDao.
 */
public class CashierBook {
	private CashierBookDB db;
	
	/**
	 * @param con as context of application as context of application.
	 * @param name of cashier.
	 * @param userName
	 * @param password
	 * @return cashier with id.
	 */
	public Cashier addCashier(Context con ,String name ,String userName ,String password){
		db = new CashierBookDB(con);
		Cashier c  = db.insert(new Cashier(0, name, userName, password));
		db.close();
		return c;
	}
	
	/**
	 * @param con as context of application
	 * @param id of {@link Cashier}.
	 * @return {@link Cashier}.
	 */
	public Cashier getCashierByID(Context con,int id) {
		db = new CashierBookDB(con);
		Cashier c  = db.findBy(id);
		db.close();
		return c;
	}
	
	/**
	 * @param con as context of application
	 * @return List of all {@link Cashier}.
	 */
	public List<Cashier> getAllCashier(Context con) {
		db = new CashierBookDB(con);
		List<Cashier> c  = db.findAll();
		db.close();
		return c;
	}
	
	/**
	 * @param con as context of application
	 * @param name of {@link Cashier}.
	 * @return {@link Cashier}.
	 */
	public Cashier getCashierByName(Context con,String name) {
		db = new CashierBookDB(con);
		Cashier c  = db.findBy(name);
		db.close();
		return c;
	}
	
	/**
	 * @param con as context of application
	 * @param id of {@link Cashier}
	 * @return {@link Cashier}
	 */
	public Cashier getCashierById(Context con,int id) {
		db = new CashierBookDB(con);
		Cashier c  = db.findBy(id);
		db.close();
		return c;
	}
	
	/**
	 * @param con as context of application
	 * @param cashier want to edit.
	 * @return {@link Cashier}
	 */
	public Cashier editCashier(Context con,Cashier cashier) {
		db = new CashierBookDB(con);
		Cashier c  = db.update(cashier);
		db.close();
		return c;
	}
	
}
