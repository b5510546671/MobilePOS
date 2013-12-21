package com.android.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle.Control;

import com.controller.SaleController;
import com.core.Item;
import com.core.ItemDescription;
import com.android.softspectproject.R;

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

/**
 * 
 * CashierFragment is the Cashier Fragment of the ActionBar.
 * 
 * @author Sikarin Larnamwong 5510546174
 * 
 */
public class CashierFragment extends Fragment {
	/**
	 * itemListView is the ListView of Item.
	 */
	private ListView itemListView;
	/**
	 * buttonNext is the Button to go to the next step.
	 */
	private Button buttonNext;
	/**
	 * scanWithBarcodeButton is the Button to scan barcode.
	 */
	private Button scanWithBarcodeButton;
	/**
	 * txtBarcode is the barcode EditText.
	 */
	private EditText txtBarcode;
	/**
	 * buttonOK is the OK Button.
	 */
	private Button buttonOK;
	/**
	 * saleController is the instance of SaleController class
	 */
	private SaleController saleController;
	/**
	 * items is the ArrayList of Item.
	 */
	private List<Item> items = new ArrayList<Item>();
	/**
	 * adapter is the CashierCustomArrayAdapter of the ListView.
	 */
	private CashierCustomArrayAdapter adapter;
	/**
	 * itemDisplay is the ArrayList of String to display.
	 */
	private List<String> itemDisplay = new ArrayList<String>();

	/**
	 * onCreateView is the method called while view created.
	 * 
	 * @param inflater
	 *            is the Inflater of the view.
	 * @param container
	 *            is the ViewGroup.
	 * @param savedInstanceState
	 *            is the Bundle of the View.
	 * @return
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(
				R.layout.activity_cashier_fragment, container, false);

		return rootView;

	}

	/**
	 * onViewCreated is the method called after view is created.
	 * 
	 * @param view
	 *            is the View of the Fragment.
	 * @param savedInstanceState
	 *            is the Bundle.
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		saleController = SaleController.getInstance();
		items.clear();
		saleController.setCurrentItemList(items);
		adapter = new CashierCustomArrayAdapter(getActivity(), items);
		itemListView = (ListView) getView().findViewById(R.id.cashierListView);

		buttonNext = (Button) getView().findViewById(R.id.btStockOK);
		buttonOK = (Button) getView().findViewById(R.id.cashierButtonOK);
		buttonNext.setOnClickListener(new OnClickListener() {

			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(),
						CashierContinueActivity.class);

				items = new ArrayList<Item>();
				adapter.notifyDataSetChanged();

				startActivity(intent);

			}
		});
		scanWithBarcodeButton = (Button) getView().findViewById(
				R.id.btScanWithBarcode);
		txtBarcode = (EditText) getView().findViewById(R.id.CashierTxtID);
		txtBarcode.setText("");
		scanWithBarcodeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				try {
					Intent intent = new Intent(
							"com.google.zxing.client.android.SCAN");
					intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
					startActivityForResult(intent, 0);
				} catch (Exception e) {
					Toast.makeText(getActivity().getBaseContext(),
							"Please Install Barcode Scanner",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		itemListView.setAdapter(adapter);
		adapter.notifyDataSetChanged();

		itemListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				saleController.setCurrentItemList(adapter.getItems());
				adapter.notifyDataSetChanged();

			}

		});

		buttonOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				saleController.setCurrentItemList(adapter.getItems());

				try {

					String barcode = txtBarcode.getText().toString();
					Item item = saleController.getItemfromInventoryByIMEI(
							getActivity().getApplicationContext(), barcode);

					if (item != null) {

						items.add(item);
						adapter.notifyDataSetChanged();
						saleController.setCurrentItemList(adapter.getItems());

					}

					else {
						final AlertDialog alertDialog1 = new AlertDialog.Builder(
								getActivity()).create();

						alertDialog1.setTitle("Stock Manager");

						alertDialog1.setMessage("No Product found!");

						alertDialog1.setButton("OK",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										

									}
								});
						

						alertDialog1.show();
					}

				} catch (Exception e) {

					final AlertDialog alertDialog1 = new AlertDialog.Builder(
							getActivity()).create();

					alertDialog1.setTitle("Cashier Manager");

					alertDialog1.setMessage("Empty");

					alertDialog1.setButton("OK",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {

								}
							});
					alertDialog1.show();

				}
			}
		});
	}

	/**
	 * onActivityResult is the method to call after get the result.
	 * 
	 * @param requestCode
	 *            is the result request code.
	 * @param resultCode
	 *            is the result.
	 * @param intent
	 *            is intent that send the result.
	 */
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