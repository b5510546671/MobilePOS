package com.android.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.controller.InventoryController;
import com.controller.SaleController;
import com.core.InventoryLineItem;
import com.core.Item;
import com.core.ItemDescription;
import com.utils.DateManager;
import com.android.softspectproject.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
	private CashierCustomArrayAdapter arrayAdapter;

	private List<Item> items = new ArrayList<Item>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stock_create_new_stock);

		inventoryController = InventoryController.getInstance();

		txtBarcode = (EditText) findViewById(R.id.txtAddNewStockID);
		btOK = (Button) findViewById(R.id.btItemListView);
		btScanWithBarcode = (Button) findViewById(R.id.btStockBarcodeScan);
		itemListView = (ListView) findViewById(R.id.listViewProductSearch);
		btFinished = (Button) findViewById(R.id.btLoginFallSignUp);
		arrayAdapter = new CashierCustomArrayAdapter(this, items);
		itemListView.setAdapter(arrayAdapter);
		itemListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long arg3) {
				// String s = (String) itemListView.getItemAtPosition(position);
				items.remove(position);

				arrayAdapter.notifyDataSetChanged();
				// Toast.makeText(getApplicationContext(), "remove",
				// Toast.LENGTH_SHORT).show();

			}
		});

		btOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					final String barcode = txtBarcode.getText().toString();
					barcode.replace(" ", "");
					Toast.makeText(getApplicationContext(), barcode, 1).show();
					ItemDescription itemDes = inventoryController.getItemDescriptionByBarcode(getApplicationContext(), barcode);
					if (itemDes == null)
						{
							//throw new Exception();
						}
					else {
						AlertDialog.Builder alert = new AlertDialog.Builder(
								StockCreateNewStock.this);

						alert.setTitle("Product IMEI number");
						alert.setMessage("Inser the Imei of the product");

						// Set an EditText view to get user input
						final EditText input = new EditText(StockCreateNewStock.this);
						alert.setView(input);

						alert.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										String imei = input.getText().toString();

										Item item = new Item(-1, inventoryController.getItemDescriptionByBarcode(getApplicationContext(), barcode), imei);
										items.add(item);

										arrayAdapter.notifyDataSetChanged();
									}
								});

						alert.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
									}
								});

						alert.show();
					}

				} catch (Exception e) {
					AlertDialog.Builder alert = new AlertDialog.Builder(StockCreateNewStock.this);

					alert.setTitle("Stock Manager");
					alert.setMessage( "Barcode Wrong!");

					// Set an EditText view to get user input 

					alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					  // Do something with value!
					  }
					});

					alert.show();
				}

			}
		});

		btFinished.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				InventoryLineItem i = new InventoryLineItem(-1, items,
						DateManager.getCurrentDate(), SaleController
								.getInstance().getCashier());
				
				

				InventoryLineItem getInven = inventoryController
						.addinventoryLineItemToInventory(
								getApplicationContext(), i);
				

				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(
						StockCreateNewStock.this);
				builder.setTitle("Stock Manaer");
				builder.setMessage("Already added to stock");
				builder.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								// Toast.makeText(getApplicationContext(),
								// "Ok is clicked", Toast.LENGTH_LONG).show();
								finish();
							}
						});

				builder.show(); // To show the AlertDialog

			}

		});

		btScanWithBarcode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				try {
					Intent intent = new Intent(
							"com.google.zxing.client.android.SCAN");
					intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
					startActivityForResult(intent, 0);
				} catch (Exception e) {
					Toast.makeText(getBaseContext(),
							"Please Install Barcode Scanner",
							Toast.LENGTH_SHORT).show();
				}

			}
		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == this.RESULT_OK) {

				String contents = intent.getStringExtra("SCAN_RESULT");
				String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

				txtBarcode.setText(contents);
			}
		}
		super.onActivityResult(requestCode, resultCode, intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.stock_create_new_stock, menu);
		return true;
	}

}
