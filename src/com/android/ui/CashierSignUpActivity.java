package com.android.ui;

import com.android.softspectproject.R;
import com.controller.CashierBookController;
import com.core.Cashier;

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
/**
 * CashierSignUpActivity is the Cashier sign up activity
 * @author Sikarin	Larnamwong  5510546174
 *
 */
public class CashierSignUpActivity extends Activity {
	/**
	 * txtUsername is the user name EditText to sign up.
	 */
	private EditText txtUsername;
	/**
	 * txtName is the name of user EditText to sign up.
	 */
	private EditText txtName;
	/**
	 * txtPassword is the password EditText to sign up.
	 */
	private EditText txtPassword;
	/**
	 * btOK is the OK Button to confirm signing up.
	 */
	private Button btOK;

	/**
	 * username is the username of the Cashier.
	 */
	private String username = "";
	/**
	 * password is the password of the Cashier.
	 */
	private String password = "";
	/**
	 * name is the name of the Cashier.
	 */
	private String name="";
	/**
	 * cashierBookController is the instance of the CashierBookController
	 */
	private CashierBookController cashierBookController;
	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
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
								Cashier c = cashierBookController.addCashierToCashierBook(getApplicationContext(), cashier);
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

	/**
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cashier_sign_up, menu);
		return true;
	}

}
