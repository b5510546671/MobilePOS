package com.android.ui;

import java.util.ArrayList;
import java.util.List;

import com.android.softspectproject.R;
import com.android.softspectproject.R.id;
import com.android.softspectproject.R.layout;
import com.android.softspectproject.R.menu;
import com.controller.InventoryController;
import com.controller.SaleController;
import com.core.Cashier;
import com.core.InventoryLineItem;
import com.core.Item;
import com.utils.DateManager;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class StockDetailsActivity extends Activity {
	private InventoryLineItem inventoryLineItem;
	private TextView txtDetails;
	private ListView listViewItem;
	private Button btFinish;
	private StockDetailsArrayAdapter adapter;
	private List<Item> items = new ArrayList<Item>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stock_details);
		
		inventoryLineItem = (InventoryLineItem) getIntent().getSerializableExtra("inventoryLineItem");
		txtDetails = (TextView)findViewById(R.id.txtStockDetails);
		listViewItem = (ListView)findViewById(R.id.listViewStockDetails);
		btFinish = (Button)findViewById(R.id.btFinishStockDetails);
		if(inventoryLineItem.getCashier()==null) Toast.makeText(getApplicationContext(), "Cashier null", 1).show();
		
		String s = 	"ID : " + inventoryLineItem.getID()
					+"\nDate : " + DateManager.getDateString(inventoryLineItem.getDate())
					+"\nCashier : " + inventoryLineItem.getCashier().getName();
		txtDetails.setText(s);
		
		items.clear();
		for(Item i : inventoryLineItem.getItems())
		{
			items.add(i);
		}
		adapter = new StockDetailsArrayAdapter(this, items);
		listViewItem.setAdapter(adapter);
		
		btFinish.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		
				AlertDialog.Builder builder = new AlertDialog.Builder(StockDetailsActivity.this);
				
				builder.setTitle("Edit Stock Confirmation");
				builder.setMessage("Are you sure want to edit Stock?");
				
				builder.setNegativeButton("OK",new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0,int arg1) {
								InventoryLineItem newLine = new InventoryLineItem(-1, items, inventoryLineItem.getDate(), SaleController.getInstance().getCashier());
								InventoryController.getInstance().removeInvntoryLineItemFromInventory(getApplicationContext(), inventoryLineItem);
								InventoryController.getInstance().addinventoryLineItemToInventory(getApplicationContext(), newLine);
								finish();
							}
						});
				builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				});

				builder.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.stock_details, menu);
		return true;
	}

}
