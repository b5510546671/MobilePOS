package com.android.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.controller.SaleController;
import com.core.Item;
import com.android.softspectproject.R;

/**
 * CashierPaymentStartFragment is the start Fragment of make sale process.
 * 
 * @author Sikarin Larnamwong 5519546174
 * 
 */
public class CashierPaymentStartFragment extends Fragment {
	/**
	 * buttonContinue is the Button to continue to the next step.
	 */
	private Button buttonContinue;
	/**
	 * txtWishList is the TextView to show wishlist.
	 */
	private TextView txtWishList;
	/**
	 * wishListText is the wishlist text to show on the txtWishList.
	 */
	private String wishListText = "";
	/**
	 * txtPaymentInput the the input cash EditText.
	 */
	private EditText txtPaymentInput;
	/**
	 * txtTotalPrice is the TextView to show the total price.
	 */
	private TextView txtTotalPrice;
	/**
	 * inputMoney is the money input from the customer.
	 */
	private double inputMoney = 0;
	/**
	 * totalPrice is the total price of the sale.
	 */
	private double totalPrice = 0;

	/**
	 * saleController is the instance of the SaleController
	 */
	private SaleController saleController;

	/**
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 *      android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.activity_cashier_continue2_payment, container, false);

		return rootView;
	}

	/**
	 * @see android.support.v4.app.Fragment#onViewCreated(android.view.View,
	 *      android.os.Bundle)
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		super.onViewCreated(view, savedInstanceState);

		saleController = SaleController.getInstance();
		Toast.makeText(getActivity(), saleController.getCurrentItemsList().toString(),
				Toast.LENGTH_SHORT).show();

		for (Item i : saleController.getCurrentItemsList()) {
			wishListText += i.toString() + "		"
					+ i.getItemDescription().getPrice() + "\n";
		}

		buttonContinue = (Button) getView().findViewById(R.id.btContinue);
		txtWishList = (TextView) getView().findViewById(R.id.txtWishListShow);
		txtPaymentInput = (EditText) getView().findViewById(
				R.id.txtPaymentInput);
		txtTotalPrice = (TextView) getView().findViewById(R.id.txtTotalPrice);

		txtWishList.setText(wishListText);
		txtTotalPrice
				.setText("Total Price : " + saleController.getTotalPrice());

		buttonContinue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final ViewPager viewPager = (ViewPager) getActivity()
						.findViewById(R.id.pager);

				try {
					inputMoney = Double.parseDouble(txtPaymentInput.getText()
							.toString());
					totalPrice = saleController.getTotalPrice();

					Log.i("Payment", "InputMoney : " + inputMoney);
					Log.i("Payment", "Total Price : " + totalPrice);

					if (inputMoney < totalPrice) {
						final AlertDialog alertDialog1 = new AlertDialog.Builder(
								getActivity()).create();

						alertDialog1.setTitle("Cashier Manager");

						alertDialog1.setMessage("Not Enough Money!");

						alertDialog1.setButton("OK",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {

									}
								});
						alertDialog1.show();
					} else {
						saleController.createPayment(totalPrice, inputMoney);

						AlertDialog alertDialog1 = new AlertDialog.Builder(
								getActivity()).create();

						alertDialog1.setTitle("Payment Details");

						alertDialog1.setMessage("Total Price :"
								+ saleController.getCurrentPayment().getPrice()
								+ "\nInput Money : "
								+ saleController.getCurrentPayment().getInput()
								+ "\nChange : "
								+ saleController.getCurrentPayment().getChange());

						alertDialog1.setButton("OK",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										viewPager.setCurrentItem((viewPager
												.getCurrentItem() + 1));
									}
								});

						alertDialog1.show();

					}

				} catch (Exception e) {
					final AlertDialog alertDialog1 = new AlertDialog.Builder(
							getActivity()).create();

					alertDialog1.setTitle("Cashier Manager");

					alertDialog1.setMessage("Payment Input Wrong!");

					alertDialog1.setButton("OK",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {

								}
							});
					alertDialog1.show();
				}

			}
		});
	}

}
