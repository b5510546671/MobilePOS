package com.android.ui;

import java.util.List;

import com.android.softspectproject.R;
import com.core.Item;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class SearchCustomArrayAdapter extends ArrayAdapter<Item> {
	private List<Item> items;
	private Activity context;

	public SearchCustomArrayAdapter(Activity context, List<Item> items) {
		super(context, R.layout.search_listview, items);
		this.context =  context;
		this.items = items;
	}
	
	
	
	
	

	@Override
	public View getView(final int position, View view, ViewGroup parent) {
	LayoutInflater inflater = context.getLayoutInflater();
	View rowView= inflater.inflate(R.layout.search_listview, null, true);
	TextView txtTitle = (TextView) rowView.findViewById(R.id.txtItemSearch);
	txtTitle.setText(items.get(position).toString());
	 
	
	return rowView;
	}
	
	public List<Item> getItems(){
		return this.items;
	}


}
