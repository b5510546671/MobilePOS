package com.android.softspectproject;

import com.core.InventoryLineItem;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class StockDetailsActivity extends Activity {
	private InventoryLineItem inventoryLineItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stock_details);
		
		inventoryLineItem = (InventoryLineItem) getIntent().getSerializableExtra("inventoryLineItem");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stock_details, menu);
		return true;
	}

}
