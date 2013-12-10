package com.android.ui;

import java.util.ArrayList;
import java.util.List;

import com.android.softspectproject.R;
import com.core.Item;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class StockDetailsArrayAdapter extends ArrayAdapter<Item> {

	
	private List<Item> items;
	private Activity context;
	
	

	public StockDetailsArrayAdapter(Activity context, List<Item> items) {
		super(context, R.layout.stock_details_list_item, items);
		this.context =  context;
		this.items = items;
	}
	
	
	
	
	

	@Override
	public View getView(final int position, View view, ViewGroup parent) {
	LayoutInflater inflater = context.getLayoutInflater();
	View rowView= inflater.inflate(R.layout.stock_details_list_item, null, true);
	TextView txtTitle = (TextView) rowView.findViewById(R.id.txtStockDetailListItem);
	txtTitle.setText(items.get(position).toString());
	 Button btRemove = (Button) rowView.findViewById(R.id.btDelete);
	 btRemove.setOnClickListener(new OnClickListener() 
	 {
		@Override
		public void onClick(View v) {
			Toast.makeText(context, "Delete position : " +position, 1).show();
		}
	});
	
	 
	 
	return rowView;
	}
	
	public List<Item> getItems(){
		return this.items;
	}

}
