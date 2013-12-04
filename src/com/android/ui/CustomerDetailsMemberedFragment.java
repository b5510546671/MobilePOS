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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerDetailsMemberedFragment extends Fragment {
	private EditText txtMemberId;
	private Button btMemberOK;
	private Button btSendEmail;
	private SaleController saleController;
	private Customer customer;
	private TextView txtMemberedDetails;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.activity_cashier_continue4_customer_details_member,
				container, false);

		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		super.onViewCreated(view, savedInstanceState);
		
		saleController = SaleController.getInstance();
		
		
		txtMemberId = (EditText) getView().findViewById(R.id.txtMemberID);
		btMemberOK = (Button) getView().findViewById(R.id.btMemberOK);
		txtMemberedDetails = (TextView)getView().findViewById(R.id.txtCustomerDetails);
		
		btMemberOK.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				
				try {
					
					int id = Integer.parseInt(txtMemberId.getText().toString());
					
					customer = saleController.getCustomerByID(getActivity().getApplicationContext(), id);
					
					if(customer == null)  throw new Exception();
					else {
						if(customer.getName().equals("none")) throw new Exception();
						saleController.setCustomer(customer);
					}
					Log.i("log",customer.toString());
					txtMemberedDetails.setText("Membered Details"
							+ "\nID : " + customer.getID()
							+ "\nName : "+ customer.getName()
							+ "\nE-mail : " + customer.getEmail()
							+ "\nRegister Date : " + customer.getRegisterDate().getDate()+"/" + customer.getRegisterDate().getMonth()+"/"+ customer.getRegisterDate().getYear());
					
					
				} catch (Exception e) {
					Toast.makeText(getActivity(),"Invalid ID", Toast.LENGTH_SHORT).show();
				}
				
				
			}
		});
		btSendEmail = (Button) getView().findViewById(R.id.btMemberSendEmail);
		btSendEmail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if(customer != null){
					
					
					Sale sale =saleController.getSale(getActivity(), DateManager.getCurrentDate());
					
					Sale receive  = saleController.addSaleToSaleLadger(getActivity().getApplicationContext(),sale);
					
					AlertDialog alertDialog1 = new AlertDialog.Builder(
							getActivity()).create();

					alertDialog1.setTitle("POS Mobile");

					alertDialog1.setMessage("E-mail Sent");

					alertDialog1.setButton("OK",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									//saleController.setCustomer(customer);
									Sale sale = saleController.getSale(getActivity().getApplicationContext(),DateManager.getCurrentDate());
									Intent newActivity = new Intent(Intent.ACTION_SEND);
									String[] email=new String[]{"deknaew_bws@hotmail.com"};
									String subject="Receipt from POS mobile";
									String message="Thnk You For Shopping";
									newActivity.putExtra(Intent.EXTRA_EMAIL,email);         
									newActivity.putExtra(Intent.EXTRA_SUBJECT, subject);
									newActivity.putExtra(Intent.EXTRA_TEXT, message);
									newActivity.setType("plain/text");
									startActivity(Intent.createChooser(newActivity, "Email Sending Option :"));
									
									
								}
							});

					alertDialog1.show();
				}
				else{
					Toast.makeText(getActivity(), "Please fil the Customer ID",Toast.LENGTH_SHORT);
				}
			}
		});

	}
}
