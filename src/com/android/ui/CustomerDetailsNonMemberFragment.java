package com.android.ui;

import java.util.Date;

import com.controller.SaleController;
import com.core.Customer;
import com.core.Sale;
import com.utils.DateManager;
import com.android.softspectproject.R;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerDetailsNonMemberFragment extends Fragment {

	private EditText txtNonMemberName;
	private EditText txtNonMemberEmail;
	private Button btNonMemberOK;
	private SaleController saleController;

	/**
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.activity_cashier_continue5_customer_nonmember,
				container, false);

		return rootView;
	}
	
	/**
	 * @see android.support.v4.app.Fragment#onViewCreated(android.view.View, android.os.Bundle)
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		super.onViewCreated(view, savedInstanceState);
		saleController  = SaleController.getInstance();
		Toast.makeText(getActivity(), saleController.getItemsList().toString(), 1).show();
		
		
		
		txtNonMemberEmail = (EditText) getView().findViewById(
				R.id.txtNonMemberEmail);
		txtNonMemberName = (EditText) getView().findViewById(
				R.id.txtNonMemberName);
		btNonMemberOK = (Button) getView().findViewById(R.id.btNonMemberOK);

		btNonMemberOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				
				AlertDialog alertDialog1 = new AlertDialog.Builder(getActivity()).create();

				Customer c = new Customer(-1, "none",DateManager.getCurrentDate(),txtNonMemberEmail.getText().toString());
				saleController.setCustomer(c);	
				Sale sale = saleController.getSale(getActivity(),DateManager.getCurrentDate());
				
				if(sale == null ) Toast.makeText(getActivity(), "Invalid data", Toast.LENGTH_SHORT).show();
				else{
					Sale savedSale = saleController.addSaleToSaleLadger(getActivity(), sale);
					alertDialog1.setTitle("POS Mobile");
					
					
					alertDialog1.setMessage(savedSale.getItems().toString()+ "\nE-mail Sent to >>" + c.getEmail());
					

					alertDialog1.setButton("OK",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									
									Intent newActivity = new Intent(Intent.ACTION_SEND);
									String email="deknaew_bws@hotmail.com";
									String subject="Receipt from POS mobile";
									String message="Thnk You For Shopping";
									newActivity.putExtra(Intent.EXTRA_EMAIL,email);         
									newActivity.putExtra(Intent.EXTRA_SUBJECT, subject);
									newActivity.putExtra(Intent.EXTRA_TEXT, message);
									newActivity.setType("plain/text");
									startActivity(Intent.createChooser(newActivity, "Email Sending Option :"));
									getActivity().finish();
								}
							});

					alertDialog1.show();
				}
				
				
				
			}
		});

	}

}
