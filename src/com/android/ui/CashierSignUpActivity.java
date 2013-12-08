package com.android.ui;

import com.android.softspectproject.R;
import com.android.softspectproject.R.layout;
import com.android.softspectproject.R.menu;
import com.controller.CashierBookController;
import com.core.Cashier;
import com.core.Item;

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

public class CashierSignUpActivity extends Activity {
	
	private EditText txtUsername;
	private EditText txtName;
	private EditText txtPassword;
	private Button btOK;

	private String username = "";
	private String password = "";
	private String name="";
	
	private CashierBookController cashierBookController;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cashier_sign_up);
		cashierBookController = CashierBookController.getInstance();
		
		
		
		txtUsername = (EditText)findViewById(R.id.txtSignUpUsername);
		txtPassword = (EditText)findViewById(R.id.txtSignUpPassword);
		txtName = (EditText)findViewById(R.id.txtSignUpName);
		btOK = (Button)findViewById(R.id.btSignUpOK);
		
		btOK.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				username = txtUsername.getText().toString();
				password = txtPassword.getText().toString();
				name = txtName.getText().toString();
				final Cashier cashier = new Cashier(-1, name, username, password);
				
				AlertDialog.Builder builder = new AlertDialog.Builder(CashierSignUpActivity.this);
				builder.setTitle("Sign up Confirmation");
				builder.setMessage("Are You sure want to Sign Up?");
				
				builder.setNegativeButton("OK",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(
									DialogInterface arg0,
									int arg1) {
								Cashier c = cashierBookController.addCashier(getApplicationContext(), cashier);
								Toast.makeText(getApplicationContext(), "Name : " + c.getName(), 1).show();
								finish();
							}
						});
				builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
						
					}
				});

				builder.show();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cashier_sign_up, menu);
		return true;
	}

}
