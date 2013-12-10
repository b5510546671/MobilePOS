package com.android.ui;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
/**
 * CashierPagerAdapter is the FragmentPagerAdapter of the PagerAdapter.
 * @author Sikarin	Larnamwong	5510546174
 *
 */
public class CashierPagerAdapter extends FragmentStatePagerAdapter {
	/**
	 * fragment list is the ArrayList of fragment of the PagerAdapter.
	 */
	private List<Fragment> fragmentList = new ArrayList<Fragment>();
	/**
	 * startPaymentFragment is the start payment Fragment.
	 */
	private Fragment startPaymentFragment = new CashierPaymentStartFragment();
	/**
	 * finishedPaymentFragment is the finished payment Fragment.
	 */
	private Fragment finishedPaymentFragment = new CashierPaymentFinishedFragment();
	/**
	 * memberDetailsFragment is the member Fragment use for make sale.
	 */
	private Fragment memberDetailsFragment = new CustomerDetailsMemberedFragment();
	/**
	 * nonMemberDetailsFragment is the non member Fragment use for make sale.
	 */
	private Fragment nonMemberDetailsFragment = new CustomerDetailsNonMemberFragment();
	/**
	 * fragmentName is the ArrayList contains name of each Fragment.
	 */
	private List<String> fragmentName = new ArrayList<String>();
	
	

	/**
	 * Constructor of the class
	 * @param fm is the FragmentManager of the Fragment.
	 */
	public CashierPagerAdapter(FragmentManager fm) {
		super(fm);
		fragmentList.add(startPaymentFragment);
		fragmentList.add(finishedPaymentFragment);
		fragmentList.add(memberDetailsFragment);
		fragmentList.add(nonMemberDetailsFragment);
		
		fragmentName.add("Payment");
		fragmentName.add("Finished");
		fragmentName.add("Member details");
		fragmentName.add("Non member details");
		
	}

	/**
	 * @see android.support.v4.app.FragmentStatePagerAdapter#getItem(int)
	 */
	@Override
	public Fragment getItem(int i) {

		return fragmentList.get(i);
	}

	/**
	 * @see android.support.v4.view.PagerAdapter#getCount()
	 */
	@Override
	public int getCount() {
		return fragmentList.size();
	}

	/**
	 * @see android.support.v4.view.PagerAdapter#getPageTitle(int)
	 */
	@Override
	public CharSequence getPageTitle(int position) {

		return fragmentName.get(position);
	}
}
