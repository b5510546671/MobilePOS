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
/**
 * CashierCustomArrayAdapter is the custom ArrayAdapter for the Item ListView
 * @author Sikarin	Larnamwong  b5510546174
 *
 */
public class CashierCustomArrayAdapter extends ArrayAdapter<Item> {

	/**
	 * items is the ArrayList of the item
	 */
	private List<Item> items;
	/**
	 * context is the activity
	 */
	private Activity context;
	
	
	/**
	 * 
	 * @param context is the context to set to this object
	 * @param items is the ArrayList of Item to set to this object
	 */
	public CashierCustomArrayAdapter(Activity context, List<Item> items) {
		super(context, R.layout.cashier_listview, items);
		this.context =  context;
		this.items = items;
	}
	
	
	
	
	

	/**
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(final int position, View view, ViewGroup parent) {
	LayoutInflater inflater = context.getLayoutInflater();
	View rowView= inflater.inflate(R.layout.cashier_listview, null, true);
	TextView txtTitle = (TextView) rowView.findViewById(R.id.txtItemListView);
	txtTitle.setText(items.get(position).toString());
	 Button btRemove = (Button) rowView.findViewById(R.id.btItemListView);
	//ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
	 
	 btRemove.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			items.remove(position);
			notifyDataSetChanged();
			
		}
	});
	return rowView;
	}
	
	public List<Item> getItems(){
		return this.items;
	}

}
