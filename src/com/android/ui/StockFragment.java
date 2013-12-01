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

public class StockFragment extends Fragment{
	
	private Button btNewStock;
	private Button btViewAll;
	private Button btAddNewProduct;

	 @Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		btNewStock = (Button)getView().findViewById(R.id.btCreateNewStock);
		btViewAll = (Button)getView().findViewById(R.id.btStockViewAll);
		btAddNewProduct = (Button)getView().findViewById(R.id.btStockAddNewProduct);
		
		btNewStock.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity().getApplicationContext(), "Create New Stock", Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent(getActivity().getApplicationContext(), StockCreateNewStock.class);
				startActivity(intent);
				
			}
		});
		
		btViewAll.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity().getApplicationContext(), "View All Stock", Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent(getActivity().getApplicationContext(), StockViewAllActivity.class);
				startActivity(intent);
				
			}
		});
		btAddNewProduct.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity().getApplicationContext(), "Add New Product to Stock", Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent(getActivity().getApplicationContext(), StockAddNewProductActivity.class);
				startActivity(intent);
				
			}
		});
	}

	@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View rootView = inflater.inflate(R.layout.activity_stock_fragment, container, false);
	        return rootView;
	    }
    
}
