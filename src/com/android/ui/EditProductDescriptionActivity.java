package com.android.ui;

import com.android.softspectproject.R;
import com.controller.InventoryController;
import com.controller.SaleController;
import com.core.ItemDescription;

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

/**
 * EditProductDescriptionActivity is the editting ProductDescription.
 * 
 * @author Sikarin Larnamwong 5510546174
 * 
 */
public class EditProductDescriptionActivity extends Activity {
	/**
	 * saleController is the instance of the SaleController.
	 */
	private SaleController saleController;
	/**
	 * inventoryController is the instance of the InventoryController.
	 */
	private InventoryController inventoryController;
	/**
	 * txtName is the edit name EdtText.
	 */
	private EditText txtName;
	/**
	 * txtBarcode is the edit barcode EdtText.
	 */
	private EditText txtBarcode;
	/**
	 * txtPrice is the edit price EdtText.
	 */
	private EditText txtPrice;
	/**
	 * txtDescription is the edit description EdtText.
	 */
	private EditText txtDescription;
	/**
	 * txtCost is the edit cost EdtText.
	 */
	private EditText txtCost;
	/**
	 * btOK is the OK Button.
	 */
	private Button btOK;

	private TextView txtOldDescription;

	/**
	 * oldItemDescription is the last ItemDescription.
	 */
	private ItemDescription oldItemDescription;

	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_product_description);

		oldItemDescription = (ItemDescription) getIntent()
				.getSerializableExtra("oldItemDescription");

		saleController = SaleController.getInstance();
		inventoryController = InventoryController.getInstance();

		txtName = (EditText) findViewById(R.id.txtEditName);
		txtBarcode = (EditText) findViewById(R.id.txtEditBarcode);
		txtPrice = (EditText) findViewById(R.id.txtEditPrice);
		txtDescription = (EditText) findViewById(R.id.txtEditDescription);
		txtOldDescription = (TextView) findViewById(R.id.txtEditDetails);
		txtCost = (EditText) findViewById(R.id.txtEditCost);
		btOK = (Button) findViewById(R.id.btEditOK);

		txtName.setText(oldItemDescription.getName() + "");
		txtBarcode.setText(oldItemDescription.getBarcode() + "");
		txtPrice.setText(oldItemDescription.getPrice() + "");
		txtOldDescription.setText(oldItemDescription.getItemDescription() + "");
		txtBarcode.setEnabled(false);

		String s = "Name : " + oldItemDescription.getName() + "\nBarcode : "
				+ oldItemDescription.getBarcode() + "\nCost : "
				+ oldItemDescription.getCost() + "\nPrice : "
				+ oldItemDescription.getPrice() + "\nDecsription : "
				+ oldItemDescription.getItemDescription();
		txtOldDescription.setText(s);

		btOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String name = txtName.getText().toString();
				String barcode = txtBarcode.getText().toString();
				String price = txtPrice.getText().toString();
				String description = txtDescription.getText().toString();
				String cost = txtCost.getText().toString();

				try {

					if (name == "" || barcode == "" || price == ""
							|| cost == "")
						throw new Exception();
					ItemDescription i = new ItemDescription(-1, name,description, Float.parseFloat(cost), Float.parseFloat(price), barcode);
					
					inventoryController.editProductDescriptionByID(oldItemDescription.getId(), i, getApplicationContext());
					final AlertDialog alertDialog1 = new AlertDialog.Builder(
							EditProductDescriptionActivity.this).create();

					alertDialog1.setTitle("Product Details");

					alertDialog1.setMessage("Finished");

					alertDialog1.setButton("OK",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									finish();
								}
							});
					alertDialog1.show();

				} catch (Exception e) {

					AlertDialog.Builder alert = new AlertDialog.Builder(
							EditProductDescriptionActivity.this);

					alert.setTitle("Product Description");
					alert.setMessage("Please fill all data correctly!");

					alert.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
								}
							});

					alert.show();

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
		getMenuInflater().inflate(R.menu.edit_product_description, menu);
		return true;
	}

}
