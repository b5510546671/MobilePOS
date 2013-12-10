package com.android.ui;

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
/**
 * StockViewAllStockCustomArrayAdapter is the custom Adapter for the view all stock Activity.
 * @author Sikarin	Larnamwong	5510546174.
 *
 */
public class StockViewAllStockCustomArrayAdapter extends ArrayAdapter<InventoryLineItem> {
	/**
	 * inventoryLineItems is the ArrayList of the current InventoryLineItem.
	 */
	private List<InventoryLineItem> inventoryLineItems;
	/**
	 * context is the current Activity.
	 */
	private Activity context;

	/**
	 * @param context is the current Activity.
	 * @param inventoryLineItems is the ArrayList of the current InventoryLineItem.
	 */
	public StockViewAllStockCustomArrayAdapter(Activity context,
			List<InventoryLineItem> inventoryLineItems) {
		super(context, R.layout.search_listview, inventoryLineItems);
		this.context = context;
		this.inventoryLineItems = inventoryLineItems;
	}

	/**
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(final int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.search_listview, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.txtItemSearch);
		txtTitle.setText("ID : "
				+ inventoryLineItems.get(position).getID()
				+ "(Date Added : "
				+ DateManager.getDateString(inventoryLineItems.get(position)
						.getDate()) + ")");

		return rowView;
	}

	/**
	 * getInventoryLineItems is the method to get current 
	 * @return ArrayList of the InventoryLineItem.
	 */
	public List<InventoryLineItem> getInventoryLineItems() {
		return this.inventoryLineItems;
	}

}
