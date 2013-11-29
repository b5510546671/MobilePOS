package com.android.ui;


import com.example.softspecproject.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class StockInventoryLineItemDetail extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stock_inventory_line_item_detail);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater()
				.inflate(R.menu.stock_inventory_line_item_detail, menu);
		return true;
	}

}
