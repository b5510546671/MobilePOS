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
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<Item> {

	
	private List<Item> items;
	private Activity context;

	public CustomArrayAdapter(Activity context, List<Item> items) {
		super(context, R.layout.list_view, items);
		this.context =  context;
		this.items = items;
	}
	
	
	
	
	

	@Override
	public View getView(final int position, View view, ViewGroup parent) {
	LayoutInflater inflater = context.getLayoutInflater();
	View rowView= inflater.inflate(R.layout.list_view, null, true);
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
