package com.android.ui;

import com.android.softspectproject.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
/**
 * CashierPaymentFinishedFragment is the Payment finished Fragment.
 * @author Sikarin	Larnamwong	5510546174
 *
 */
public class CashierPaymentFinishedFragment extends Fragment {
	/**
	 * btMember is the Button to enter member details Fragment.
	 */
	private Button btMember;
	/**
	 * btNonMember is the Button to enter non member details Fragment.
	 */
	private Button btNonMember;
	/**
	 * viewPager is the ViewPager of the View.
	 */
	private ViewPager viewPager;

	/**
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.activity_cashier_continue3_finished_payment,
				container, false);

		return rootView;
	}

	/**
	 * @see android.support.v4.app.Fragment#onViewCreated(android.view.View, android.os.Bundle)
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		super.onViewCreated(view, savedInstanceState);
		viewPager = (ViewPager) getActivity().findViewById(R.id.pager);

		btMember = (Button) getView().findViewById(R.id.btMemberSendEmail);
		btNonMember = (Button) getView().findViewById(R.id.btNonMember);
		btMember.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				viewPager.setCurrentItem(2);
			}
		});
		btNonMember.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				viewPager.setCurrentItem(3);
			}
		});

	}
}