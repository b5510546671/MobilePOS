package com.core;

import java.util.ArrayList;
import java.util.List;

import com.database.CustomerBookDB;

import android.content.Context;

public class CustomerBook {
	private CustomerBookDB db;
	private List<Customer> customers;
	private Context con; 
	public CustomerBook(Context context) {
		con = context;
		customers = new ArrayList<Customer>();
	}

	public Customer getCustomer(int id) {
		return db.findBy(id);
	}

	public void addCutomer(Customer customer) {
		db = new CustomerBookDB(con);
		db.insert(customer);
		db.close();
		customers.add(customer);
	}

	public int getAmount() {
		db = new CustomerBookDB(con);
		int i = db.findAll().length;
		db.close();
		return i;
	}

	public boolean remove(Customer customer) {
		db = new CustomerBookDB(con);
		db.delete(customer.getId());
		db.close();
		return customers.remove(customer);
	}

	public boolean remove(int id) {
		db = new CustomerBookDB(con);
		db.delete(id);
		db.close();
		for(Customer c: customers){
			if(c.getId() == id)
				return customers.remove(c);
		}
		return false;
	}

	public boolean isContains(Customer customer) {
		db = new CustomerBookDB(con);
		Customer[] x = db.findAll();
		db.close();
		for (Customer c : x) {
			if (c.getId() == customer.getId())
				{
				return true;
				}
		}

		return false;
	}

}
