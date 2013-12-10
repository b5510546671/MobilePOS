package com.android.ui;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.softspectproject.R;
import com.core.Sale;
import com.utils.DateManager;
/**
 * HistoryCustomArrayAdapter is the custom ArrayAdapter of the sale history ListView.
 * @author Sikarin	Larnamwong	5510546174
 *
 */
public class HistoryCustomArrayAdapter extends ArrayAdapter<Sale> {
	/**
	 * sales is the ArrayList of Sale
	 */
	private List<Sale> sales;
	/**
	 * context is the current Activity.
	 */
	private Activity context;
	/**
	 * Constructor method of the class
	 * @param context is the current Activity.
	 * @param sales is the Sale List of Adapter.
	 */
	public HistoryCustomArrayAdapter(Activity context, List<Sale> sales) {
		super(context, R.layout.sale_listview, sales);
		this.context = context;
		this.sales = sales;
	}

	/**
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(final int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.sale_listview, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.txtSaleDetail);
		txtTitle.setText("Sale ID : " + sales.get(position).getID()
				+ " (  Date :"
				+ DateManager.getDateString(sales.get(position).getDate())
				+ ")");

		return rowView;
	}

}
