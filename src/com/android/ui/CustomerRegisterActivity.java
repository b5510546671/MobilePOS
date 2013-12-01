package com.android.ui;

import java.util.Date;

import com.controller.SaleController;
import com.core.Customer;
import com.core.Sale;
import com.utils.DateManager;
import com.android.softspectproject.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerRegisterActivity extends Activity {
	
	private Button btOK;
	private EditText txtName;
	private EditText txtEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customer_register);
		
		
		
		btOK = (Button)findViewById(R.id.btRegisterOK);
		txtName = (EditText)findViewById(R.id.txtSearchProduct);
		txtEmail = (EditText)findViewById(R.id.txtRegisterPrice);
		 final String name = txtName.getText().toString();
         final String email = txtEmail.getText().toString();
		
		btOK.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(CustomerRegisterActivity.this);
                builder.setTitle("Member Register");
                
               
                builder.setMessage("Name : " + name + "\nE-mail : " + email);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
 
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    	SaleController.getInstance().addCustomerToCustomerBook(getApplicationContext(), new Customer(-1,name, DateManager.getCurrentDate(), email));
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
 
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    	
                    }
                });
               
                builder.show(); 
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.customer_register, menu);
		return true;
	}

}
