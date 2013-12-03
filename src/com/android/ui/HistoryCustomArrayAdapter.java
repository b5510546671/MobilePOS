package com.android.ui;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.softspectproject.R;
import com.core.Item;
import com.core.Sale;

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
	txtTitle.setText("Sale ID : " + sales.get(position).getID() + " (  Date :" + (sales.get(position).getDate().getDay()+1)+"/"+(sales.get(position).getDate().getMonth()+1) + "/" + (sales.get(position).getDate().getYear()+1900)+")");
	 
	
	return rowView;
	}
	
	


}
