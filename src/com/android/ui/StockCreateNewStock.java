package com.android.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.controller.InventoryController;
import com.core.InventoryLineItem;
import com.core.Item;
import com.core.ItemDescription;
import com.example.softspecproject.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class StockCreateNewStock extends Activity {

	private EditText txtBarcode;
	private Button btOK;
	private Button btScanWithBarcode;
	private ListView itemListView;
	private Button btFinished;
	private InventoryController inventoryController;
	private ArrayAdapter<String> arrayAdapter;

	private List<Item> items = new ArrayList<Item>();
	private List<String> itemDisplay = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stock_create_new_stock);

		inventoryController = InventoryController.getInstance();

		txtBarcode = (EditText) findViewById(R.id.editText1);
		btOK = (Button) findViewById(R.id.button1);
		btScanWithBarcode = (Button) findViewById(R.id.button3);
		itemListView = (ListView) findViewById(R.id.listView1);
		btFinished = (Button) findViewById(R.id.button2);
		arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
				R.layout.activity_stock_add_item, itemDisplay);
		itemListView.setAdapter(arrayAdapter);
		itemListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long arg3) {
				String s = (String) itemListView.getItemAtPosition(position);
				items.remove(position);
				itemDisplay.remove(position);
				arrayAdapter.notifyDataSetChanged();
				Toast.makeText(getApplicationContext(), "remove", Toast.LENGTH_SHORT).show();

			}
		});

		btOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					Log.d("Add new Item Activity ",inventoryController.toString());
					int barcode = Integer.parseInt(txtBarcode.getText()
							.toString());
					//Toast.makeText(getApplicationContext(),"Barcode : " + barcode, Toast.LENGTH_SHORT).show();
					ItemDescription itemDes = inventoryController.getItemDescriptionByBarcode(getApplicationContext(), barcode);

					//Toast.makeText(getApplicationContext(),"Name : "+itemDes.getName(), Toast.LENGTH_SHORT).show();

					Item item = new Item(-1, itemDes);
					items.add(item);
					itemDisplay.add(item.getItemDescription().getName());
					arrayAdapter.notifyDataSetChanged();

					
					//Toast.makeText(getApplicationContext(), "Finished",Toast.LENGTH_SHORT).show();

				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "Wrong barcode",Toast.LENGTH_SHORT).show();
				}

			}
		});
		
		
		
		btFinished.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				InventoryLineItem i  =new InventoryLineItem(-1, items, new Date(0,0,0));
				
				InventoryLineItem getInven = inventoryController.addinventoryLineItemToInventory(getApplicationContext(), i);
				
				Toast.makeText(getApplicationContext(), getInven.getItems().get(0).getItemDescription().getName(),Toast.LENGTH_SHORT).show();
				//Toast.makeText(getApplicationContext(), "123", Toast.LENGTH_SHORT).show();
				
			}
			
		});
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.stock_create_new_stock, menu);
		return true;
	}

}
