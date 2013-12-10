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
/**
 * SearchCustomArrayAdapter is the custom ArrayAdapter use in the search tap ListView.
 * @author Sikarin	Larnamwong	5510546174
 *
 */
public class SearchCustomArrayAdapter extends ArrayAdapter<Item> {
	/**
	 * items is the List of the Item.
	 */
	private List<Item> items;
	/**
	 * context is the current Activity.
	 */
	private Activity context;
	/**
	 * SearchCustomArrayAdapter is the Constructor method.
	 * @param context is the current Activity.
	 * @param items is the List of the Item.
	 */
	public SearchCustomArrayAdapter(Activity context, List<Item> items) {
		super(context, R.layout.search_listview, items);
		this.context =  context;
		this.items = items;
	}
	

	/**
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(final int position, View view, ViewGroup parent) {
	LayoutInflater inflater = context.getLayoutInflater();
	View rowView= inflater.inflate(R.layout.search_listview, null, true);
	TextView txtTitle = (TextView) rowView.findViewById(R.id.txtItemSearch);
	txtTitle.setText(items.get(position).toString());
	 
	
	return rowView;
	}
	
	/**
	 * getItems is the method to get the Item list
	 * @return List of the Item
	 */
	public List<Item> getItems(){
		return this.items;
	}


}
