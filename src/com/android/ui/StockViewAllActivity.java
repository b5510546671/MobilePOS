package com.android.ui;

import java.util.ArrayList;
import java.util.List;

import com.android.softspectproject.R;
import com.controller.SaleController;
import com.core.ItemDescription;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class StockViewAllActivity extends Activity {

	private ListView listview;
	private SaleController saleController;
	private StockViewAllCustomArrayAdapter adapter;
	private List<ItemDescription> itemDescriptions = new ArrayList<ItemDescription>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stock_view_all);

		saleController = SaleController.getInstance();

		listview = (ListView) findViewById(R.id.listViewStockAll);
		itemDescriptions = new ArrayList<ItemDescription>();
		adapter = new StockViewAllCustomArrayAdapter(this, itemDescriptions);

		listview.setAdapter(adapter);

		for (ItemDescription i : saleController
				.getAllItemDescription(getApplicationContext())) {
			itemDescriptions.add(i);
		}
		adapter.notifyDataSetChanged();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stock_view_all, menu);
		return true;
	}

	@Override
	protected void onResume() {
		itemDescriptions = new ArrayList<ItemDescription>();
		for (ItemDescription i : saleController
				.getAllItemDescription(getApplicationContext())) {
			itemDescriptions.add(i);
		}
		adapter.notifyDataSetChanged();
		super.onResume();
	}

}
