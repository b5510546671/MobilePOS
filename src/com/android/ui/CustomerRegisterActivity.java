package com.android.ui;

import java.util.Date;

import com.core.Sale;
import com.example.softspecproject.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CustomerRegisterActivity extends Activity {
	
	private Button btOK;
	private EditText txtName;
	private EditText txtEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customer_register);
		
		
		
		btOK = (Button)findViewById(R.id.btRegisterOK);
		txtName = (EditText)findViewById(R.id.txtRegisterName);
		txtEmail = (EditText)findViewById(R.id.txtRegisterPrice);
		
		btOK.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog alertDialog1 = new AlertDialog.Builder(getApplicationContext()).create();

				alertDialog1.setTitle("Register finished");
				
				String name = txtName.getText().toString();
				String eMail = txtEmail.getText().toString();

				alertDialog1.setMessage("Name : " + name +"\nE-Mail : " + eMail);

				alertDialog1.setButton("OK",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,int which) {
							//TODO create the customer from database to this	
								
							}
						});

				alertDialog1.show();
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
