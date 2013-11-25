package com.example.softspecproject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.core.Customer;
import com.core.InventoryLineItem;
import com.core.Item;
import com.core.ItemDescription;
import com.core.Payment;
import com.core.Sale;
import com.core.database.CustomerBook;
import com.core.database.Inventory;
import com.core.database.ItemDescriptionBook;
import com.core.database.SaleLadger;
import com.database.CustomerBookDB;
import com.database.InventoryDB;
import com.database.ItemDescriptionBookDB;
import com.database.SaleLadgerDB;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	EditText txt1;
	EditText txt2;
	EditText txt3;
	TextView txt_view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txt1 = (EditText)findViewById(R.id.txt_id);
		txt2 = (EditText)findViewById(R.id.txt_name);
		txt3 = (EditText)findViewById(R.id.editText1);
		txt_view = (TextView)findViewById(R.id.txt_view);
	
	}
	
    public void onClick_bt1(View v){
    	
    	ItemDescriptionBook itemDescriptionBook = new ItemDescriptionBook();
    	ItemDescription itemDescription = itemDescriptionBook.add(getApplicationContext(), "IPHONE 5s" , "newest apple phone" ,23000.0f , 100012);
    	
    	List<Item> items = new ArrayList<Item>();
    	items.add( new Item(0, itemDescription));
    	items.add( new Item(0, itemDescription));
    	items.add( new Item(0, itemDescription));
    	items.add( new Item(0, itemDescription));
    	InventoryLineItem inventoryLineItem = new InventoryLineItem(0, items, new Date());
    	
    	Inventory inventory = new Inventory();
    	inventory.addInventoryLineItem(getApplicationContext(), inventoryLineItem);
    	items  = inventory.getItemsByItemDescription(getApplicationContext(), itemDescription);
    	items.remove(2);
    	
    	Sale sale = new Sale(0 , items, new Customer(-1 ,"none" , new Date() ,"benz_suakularb@hotmail.com") , new Payment(0, 100000, 0) , new Date());
    	SaleLadger saleLadger = new SaleLadger();
    	saleLadger.add(getApplicationContext(), sale);
    	
    	txt_view.setText("setted");
    	/*
    	InventoryDB inventoryDB =  new InventoryDB(getApplicationContext());
    	ArrayList<Item> items = new ArrayList<Item>();
    	items.add(new Item(0, new ItemDescription(2, "aaa", "zzz", 12.22f, 102220)));
    	items.add(new Item(0, new ItemDescription(2, "aaa", "zzz", 12.22f, 102220)));
    	items.add(new Item(0, new ItemDescription(3, "aaa", "zzz", 12.22f, 102220)));
    	items.add(new Item(0, new ItemDescription(3, "aaa", "zzz", 12.22f, 102220)));
    	InventoryLineItem inventoryLineItem = new InventoryLineItem(0, items , new java.util.Date());
    	inventoryDB.insert(inventoryLineItem);
    	inventoryDB.close();*/
    }
    
    public void onClick_bt2(View v){
    	InventoryDB inventoryDB =  new InventoryDB(getApplicationContext());
    	
    	ArrayList<Item> items = new ArrayList<Item>();
    	items.add(new Item(0, new ItemDescription(2, "aaa", "zzz", 12.22f, 102220)));
    	items.add(new Item(0, new ItemDescription(2, "aaa", "zzz", 12.22f, 102220)));
    	items.add(new Item(0, new ItemDescription(3, "aaa", "zzz", 12.22f, 102220)));
    	items.add(new Item(0, new ItemDescription(3, "aaa", "zzz", 12.22f, 102220)));
    	InventoryLineItem inventoryLineItem = new InventoryLineItem(0, items , new java.util.Date());
    	inventoryLineItem = inventoryDB.insert(inventoryLineItem);
    	
    	txt_view.setText(  inventoryDB.findByInventoryLineItemID(inventoryLineItem.getID()).size() + " "  );
    	inventoryDB.close();
    }
    
    public void onClick_bt3(View v){
    	ItemDescriptionBookDB itemDescriptionBookDB = new ItemDescriptionBookDB(getApplicationContext());
    	ItemDescription itemDescription = itemDescriptionBookDB.insert(new ItemDescription(0, "helloooo", "kdskdak", 1000, 12345678));
    	
    	itemDescription = itemDescriptionBookDB.findByBarcode(itemDescription.getBarcode());
    	txt_view.setText(itemDescription.getName() + "    " + itemDescription.getBarcode() + "   " + itemDescription.getPrice());
    }

    public void onClick_bt4(View v){
    	CustomerBookDB customerBookDB = new CustomerBookDB(getApplicationContext());
    	Customer c = customerBookDB.findBy("benz");
    	
    	c.setName("helllll");
    	customerBookDB.update(c);
    	customerBookDB.close();
    	txt_view.setText(c.getID() +  "   " + c.getName());
    }
}
