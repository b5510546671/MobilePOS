package com.android.ui;

import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.softspectproject.R;
import com.core.Item;
import com.core.Sale;
import com.utils.DateManager;

public class HistoryCustomArrayAdapter extends ArrayAdapter<Sale> {
	private List<Sale> sales;
	private Activity context;
	

	public HistoryCustomArrayAdapter(Activity context, List<Sale> sales) {
		super(context, R.layout.sale_listview, sales);
		this.context =  context;
		this.sales = sales;
	}
	
	
	
	
	

	@Override
	public View getView(final int position, View view, ViewGroup parent) {
	LayoutInflater inflater = context.getLayoutInflater();
	View rowView= inflater.inflate(R.layout.sale_listview, null, true);
	TextView txtTitle = (TextView) rowView.findViewById(R.id.txtSaleDetail);
	txtTitle.setText("Sale ID : " + sales.get(position).getID() + " (  Date :" + DateManager.getDateString(sales.get(position).getDate())+")");
	 
	
	
	return rowView;
	}
	
	


}
