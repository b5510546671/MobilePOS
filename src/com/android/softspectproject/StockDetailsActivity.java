package com.android.softspectproject;

import java.util.ArrayList;
import java.util.List;

import com.android.ui.StockDetailsArrayAdapter;
import com.core.InventoryLineItem;
import com.core.Item;
import com.utils.DateManager;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
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
		if(inventoryLineItem.getCashier()==null)Toast.makeText(getApplicationContext(), "Cashier null", 1).show();
		
		String s = 	"ID : " + inventoryLineItem.getID()
					+"\nDate : " + DateManager.getDateString(inventoryLineItem.getDate())
					+"\nCashier : " + inventoryLineItem.getCashier().getName();
		txtDetails.setText(s);
		
		items.clear();
		for(com.core.Item i : inventoryLineItem.getItems())
		{
			items.add(i);
		}
		adapter = new StockDetailsArrayAdapter(this, items);
		listViewItem.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stock_details, menu);
		return true;
	}

}
