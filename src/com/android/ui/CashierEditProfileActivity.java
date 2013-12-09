package com.android.ui;

import com.android.softspectproject.R;
import com.android.softspectproject.R.id;
import com.android.softspectproject.R.layout;
import com.android.softspectproject.R.menu;
import com.controller.SaleController;
import com.core.Cashier;

import android.os.Bundle;
import android.app.Activity;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cashier_edit_profile);
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
				
				Cashier newCashier = new Cashier(cashier.getId(), name, username, pass);
				
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
