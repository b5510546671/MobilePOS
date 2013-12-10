package com.android.ui;

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
/**
 * 
 * CustomerDetailsMemberedFragment is the customer details Fragment
 * @author Sikarin	Larnamwong	5510546174
 *
 */
public class CustomerDetailsMemberedFragment extends Fragment {
	/**
	 * txtMemberId is the member ID EditText.
	 */
	private EditText txtMemberId;
	/**
	 * btMemberOK is the OK Button to continue.
	 */
	private Button btMemberOK;
	/**
	 * btSendEmail is the Button to send the E-mail to the Customer.
	 */
	private Button btSendEmail;
	/**
	 * saleController is the instance of the SaleController.
	 */
	private SaleController saleController;
	/**
	 * customer is the current customer.
	 */
	private Customer customer;
	/**
	 * txtMemberedDetails is the member details TextView.
	 */
	private TextView txtMemberedDetails;
	

	/**
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.activity_cashier_continue4_customer_details_member,
				container, false);

		return rootView;
	}

	/**
	 * @see android.support.v4.app.Fragment#onViewCreated(android.view.View, android.os.Bundle)
	 */
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
						saleController.setCurrentCustomer(customer);
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
					
					
					Sale sale =saleController.getCurrentSale(getActivity(), DateManager.getCurrentDate());
					
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
									Sale sale = saleController.getCurrentSale(getActivity().getApplicationContext(),DateManager.getCurrentDate());
									Intent newActivity = new Intent(Intent.ACTION_SEND);
									String[] email=new String[]{customer.getEmail()};
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
				else{
					Toast.makeText(getActivity(), "Please fil the Customer ID",Toast.LENGTH_SHORT);
				}
			}
		});

	}
}
