package com.android.ui;

import java.util.List;


import android.app.AlertDialog;
import android.app.SearchManager;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.controller.SaleController;
import com.core.Item;
import com.core.ItemDescription;
import com.core.Payment;
import com.example.android.navigationdrawerexample.R;

public class CashierPaymentStartFragment extends Fragment {
	private Button buttonContinue;
	private TextView txtWishList;
	private List<String> wishList;
	private String wishListText = "";
	private EditText txtPaymentInput;
	private TextView txtTotalPrice;
	private double inputMoney=0;
	private double totalPrice=0;
	
	
	private Payment payment;
	private SaleController saleController;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.activity_cashier_continue2_payment, container, false);

		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		super.onViewCreated(view, savedInstanceState);
		
		
		
		
		saleController  =SaleController.getInstance();
		Toast.makeText(getActivity(),saleController.getItemsList().toString(),Toast.LENGTH_SHORT).show();
		
		for(Item i : saleController.getItemsList()){
			wishListText += i.toString()+ "		" + i.getItemDescription().getPrice() + "\n" ;
		}

		buttonContinue = (Button) getView().findViewById(R.id.btContinue);
		txtWishList = (TextView) getView().findViewById(R.id.txtWishListShow);
		txtPaymentInput = (EditText) getView().findViewById(R.id.txtPaymentInput);
		txtTotalPrice = (TextView)getView().findViewById(R.id.txtTotalPrice);
		
		txtWishList.setText(wishListText);
		txtTotalPrice.setText("Total Price : " + saleController.getTotalPrice());

		buttonContinue.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final ViewPager viewPager = (ViewPager) getActivity().findViewById(
						R.id.pager);
				
				try {
					inputMoney = Double.parseDouble(txtPaymentInput.getText().toString());
					totalPrice = saleController.getTotalPrice();
					
					Log.i("Payment", "InputMoney : " + inputMoney);
					Log.i("Payment", "Total Price : " + totalPrice);
					
					if(inputMoney < totalPrice) Toast.makeText(getActivity(), "NotEnough Money!",Toast.LENGTH_SHORT).show();
					else {
						saleController.createPayment(totalPrice, inputMoney);
						
			            AlertDialog alertDialog1 = new AlertDialog.Builder(
			                    getActivity()).create();
			 
			            alertDialog1.setTitle("Payment Details");
			 
			            alertDialog1.setMessage("Total Price :" + saleController.getPayment().getPrice() + "\nInput Money : " + saleController.getPayment().getInput() + "\nChange : " + saleController.getPayment().getChange());
			
			            alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {
			 
			                public void onClick(DialogInterface dialog, int which) {
			                    viewPager.setCurrentItem((viewPager.getCurrentItem() + 1));
			                }
			            });
			 
			            alertDialog1.show();
						
						
					}
					
				} catch (Exception e) {
					Toast.makeText(getActivity(), "Invalid Money Input! "+"\n"+e.getMessage(), Toast.LENGTH_SHORT).show();
					
				}
				

			}
		});
	}

}
