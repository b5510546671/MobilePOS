package com.android.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.android.softspectproject.R;
import com.controller.SaleController;
import com.core.Customer;
import com.core.Item;
import com.core.Payment;
import com.core.Sale;
import com.utils.DateManager;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * HistoryDetailsActivity is the sale history details Activity.
 * 
 * @author Sikarin Larnamwong 5510546174
 * 
 */
public class HistoryDetailsActivity extends Activity {
	/**
	 * sale is the sale's Sale.
	 */
	private Sale sale;
	/**
	 * customer is the sale's Customer.
	 */
	private Customer customer;
	/**
	 * payment is the sale's Payment
	 */
	private Payment payment;
	/**
	 * items is the sale's Item list
	 */
	private List<Item> items = new ArrayList<Item>();
	/**
	 * date is the sale's Date.
	 */
	private Date date;
	/**
	 * txtDate is the date EditText.
	 */
	private EditText txtDate;
	/**
	 * txtName is the name EditText.
	 */
	private EditText txtName;
	/**
	 * txtEmail is the E-mail EditText.
	 */
	private EditText txtEmail;
	/**
	 * txtPrice is the price EditText.
	 */
	private EditText txtPrice;
	/**
	 * txtCash is the input EditText.
	 */
	private EditText txtCash;
	/**
	 * btOk is the OK Button.
	 */
	private Button btOK;
	/**
	 * productListView is the ListView of Item
	 */
	private ListView productListView;
	/**
	 * adapter is the adapter of ListView.
	 */
	private SearchCustomArrayAdapter adapter;

	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history_deails);

		sale = (Sale) getIntent().getSerializableExtra("sale");

		customer = sale.getCustomer();
		payment = sale.getPayment();

		items.clear();
		for (Item i : sale.getItems()) {
			items.add(i);
		}
		date = sale.getDate();

		txtDate = (EditText) findViewById(R.id.txtHistoryDetailsDate);
		txtCash = (EditText) findViewById(R.id.txtHistoryDetailsCash);
		txtPrice = (EditText) findViewById(R.id.txtHistoryDetailsPrice);
		txtName = (EditText) findViewById(R.id.txthistoryDetailsName);
		txtEmail = (EditText) findViewById(R.id.txtHistoryDetailsEmail);

		btOK = (Button) findViewById(R.id.btHistoryDetailsOk);
		productListView = (ListView) findViewById(R.id.listViewHistoryDetailsProduct);

		String dateString = DateManager.getDateString(date);
		txtDate.setText(dateString);
		txtDate.setEnabled(false);
		txtCash.setText(payment.getInput() + "");
		int price = 0;
		for (Item i : items) {
			price += i.getItemDescription().getPrice();
		}

		txtPrice.setText(price + "");
		txtPrice.setEnabled(false);
		txtName.setText(customer.getName());
		txtEmail.setText(customer.getEmail());
		btOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				AlertDialog.Builder builder = new AlertDialog.Builder(
						HistoryDetailsActivity.this);
				builder.setTitle("Edit Sale Confirmation");
				builder.setMessage("Are You sure want to edit sale?");
				builder.setNegativeButton("OK",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								SaleController saleController = SaleController
										.getInstance();

								Payment paymentNew = new Payment(-1, Float
										.parseFloat(txtPrice.getText()
												.toString()), Float
										.parseFloat(txtCash.getText()
												.toString()));
								Customer customerNew = new Customer(-1, txtName
										.getText().toString(), date, txtEmail
										.getText().toString());

								Sale saleNew = new Sale(-1, items,
										saleController.getCurrentCashier(),
										customerNew, paymentNew, date);
								saleController.removeSaleFromSaleLadger(
										getApplicationContext(), sale);
								saleController.addSaleToSaleLadger(
										getApplicationContext(), saleNew);
								finishActivity(RESULT_OK);
								finish();
							}
						});
				builder.setPositiveButton("Cancel",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								finishActivity(RESULT_OK);
								finish();
							}
						});

				builder.show();
			}
		});

		adapter = new SearchCustomArrayAdapter(this, items);

		productListView.setAdapter(adapter);

		productListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {

				Item item = items.get(arg2);

				AlertDialog.Builder builder = new AlertDialog.Builder(
						HistoryDetailsActivity.this);
				builder.setTitle("Product details");
				String s = "ID : " + item.getID() + "\nName : "
						+ item.getItemDescription().getName() + "\nBarcode : "
						+ item.getItemDescription().getBarcode() + "\nPrice : "
						+ item.getItemDescription().getPrice();
				builder.setMessage(s);
				builder.setNegativeButton("OK",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {

							}
						});
				builder.setPositiveButton("Delete",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								AlertDialog.Builder builder = new AlertDialog.Builder(
										HistoryDetailsActivity.this);
								builder.setTitle("Delete Confirmation");
								builder.setMessage("Are You sure want to delete?");
								builder.setNegativeButton("OK",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface arg0,
													int arg1) {
												items.remove(arg2);
												adapter.notifyDataSetChanged();
												int price = 0;
												for (Item i : items) {
													price += i
															.getItemDescription()
															.getPrice();
												}

												txtPrice.setText(price + "");
											}
										});
								builder.setPositiveButton("Cancel",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface arg0,
													int arg1) {

											}
										});

								builder.show();
							}
						});

				builder.show();

			}

		});

	}

	/**
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.history_deails, menu);
		return true;
	}

}
