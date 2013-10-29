package com.core;

import java.util.ArrayList;
import java.util.List;

import com.database.CustomerBookDB;

import android.content.Context;

public class CustomerBook {
	private CustomerBookDB db;
	private List<Customer> customers;

	public CustomerBook(Context context) {
		db = new CustomerBookDB(context);
		customers = new ArrayList<Customer>();
	}

	public Customer getCustomer(int id) {
		return db.findBy(id);
	}

	public void addCutomer(Customer customer) {
		db.insert(customer);
		customers.add(customer);
	}

	public int getAmount() {
		return db.findAll().length;
	}

	public boolean remove(Customer customer) {
		db.delete(customer.getId());
		return customers.remove(customer);
	}

	public boolean remove(int id) {
		db.delete(id);
		for(Customer c: customers){
			if(c.getId() == id)
				return customers.remove(c);
		}
		return false;
	}

	public boolean isContains(Customer customer) {
		for (Customer c : db.findAll()) {
			if (c.getId() == customer.getId())
				return true;
		}
		return false;
	}

}
