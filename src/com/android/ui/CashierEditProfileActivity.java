package com.android.ui;

import com.android.softspectproject.R;
import com.android.softspectproject.R.id;
import com.android.softspectproject.R.layout;
import com.android.softspectproject.R.menu;
import com.controller.InventoryController;
import com.controller.SaleController;
import com.core.Cashier;
import com.core.InventoryLineItem;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CashierEditProfileActivity extends Activity {
	
	private EditText txtPassword;
	private EditText txtUsername;
	private EditText txtName;
	private Button btOK;
	private TextView txtDetails;
	private SaleController saleController;
	private InventoryController inventoryController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cashier_edit_profile);
		inventoryController = InventoryController.getInstance();
		saleController = SaleController.getInstance();
		final Cashier cashier = saleController.getCashier();
	
		txtName = (EditText)findViewById(R.id.txtEditProfileName);
		txtUsername = (EditText)findViewById(R.id.txtEditProfileUsername);
		txtPassword = (EditText)findViewById(R.id.txtEditProfilePassword);
		btOK = (Button)findViewById(R.id.btEditProfileOK);
		txtDetails = (TextView)findViewById(R.id.txtEditProfileDetails);
		txtDetails.setEnabled(false);
		String s = 	"ID : " +cashier.getId()+
					"\nName : " +cashier.getName()+
					"\nUsername : " + cashier.getUsername()+
					"\nPassword : " + cashier.getPassword();
		txtDetails.setText(s);
		
		btOK.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String name = txtName.getText().toString();
				String pass = txtPassword.getText().toString();
				String username = txtUsername.getText().toString();
				
				final Cashier newCashier = new Cashier(cashier.getId(), name, username, pass);
				AlertDialog.Builder alert = new AlertDialog.Builder(CashierEditProfileActivity.this);

				alert.setTitle("Edit Confirmation");
				alert.setMessage("Are you sure want to edit cashier?");

				alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					
					SaleController.getInstance().setCashier(inventoryController.editCashier(getApplicationContext(), newCashier));
				  }
				});

				alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				  public void onClick(DialogInterface dialog, int whichButton) {
				  }
				});

				alert.show();
				
			}
		});
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cashier_edit_profile, menu);
		return true;
	}

}
