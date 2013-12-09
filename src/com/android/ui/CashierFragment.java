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

public class CashierFragment extends Fragment {
	private ListView itemListView;
	private Button buttonNext;
	private Button scanWithBarcodeButton;
	private EditText txtBarcode;
	private Button buttonOK;
	private SaleController saleController;
	private List<Item> items = new ArrayList<Item>();

	private CashierCustomArrayAdapter adapter;
	private List<String> itemDisplay = new ArrayList<String>();

	private HashMap<Integer, Item> itemsMap = new HashMap<Integer, Item>();

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
				// Toast.makeText(getActivity(), items.toString(), 1).show();
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

		// itemListView.setTextFilterEnabled(true);
		itemListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				saleController.setItemList(adapter.getItems());
				// itemDisplay.remove(arg2);
				// items.remove(arg2);
				// saleController.setItemList(items);
				adapter.notifyDataSetChanged();

			}

		});

		buttonOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// txtBarcode.setText("");
				// Toast.makeText(getActivity(), "Item amount : " +
				// items.size(), Toast.LENGTH_SHORT).show();

				saleController.setItemList(adapter.getItems());

				try {

					String barcode = txtBarcode.getText().toString();
					Item item = saleController.getItemfromInventoryByIMEI(getActivity().getApplicationContext(), barcode);

					if (item != null) {

						items.add(item);
						adapter.notifyDataSetChanged();
						saleController.setItemList(adapter.getItems());

					}

					else {
						final AlertDialog alertDialog1 = new AlertDialog.Builder(
								getActivity()).create();

						alertDialog1.setTitle("Stock Manager");

						alertDialog1
								.setMessage("Not enough product!\nIf you want to force add press OK");

						alertDialog1.setButton("OK",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {

									}
								});

						alertDialog1.show();
					}

				} catch (Exception e) {
					// Toast.makeText(getActivity(),
					// "Please fill the blank with the barcode number",
					// Toast.LENGTH_SHORT).show();

					final AlertDialog alertDialog1 = new AlertDialog.Builder(
							getActivity()).create();

					alertDialog1.setTitle("Cashier Manager");

					alertDialog1
							.setMessage("Please fill the blank with the barcode number");

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