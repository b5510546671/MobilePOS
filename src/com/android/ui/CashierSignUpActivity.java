package com.android.ui;

import com.android.softspectproject.R;
import com.android.softspectproject.R.layout;
import com.android.softspectproject.R.menu;
import com.controller.CashierBookController;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CashierSignUpActivity extends Activity {
	
	private EditText txtUsername;
	private EditText txtPassword;
	private Button btOK;

	private String username = "";
	private String password = "";
	
	private CashierBookController cashierBookController;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cashier_sign_up);
		cashierBookController = CashierBookController.getInstance();
		
		
		
		txtUsername = (EditText)findViewById(R.id.txtSignUpUsername);
		txtPassword = (EditText)findViewById(R.id.txtSignUpPassword);
		btOK = (Button)findViewById(R.id.btSignUpOK);
		
		btOK.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				username = txtUsername.getText().toString();
				password = txtPassword.getText().toString();
				
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
