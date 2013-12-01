package com.android.ui;



import java.util.ArrayList;
import java.util.List;

import com.android.softspectproject.R;
import com.controller.SaleController;
import com.core.Item;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SearchFragment extends Fragment{
	
	private SaleController saleController;
	private List<Item> items = new ArrayList<Item>();;
	private CashierCustomArrayAdapter adapter;
	private ListView searchListView;
	private EditText txtSearch;
	private List<Item> showItems = new ArrayList<Item>();

	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View rootView = inflater.inflate(R.layout.activity_search_fragment, container, false);
	        return rootView;
	    }

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		saleController = SaleController.getInstance();
		items.clear();
		
		for(Item i : saleController.getAllItem(getActivity().getApplicationContext())){			
			
				items.add(i);
				showItems.add(i);
			
			
	}
		searchListView = (ListView)getView().findViewById(R.id.listViewProductSearch);
		adapter = new CashierCustomArrayAdapter(getActivity(), showItems);
		searchListView.setAdapter(adapter);
		txtSearch = (EditText)getView().findViewById(R.id.txtSearchProduct);
		
		txtSearch.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//Toast.makeText(getActivity(), "Text : " + s, 1).show();
				if(s==""){
					showItems = items;
				}
				else{
					showItems.clear();
					for(Item i : items){
						if(i.getItemDescription().getName().contains(s)){
							showItems.add(i);
						}
					}
				}
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Toast.makeText(getActivity(), items.toString(), 1).show();
		
		
		
		super.onViewCreated(view, savedInstanceState);
	}

	
   
}