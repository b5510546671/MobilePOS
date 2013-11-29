package com.core.database;

import java.util.ArrayList;
import java.util.List;

import com.core.Customer;
import com.database.CustomerBookDB;

import android.content.Context;

public class CustomerBook {
	private CustomerBookDB db;

	public Customer getCustomerByID(int id) {
		return db.findBy(id);
	}

	public Customer addCutomer(Context con,Customer customer) {
		db = new CustomerBookDB(con);
		customer = db.insert(customer);
		db.close();
		return customer;
	}

	public List<Customer> getAllCustomer(Context con) {
		db = new CustomerBookDB(con);
		List<Customer> i = db.findAll();
		db.close();
		return i;
	}

	public boolean remove(Context con,Customer customer) {
		db = new CustomerBookDB(con);
		db.deleteByID(customer.getID());
		db.close();
		return true;
	}

	public boolean isContains(Context con,Customer customer) {
		db = new CustomerBookDB(con);
		Customer c = db.findBy(customer.getID());
		db.close();
		return c != null;
	}
	
	public int getAllCustomerQuantity(Context con){
		return getAllCustomer(con).size();
	}

}
