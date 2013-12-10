package com.android.ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.android.softspectproject.R;
import com.controller.SaleController;
import com.core.Sale;
import com.utils.DateManager;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
/**
 * HistoryFragment is the Sale historyFragment.
 * @author Sikarin	Larnamwong
 *
 */
public class HistoryFragment extends Fragment {
	/**
	 * btDateChange is the Date ChangingButton;
	 */
	private Button btDateChange;
	/**
	 * optionSearchSpinner is the drop down Spinner to search.
	 */
	private Spinner optionSearchSpinner;
	/**
	 * listViewSale is the Sale history ListView.
	 */
	private ListView listViewSale;
	/**
	 * selectedDay is the user search selected day.
	 */
	private int selectedDay = 0;
	/**
	 * selectedMonth is the user search selected month.
	 */
	private int selectedMonth = 0;
	/**
	 * selectedYear is the user search selected Year 
	 */
	private int selectedYear = 0;
	/**
	 * saleController is the instance of the SaleController.
	 */
	private SaleController saleController;
	/**
	 * allSales is the ArrayList of all Sale History.
	 */
	private List<Sale> allSales = new ArrayList<Sale>();
	/**
	 * sales is the ArrayList of the display Sale History.
	 */
	private List<Sale> sales = new ArrayList<Sale>();
	/**
	 * adapter is the HistoryCustomArrayAdapter of the ListView.
	 */
	private HistoryCustomArrayAdapter adapter;

	/**
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_history_fragment,
				container, false);
		return rootView;
	}

	/**
	 * @see android.app.Fragment#onViewCreated(android.view.View, android.os.Bundle)
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		saleController = SaleController.getInstance();
		final Calendar c = Calendar.getInstance();
		selectedYear = DateManager.getCurrentDate().getYear() + 1900;
		selectedMonth = DateManager.getCurrentDate().getMonth() + 1;
		selectedDay = DateManager.getCurrentDate().getDay() + 1;
		optionSearchSpinner = (Spinner) getView().findViewById(
				R.id.spinnerReport);
		btDateChange = (Button) getView().findViewById(R.id.btReportDate);
		btDateChange.setText("Selected Date : " + selectedDay + "/"
				+ selectedMonth + "/" + selectedYear);

		listViewSale = (ListView) getView().findViewById(R.id.listViewAllSale);

		final List<String> list = new ArrayList<String>();
		list.add("View All");
		list.add("Search by Date");
		list.add("Search by Month");
		list.add("Search by Year");

		adapter = new HistoryCustomArrayAdapter(getActivity(), sales);
		listViewSale.setAdapter(adapter);
		listViewSale.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {

				String s = "";
				s += "Sale ID : " + sales.get(position).getID();
				s += "\nProduct : " + ""
						+ sales.get(position).getItems().toString();
				s += "\nTotal Price : " + sales.get(position).getTotalPrice();
				s += "\nCash : " + sales.get(position).getPayment().getInput();
				s += "\nCustomer : "
						+ sales.get(position).getCustomer().getName();
				s += "\nDate : "+DateManager.getDateString(sales.get(position).getDate());

				AlertDialog.Builder builder = new AlertDialog.Builder(
						getActivity());
				builder.setTitle("Sale Manager");
				builder.setMessage(s);
				builder.setPositiveButton("Edit",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								Intent intent = new Intent(getActivity()
										.getApplicationContext(),
										HistoryDetailsActivity.class);
								intent.putExtra("sale", sales.get(position));
								startActivity(intent);

							}
						});
				builder.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {

							}
						});

				builder.show();

			}

		});
		sales.clear();
		for (Sale sale : saleController.getAllSaleFromSaleLadger(getActivity()
				.getApplicationContext())) {
			sales.add(sale);
		}

		adapter.notifyDataSetChanged();

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		optionSearchSpinner.setAdapter(dataAdapter);

		optionSearchSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parentView,
							View selectedItemView, int position, long id) {

//						 Toast.makeText(getActivity(),selectedDay +"/"+ selectedMonth+"/"+ selectedYear+"/n", 1).show();

						if (position == 0) {
							sales.clear();
							for (Sale sale : saleController
									.getAllSaleFromSaleLadger(getActivity())) {
								sales.add(sale);
							}
							adapter.notifyDataSetChanged();

						} else if (position == 1) {// By Date
							sales.clear();
							for (Sale sale : saleController
									.getAllSaleFromSaleLadger(getActivity())) {
								if (DateManager.getDateString(sale.getDate()).split(" ")[0].equals(selectedDay+"")
										&& sale.getDate().getMonth() + 1 == selectedMonth
										&& sale.getDate().getYear() + 1900 == selectedYear)
									sales.add(sale);
							}
							adapter.notifyDataSetChanged();

						} else if (position == 2) {
							sales.clear();
							for (Sale sale : saleController
									.getAllSaleFromSaleLadger(getActivity())) {
								if (sale.getDate().getMonth() + 1 == selectedMonth)
									sales.add(sale);
							}
							adapter.notifyDataSetChanged();
						} else if (position == 2) {// By Month
							sales.clear();
							for (Sale sale : saleController
									.getAllSaleFromSaleLadger(getActivity())) {
								if (sale.getDate().getYear() + 1900 == selectedYear)
									sales.add(sale);
							}
							adapter.notifyDataSetChanged();
						}

					}

					@Override
					public void onNothingSelected(AdapterView<?> parentView) {

					}

				});

		btDateChange.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final Calendar c = Calendar.getInstance();
				int mYear = c.get(Calendar.YEAR);
				int mMonth = c.get(Calendar.MONTH);
				int mDay = c.get(Calendar.DAY_OF_MONTH);

				OnDateSetListener mDateSetListener = new OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						selectedDay = dayOfMonth;
						selectedMonth = monthOfYear + 1;
						selectedYear = year;
						btDateChange.setText(selectedDay + "/" + selectedMonth
								+ "/" + selectedYear);

					}
				};
				DatePickerDialog dpdFromDate = new DatePickerDialog(
						getActivity(), mDateSetListener, mYear, mMonth, mDay);
				dpdFromDate.show();

				dpdFromDate.setButton(DialogInterface.BUTTON_NEGATIVE,
						"Cancel", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								if (which == DialogInterface.BUTTON_NEGATIVE) {

								}
							}
						});

			}
		});

		super.onViewCreated(view, savedInstanceState);
	}

	/**
	 * @see android.app.Fragment#onResume()
	 */
	@Override
	public void onResume() {
		sales.clear();
		for (Sale sale : saleController.getAllSaleFromSaleLadger(getActivity()
				.getApplicationContext())) {
			sales.add(sale);
		}

		adapter.notifyDataSetChanged();
		super.onResume();
	}

}
