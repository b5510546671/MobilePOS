package com.android.ui;



import com.android.softspectproject.R;
import com.controller.SaleController;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class SearchFragment extends Fragment{
	
	private SaleController saleController;

	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View rootView = inflater.inflate(R.layout.activity_search_fragment, container, false);
	        return rootView;
	    }

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		saleController = SaleController.getInstance();
		
		super.onViewCreated(view, savedInstanceState);
	}
   
}