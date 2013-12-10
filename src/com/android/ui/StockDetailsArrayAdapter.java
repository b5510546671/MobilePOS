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
/**
 * StockDetailsArrayAdapter is the InventoryLineItem details Activity.
 * @author Sikarin	Larnamwong	5510546174
 *
 */
public class StockDetailsArrayAdapter extends ArrayAdapter<Item> {
	/**
	 * items is the current Item to show in ListView.
	 */
	private List<Item> items;
	/**
	 * context is the current Activity.
	 */
	private Activity context;

	/**
	 * StockDetailsArrayAdapter is the Constructor method.
	 * @param context is the current Activity.
	 * @param items is the current Item to show in ListView.
	 */
	public StockDetailsArrayAdapter(Activity context, List<Item> items) {
		super(context, R.layout.stock_details_list_item, items);
		this.context = context;
		this.items = items;
	}

	/**
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(final int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.stock_details_list_item, null,
				true);
		TextView txtTitle = (TextView) rowView
				.findViewById(R.id.txtStockDetailListItem);
		txtTitle.setText(items.get(position).toString());
		Button btRemove = (Button) rowView.findViewById(R.id.btDelete);
		btRemove.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "Delete position : " + position, 1)
						.show();
				items.remove(position);
				notifyDataSetChanged();
			}
		});

		return rowView;
	}

	/**
	 * getItems is the method to get the current Item List
	 * @return ArrayList of the current Item list.
	 */
	public List<Item> getItems() {
		return this.items;
	}

}
