package com.core.database;

import java.util.ArrayList;
import java.util.List;

import com.core.Customer;
import com.database.CustomerBookDB;

import android.content.Context;


/**
 * @author //TODO
 * CustomerBook contact directly to CustomerBookDao.
 */
public class CustomerBook {
	private CustomerBookDB db;

	/**
	 * Find {@link Customer} by id.
	 * @param con as context of application.
	 * @param id of {@link Customer}
	 * @return {@link Customer}
	 */
	public Customer getCustomerByID(Context con,int id) {
		db = new CustomerBookDB(con);
		Customer c  = db.findBy(id);
		db.close();
		return c;
	}

	/**
	 * add customer.
	 * @param con as context of application.
	 * @param customer with non id.
	 * @return customer with id.
	 */
	public Customer addCutomer(Context con,Customer customer) {
		db = new CustomerBookDB(con);
		customer = db.insert(customer);
		db.close();
		return customer;
	}

	/**
	 * Get all customer list.
	 * @param con as context of application.
	 * @return list of all {@link Customer}
	 */
	public List<Customer> getAllCustomer(Context con) {
		db = new CustomerBookDB(con);
		List<Customer> i = db.findAll();
		db.close();
		return i;
	}

	/**
	 * @param con as context of application.
	 * @param customer want to remove.
	 * @return true if success.
	 */
	public boolean remove(Context con,Customer customer) {
		db = new CustomerBookDB(con);
		db.deleteByID(customer.getID());
		db.close();
		return true;
	}

	/**
	 * @param con as context of application.
	 * @param customer that want to check.
	 * @return true if contains this customer in database.
	 */
	public boolean isContains(Context con,Customer customer) {
		db = new CustomerBookDB(con);
		Customer c = db.findBy(customer.getID());
		db.close();
		return c != null;
	}
	
	/**
	 * @param con as context of application.
	 * @return quantity of customer.
	 */
	public int getAllCustomerQuantity(Context con){
		return getAllCustomer(con).size();
	}

}
