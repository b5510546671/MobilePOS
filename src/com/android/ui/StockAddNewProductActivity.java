package com.android.ui;

import com.controller.InventoryController;
import com.core.ItemDescription;
import com.example.android.navigationdrawerexample.R;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StockAddNewProductActivity extends Activity {
	
	private EditText txtName;
	private EditText txtPrice;
	private EditText txtBarcode;
	private EditText txtProductDescription;
	
	private Button btOK;
	
	private InventoryController inventoryController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_add_new_product);
        
        inventoryController = InventoryController.getInstance();
        
        txtName = (EditText)findViewById(R.id.txtRegisterName);
        txtPrice = (EditText)findViewById(R.id.txtRegisterPrice);
        txtBarcode = (EditText)findViewById(R.id.txtRegisterBarcode);
        txtProductDescription = (EditText)findViewById(R.id.txtRegisterProductionDescription);
        btOK = (Button)findViewById(R.id.btRegisterOK);
        
        btOK.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					String name = txtName.getText().toString();
					float price = Float.parseFloat(txtPrice.getText().toString());
					int barcode = Integer.parseInt( txtBarcode.getText().toString());
					String productDescription = txtProductDescription.getText().toString();
					
					Log.i("Add new Item Activity ",inventoryController.toString());
					ItemDescription itemDescription = inventoryController.createNewItemDescription(getApplicationContext(), name, productDescription, price, barcode);
					ItemDescription itemGet = inventoryController.getItemDescriptionByBarcode(getApplicationContext(), barcode);
					
					Toast.makeText(getApplicationContext(),"Barcode : "+itemGet.getBarcode()+ "\nID : " + itemGet.getId()+ "\nName : "+ itemGet.getName() +"\nPrice"+ itemGet.getPrice() + "\nDescription :"+itemGet.getItemDescription()  , Toast.LENGTH_SHORT).show();
					
					
					
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "Wrong Data", Toast.LENGTH_SHORT).show();
				}
				
				
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.stock_add_new_product, menu);
        return true;
    }
    
}
