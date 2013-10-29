package com.core;

import java.util.ArrayList;

public class SaleLineItem {
	public static final String DATABASE_TABLE = "SaleLineItems";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists SaleLineItems (_id integer primary key autoincrement , items text not null);";
   
    public static final String COL_ITEMS = "items";
    
    private int id;
    private ArrayList<Integer> itemsId;
    
    public SaleLineItem(){
    	itemsId = new ArrayList<Integer>();
    }
    
    public void insertItem(Item item){
    	itemsId.add(item.get_id());
    }
    
    public void insertItemBy(int id){
    	itemsId.add(id);
    }
    
    public String getItemsString(){
    	return null;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
