package com.android.ui;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle.Control;


import com.controller.SaleController;
import com.core.Item;
import com.core.ItemDescription;
import com.example.softspecproject.R;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CashierFragment extends Fragment {
	private ListView itemListView;
	private Button nextButton;
	private Button scanWithBarcodeButton;
	private EditText txtBarcode;
	private Button buttonOK;
	private SaleController saleController;
	private List<Item> items = new ArrayList<Item>();
	private List<Item> forceAddItems = new  ArrayList<Item>();
	
	private ArrayAdapter<String> adapter;
	private List<String> itemDisplay = new ArrayList<String>();
	
	private HashMap<Integer , Item> itemsMap = new HashMap<Integer, Item>();
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(
				R.layout.activity_cashier_fragment, container, false);
		

		return rootView;

	}
	

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		saleController = SaleController.getInstance();
		adapter = new ArrayAdapter<String>(
				getActivity(), R.layout.list_view, itemDisplay);
		itemListView = (ListView) getView().findViewById(R.id.cashierListView);
		
		
		nextButton = (Button) getView().findViewById(R.id.btStockOK);
		buttonOK = (Button) getView().findViewById(R.id.cashierButtonOK);
		nextButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(),CashierContinueActivity.class);
				
				
				
				while(itemDisplay.size()!=0) itemDisplay.remove(0);
				while(items.size()!=0) items.remove(0);
				//saleController.setItemList(items);
				adapter.notifyDataSetChanged();
				
				startActivity(intent);

			}
		});
		scanWithBarcodeButton = (Button) getView().findViewById(
				R.id.btScanWithBarcode);
		txtBarcode = (EditText) getView().findViewById(R.id.CashierTxtID);
		scanWithBarcodeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				try {
					Intent intent = new Intent("com.google.zxing.client.android.SCAN");
					intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
					startActivityForResult(intent, 0);
				} catch (Exception e) {
					Toast.makeText(getActivity().getBaseContext(),"Please Install Barcode Scanner",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		itemListView.setAdapter(adapter);
		adapter.notifyDataSetChanged();

		itemListView.setTextFilterEnabled(true);
		itemListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				itemDisplay.remove(arg2);
				items.remove(arg2);
				saleController.setItemList(items);
				adapter.notifyDataSetChanged();
			}

		});

		buttonOK.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				
				//txtBarcode.setText("");
				Toast.makeText(getActivity(), "Item amount : " + items.size(), Toast.LENGTH_SHORT).show();
				
				

				try {
					
					int barcode = Integer.parseInt(txtBarcode.getText().toString());
					Log.i("buttonOK", "amount in DB : "+saleController.getItemQuantity(getActivity().getApplicationContext(),barcode) + " || In list : " + saleController.getAmountInList(barcode));
				
					if(saleController.getItemQuantity(getActivity().getApplicationContext(),barcode) >saleController.getAmountInList(barcode) && saleController.getItemQuantity(getActivity().getApplicationContext(),barcode)>0)
					{
						Item item  = saleController.getItemfromInventory(getActivity().getApplicationContext(),barcode);
						Toast.makeText(getActivity().getApplicationContext(), item.getItemDescription().getName(),Toast.LENGTH_SHORT).show();
						
						if(item != null) {
							
							
							itemDisplay.add(item.toString());
							items.add(item);
							saleController.setItemList(items);
							adapter.notifyDataSetChanged();
							//Log.d("buttonOK", "amount in DB : "+saleController.getItemQuantity(getActivity().getApplicationContext(),barcode) + " || In list : " + saleController.getAmountInList(barcode));
						}
						else
						{
							Toast.makeText(getActivity(), "Product barcode wrong", Toast.LENGTH_SHORT).show();
						}
						
						
					
					}
					else
					{
						final AlertDialog alertDialog1 = new AlertDialog.Builder(
			                    getActivity()).create();
			 
			            alertDialog1.setTitle("Stock Manager");
			 
			            alertDialog1.setMessage("Not enough product!\nIf you want to force add press OK");
			
			            alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {
			            	
			                public void onClick(DialogInterface dialog, int which) {
			                	
			                	int barcode = Integer.parseInt(txtBarcode.getText().toString());
			                	
			                	ItemDescription forceAddItemDescription = saleController.getItemDescriptionByBarcode(getActivity(), barcode);
			                	
			                	
			                	Item forceAddedItem = saleController.forceAddItemToInventory(getActivity(), new Item(-10, forceAddItemDescription), new Date(0, 0, 0));
			                	
			                	forceAddItems.add(forceAddedItem);
			                	//items.add(forceAddedItem);
			                	itemDisplay.add(forceAddedItem.getItemDescription().getName());
			                	
			                	saleController.setItemList(items);
			                	adapter.notifyDataSetChanged();
			                }
			            });
			            
			          
			 
			            alertDialog1.show();
					}
					
					
				} 
			catch (Exception e) {
					Toast.makeText(getActivity(), "Please fill the blank with the barcode number", Toast.LENGTH_SHORT).show();
				}
				
				
				
				
			}
		});
	}


	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == getActivity().RESULT_OK) {

				String contents = intent.getStringExtra("SCAN_RESULT");
				String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

				txtBarcode.setText(contents);
			}
		}
		super.onActivityResult(requestCode, resultCode, intent);
	}

}