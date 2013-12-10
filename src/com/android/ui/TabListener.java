package com.android.ui;



import com.android.softspectproject.R;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;

/**
 * TabListener is the TabListener of the MainAcitivity Tab.
 * @author thigirakaipon
 *
 */
public class TabListener implements ActionBar.TabListener {
	/**
	 * fragment is the current Fragment.
	 */
	Fragment fragment;

	/**
	 * TabListener is the Constructor method.
	 * @param fragment is the Tab Fragment.
	 */
	public TabListener(Fragment fragment) {
		this.fragment = fragment;
	}

	/**
	 * @see android.app.ActionBar.TabListener#onTabSelected(android.app.ActionBar.Tab, android.app.FragmentTransaction)
	 */
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		ft.replace(R.id.content_frame, fragment);
	}

	/**
	 * @see android.app.ActionBar.TabListener#onTabUnselected(android.app.ActionBar.Tab, android.app.FragmentTransaction)
	 */
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		ft.remove(fragment);
	}

	/**
	 * @see android.app.ActionBar.TabListener#onTabReselected(android.app.ActionBar.Tab, android.app.FragmentTransaction)
	 */
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
}
