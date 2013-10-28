package com.core;

import java.util.ArrayList;

public class SaleLineItem {
	public static final String DATABASE_TABLE = "SaleLineItems";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists SaleLineItems (_id integer primary key autoincrement , items text not null);";
   
    public static final String COL_ITEMS = "items";
    
    private int id;
    private ArrayList<Item> items;
    
    public SaleLineItem(){
    	items = new ArrayList<Item>();
    }
    
    public void insertItem(Item item){
    	items.add(item);
    }
    
    public String getItemsString(){
    	return null;
    }
    
    /*
    public void saveToDataBase(){
    
    }*/
}
