package com.android.ui;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.controller.SaleController;
import com.core.Customer;
import com.utils.DateManager;
import com.android.softspectproject.R;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
/**
 * CustomerRegisterActivity is the Customer register Activity
 * @author Sikarin	Larnamwong	5510546174
 *
 */
public class CustomerRegisterActivity extends Activity {
	/**
	 * btOK is the OK Button
	 */
	private Button btOK;
	/**
	 * txtName is the customer's name EditText.
	 */
	private EditText txtName;
	/**
	 * txtEmail is the customer's E-mail EditText.
	 */
	private EditText txtEmail;

	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customer_register);
		
		
		
		btOK = (Button)findViewById(R.id.btRegisterOK);
		txtName = (EditText)findViewById(R.id.txtSearchProduct);
		txtEmail = (EditText)findViewById(R.id.txtAddNewProducrDescriptionPrice);
		
		btOK.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
				Pattern pattern = Pattern.compile(EMAIL_PATTERN);
				Matcher matcher = pattern.matcher(txtEmail.getText().toString());
				
				if(!matcher.matches())
				{
					AlertDialog.Builder builder = new AlertDialog.Builder(CustomerRegisterActivity.this);
	                builder.setTitle("E-mail is invlid");
	                builder.setMessage("Your E-mail is incorrect!");
	                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
						}
					});
	                builder.show();
	                
				}
				else{
					AlertDialog.Builder builder = new AlertDialog.Builder(CustomerRegisterActivity.this);
	                builder.setTitle("Member Register");
	                
	                final String name = txtName.getText().toString();
	                final String email = txtEmail.getText().toString();
	                builder.setMessage("Name : " + name + "\nE-mail : " + email);
	                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	 
	                    @Override
	                    public void onClick(DialogInterface arg0, int arg1) {
	                    	final Customer c = SaleController.getInstance().addCustomerToCustomerBook(getApplicationContext(), new Customer(-1,name, DateManager.getCurrentDate(), email));
	                    	AlertDialog.Builder builder2 = new AlertDialog.Builder(CustomerRegisterActivity.this);
	                        builder2.setTitle(c.getName()+"'s Member Details");
	                        builder2.setMessage("ID: " +c.getID() +"\nName : " + c.getName() + "\nE-mail : " + c.getEmail() + "\nRegister date : "+ c.getRegisterDate().getDate()+ "/"+ c.getRegisterDate().getMonth() + "/"+ c.getRegisterDate().getYear());
	                        builder2.setPositiveButton("OK",new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									
									Intent newActivity = new Intent(Intent.ACTION_SEND);
									String email=c.getEmail();
									String subject="Register finished";
									newActivity.putExtra(Intent.EXTRA_EMAIL,email);         
									newActivity.putExtra(Intent.EXTRA_SUBJECT, subject);
									newActivity.putExtra(Intent.EXTRA_TEXT, c.getName()+"'s Member Details"+"\nID" +c.getID() +"\nName : " + c.getName() + "\nE-mail : " + c.getEmail() + "\nRegister date : "+ c.getRegisterDate().getDate()+ "/"+ c.getRegisterDate().getMonth() + "/"+ (c.getRegisterDate().getYear()+1900) );
									newActivity.setType("plain/text");
									startActivity(Intent.createChooser(newActivity, "Email Sending Option :"));
									finish();
									
								}
							});
	                    	
	                        builder2.show(); 
	                    	
	                    }
	                });
	                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	 
	                    @Override
	                    public void onClick(DialogInterface arg0, int arg1) {
	                    	
	                    }
	                });
	               
	                builder.show(); 
				}
			}
		});
		
	}

	/**
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.customer_register, menu);
		return true;
	}

}
