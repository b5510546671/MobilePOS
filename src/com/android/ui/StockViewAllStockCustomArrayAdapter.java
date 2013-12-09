package com.android.ui;

import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.softspectproject.R;
import com.core.InventoryLineItem;
import com.utils.DateManager;

public class StockViewAllStockCustomArrayAdapter extends ArrayAdapter<InventoryLineItem> {
	private List<InventoryLineItem> inventoryLineItems;
	private Activity context;

	public StockViewAllStockCustomArrayAdapter(Activity context, List<InventoryLineItem> inventoryLineItems) {
		super(context, R.layout.search_listview, inventoryLineItems);
		this.context =  context;
		this.inventoryLineItems = inventoryLineItems;
	}
	
	
	
	
	

	@Override
	public View getView(final int position, View view, ViewGroup parent) {
	LayoutInflater inflater = context.getLayoutInflater();
	View rowView= inflater.inflate(R.layout.search_listview, null, true);
	TextView txtTitle = (TextView) rowView.findViewById(R.id.txtItemSearch);
	txtTitle.setText("ID : " + inventoryLineItems.get(position).getID() + "(Date Added : "+DateManager.getDateString(inventoryLineItems.get(position).getDate()) +")" );
	 
	
	return rowView;
	}
	
	public List<InventoryLineItem> getInventoryLineItems(){
		return this.inventoryLineItems;
	}


}
