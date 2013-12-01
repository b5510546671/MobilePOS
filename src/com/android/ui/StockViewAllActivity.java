package com.android.ui;

import java.util.ArrayList;

import com.android.softspectproject.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class StockViewAllActivity extends Activity {
	private ListView listview;
	//TODO delete after test finished
	String[] values = new String[] { "Android List View", 
            "Adapter implementation",
            "Simple List View In Android",
            "Create List View Android", 
            "Android Example", 
            "List View Source Code", 
            "List View Array Adapter", 
            "Android Example List View" 
           };
	
	private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stock_view_all);
        
       listview= (ListView)findViewById(R.id.listViewStockAllItem2);
       arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.activity_view_all_listview_item,values);
       listview.setAdapter(arrayAdapter);
       listview.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long arg3) {
			String s = (String) listview.getItemAtPosition(position);
			

			
		}
	});
     
       
    }
    
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.stock_view_all, menu);
        return true;
    }
    
}
