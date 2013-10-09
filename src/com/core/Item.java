package com.core;

public class Item {
    public static final String DATABASE_TABLE = "Inventory3";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
        "create table if not exists Inventory3 (_id integer primary key autoincrement , status integer, description_id integer not null, inventorylineitem_id integer not null);";
   
    public static final String COL_INVENTORYLINEITEM_ID = "inventorylineitem_id";
    public static final String COL_DESCRIPTION_ID = "description_id";
    public static final String COL_STATUS = "status";
	
    public static final int STATUS_SOLD = 0;
    public static final int STATUS_STOCK = 1;
    public static final int STATUS_OTHER = 2;
    
    public int _id;
    private int status;
	private int inventoryLineItemId;
	private int descriptionId;
	
	
	public Item( int inventoryLineItemId , int descriptionId){
		this.setStatus(STATUS_STOCK);
		this.inventoryLineItemId = inventoryLineItemId;
		this.descriptionId = descriptionId;
	}
	
	public void setInventoryLineItemId(int inventoryLineItemId){
		this.inventoryLineItemId = inventoryLineItemId;
	}
	
	public int getInventoryLineItemId (){
		return inventoryLineItemId;
	}
	
	@Override
	public boolean equals(Object o) {
		if(Item.class != o.getClass()) return false;
		return _id  == ((Item)o)._id;
	}

	public int getDescriptionId (){
		return descriptionId;
	}
	
	public void setDescriptionId (int despId){
		descriptionId = despId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}