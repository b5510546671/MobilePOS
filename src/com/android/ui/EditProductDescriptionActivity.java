package com.android.ui;

import com.android.softspectproject.R;
import com.android.softspectproject.R.layout;
import com.android.softspectproject.R.menu;
import com.controller.InventoryController;
import com.controller.SaleController;
import com.core.ItemDescription;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditProductDescriptionActivity extends Activity {
	
	private SaleController saleController;
	private InventoryController inventoryController;
	private EditText txtName;
	private EditText txtBarcode;
	private EditText txtPrice;
	private EditText txtDescription;
	private Button btOK;
	private TextView txtOldDescription;
	
	private ItemDescription oldItemDescription;
	private ItemDescription newItemDescription;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_product_description);
		
		oldItemDescription = (ItemDescription)getIntent().getSerializableExtra("oldItemDescription");
		
		
		saleController = SaleController.getInstance();
		inventoryController = InventoryController.getInstance();
		
		txtName = (EditText)findViewById(R.id.txtEditName);
		txtBarcode  = (EditText)findViewById(R.id.txtEditBarcode);
		txtPrice = (EditText)findViewById(R.id.txtEditPrice);
		txtDescription = (EditText)findViewById(R.id.txtEditDescription);
		txtOldDescription = (TextView)findViewById(R.id.txtEditDetails);
		btOK = (Button)findViewById(R.id.btEditOK);
		
		txtName.setText(oldItemDescription.getName()+"");
		txtBarcode.setText(oldItemDescription.getBarcode()+"");
		txtPrice.setText(oldItemDescription.getPrice() +"");
		txtOldDescription.setText(oldItemDescription.getItemDescription()+"");
		txtBarcode.setEnabled(false);
		
		
		String s = 	"Name : " + oldItemDescription.getName()+
					"\nBarcode : " + oldItemDescription.getBarcode()+
					"\nPrice : " + oldItemDescription.getPrice()+
					"\nDecsription : " + oldItemDescription.getItemDescription();
		txtOldDescription.setText(s);
		
		btOK.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String name = txtName.getText().toString();
				String barcode = txtBarcode.getText().toString();
				String price = txtPrice.getText().toString();
				String description = txtDescription.getText().toString();
				
				try {
					
					if(name=="" || barcode=="" || price=="" ) throw new Exception();
					ItemDescription i = new ItemDescription(-1, name, description, Float.parseFloat(price), Integer.parseInt(barcode));
					inventoryController.removeItemDescription(getApplicationContext(), oldItemDescription.getBarcode());
					inventoryController.createNewItemDescription(getApplicationContext(), i.getName(), i.getItemDescription(), i.getPrice(), i.getBarcode());
					final AlertDialog alertDialog1 = new AlertDialog.Builder(
		                    EditProductDescriptionActivity.this).create();
		 
		            alertDialog1.setTitle("Product Details");
		 
		            alertDialog1.setMessage("Finished");
		
		            alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {
		            	
		                public void onClick(DialogInterface dialog, int which) {
		                	finish();
		                }
		            });
		            alertDialog1.show();		            
		            
					
				} catch (Exception e) {
					 AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
			            
			            	
			            builder.setTitle("Product Description");
			            builder.setMessage("Please fill all data correctly!");
			            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			                @Override
			                public void onClick(DialogInterface arg0, int arg1) {
			                	Intent intent = new Intent(getApplicationContext(), StockViewAllActivity.class);
			                	startActivity(intent);
			                }
			            });
			            
			            
			            
			            builder.show(); //To show the AlertDialog

				}
				
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_product_description, menu);
		return true;
	}

}