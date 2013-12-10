package com.android.ui;


import com.android.softspectproject.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * ManagerFragment is the manager in stock and customer part Fragment.
 * @author Sikarin	Larnamwong	5510546174.
 *
 */
public class ManagerFragment extends Fragment{
	/**
	 * btNewStock is the add new stock Button.
	 */
	private Button btNewStock;
	/**
	 * btViewAll is the view all ItemDescription Button.
	 */
	private Button btViewAll;
	/**
	 * btAddNewProduct is the Button to add new ItemDescription Button.
	 */
	private Button btAddNewProduct;
	/**
	 * btCreateNewMember is the Button to create new member.
	 */
	private Button btCreateNewMember;
	/**
	 * btViewAllStock is the view all inventoryLineItem Button.
	 */
	private Button btViewAllStock;

	 /**
	 * @see android.app.Fragment#onViewCreated(android.view.View, android.os.Bundle)
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		btNewStock = (Button)getView().findViewById(R.id.btSearch);
		btViewAll = (Button)getView().findViewById(R.id.btStockViewAll);
		btAddNewProduct = (Button)getView().findViewById(R.id.btStockAddNewProduct);
		btViewAllStock = (Button)getView().findViewById(R.id.btStockViewAllStock);
		btCreateNewMember = (Button)getView().findViewById(R.id.btRegisterNewMember);
		
		btViewAllStock.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity().getApplicationContext(), StockViewAllStockActivity.class);
				startActivity(intent);
				
			}
		});
		btCreateNewMember.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(getActivity().getApplicationContext(), CustomerRegisterActivity.class);
				startActivity(intent);
				
			}
		});
		
		
		btNewStock.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(getActivity().getApplicationContext(), StockCreateNewStock.class);
				startActivity(intent);
				
			}
		});
		
		btViewAll.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(getActivity().getApplicationContext(), StockViewAllItemDescriptionActivity.class);
				startActivity(intent);
				
			}
		});
		btAddNewProduct.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(getActivity().getApplicationContext(), StockAddNewProductActivity.class);
				startActivity(intent);
				
			}
		});
	}

	/**
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View rootView = inflater.inflate(R.layout.activity_stock_fragment, container, false);
	        return rootView;
	    }
    
}
