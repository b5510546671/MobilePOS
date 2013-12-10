package com.android.ui;

import android.os.Bundle;

import android.app.ActionBar;
import android.app.Fragment;

/**
 * ContentFragment is the Fragment of the ActionBar content.
 * 
 * @author Sikarin Larnamwong 5510546174
 * 
 */
public class ContentFragment extends Fragment {

	/**
	 * actionBar is the ActionBar of the Activity.
	 */
	private ActionBar actionBar;

	/**
	 * @see android.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		actionBar = getActivity().getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	}

}
