
package com.android.ui;


import com.android.softspectproject.R;
import com.controller.SaleController;
import com.core.Cashier;



import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * MainActivity is the main page that contain DrawerLayout.
 * @author Sikarin	Larnamwong 5510546174
 *
 */
public class MainActivity extends Activity {
	/**
	 * mDrawerLayout is the current DrawerLayout
	 */
    private DrawerLayout mDrawerLayout;
    /**
     * mDrawerList is the ListView of DrawerLayout.
     */
    private ListView mDrawerList;
    /**
     * mDrawerToggle is ActionBarDrawerToggle of this Activity.
     */
    private ActionBarDrawerToggle mDrawerToggle;
    /**
     * mDrawerTitle is the title of the Drawer Item.
     */
    private CharSequence mDrawerTitle;
    /**
     * mTitle is the title of the Drawer.
     */
    private CharSequence mTitle;
    /**
     * title is the title of each Tab
     */
    private String[] title;
    /**
     * tabs is the ActionTap of thisActivity.
     */
    ActionBar.Tab[] tabs;
    /**
     * historyFragment is the tab Fragment
     */
    HistoryFragment historyFragment = new HistoryFragment();
    /**
     * cashierFragment is the tab Fragment
     */
    CashierFragment cashierFragment = new CashierFragment();
    /**
     * searchFragment is the tab Fragment
     */
	SearchFragment searchFragment = new SearchFragment();
	/**
     * stockFragment is the tab Fragment
     */
	ManagerFragment stockFragment = new ManagerFragment();
	/**
	 * actionBar is the ActionBar of this Activity.
	 */
	ActionBar actionBar;
	/**
	 * listViews is the Cashier ListView.
	 */
	private static ListView listViews;
	/**
	 * saleController is the instance of the SaleController.
	 */
	private SaleController saleController;
	/**
	 * cashier is the currentCashier.
	 */
	private Cashier cashier;
	/**
	 * txtHeader is the header of the Drawer.
	 */
	private TextView txtHeader;
	
    /**
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saleController = SaleController.getInstance();
        cashier = (Cashier) getIntent().getSerializableExtra("cashier");
        
        if(cashier != null){
        	Toast.makeText(getApplicationContext(), "Cashier : "+cashier.getName(), 1).show();
        	saleController.setCurrentCashier(cashier);
        }
        
        tabs = new ActionBar.Tab[4];
        listViews = (ListView)findViewById(R.id.cashierListView);
        

        mTitle = mDrawerTitle = "Mobile POS";
         title = new String[]{"Profile","Sign out"};
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        View headerView = ((LayoutInflater)getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE)).inflate(R.layout.header, null, false);
        txtHeader = (TextView)(headerView.findViewById(R.id.txtHeaderUsername));
        txtHeader.setText("Cashier : " + saleController.getCurrentCashier().getName());
        mDrawerList.addHeaderView(headerView, "HEY", false);
        mDrawerList.setSelected(false);
        mDrawerList.setBackgroundColor(Color.WHITE);
        

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item, title));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  
                mDrawerLayout,         
                R.drawable.ic_drawer,  
                R.string.drawer_open,  
                R.string.drawer_close  
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); 
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); 
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        
        actionBar = getActionBar();
     	actionBar.setDisplayShowHomeEnabled(true);

     	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
     		tabs[0]= actionBar.newTab().setText("History");
     		tabs[1] = actionBar.newTab().setText("Cashier");
     		tabs[2] = actionBar.newTab().setText("Manager");
     		tabs[3] = actionBar.newTab().setText("Search");
     		

     		tabs[0].setTabListener(new TabListener(historyFragment));
     		tabs[1].setTabListener(new TabListener(cashierFragment));
     		tabs[2].setTabListener(new TabListener(stockFragment));
     		tabs[3].setTabListener(new TabListener(searchFragment));
				
     		for(int i=0;i<tabs.length;i++)
     		{
     			actionBar.addTab(tabs[i]);
     		}
     		
     		

        if (savedInstanceState == null) {
        	HistoryFragment fragment = new HistoryFragment();
             getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
           
        }
    }

    /**
     * @see android.app.Activity#onResume()
     */
    @Override
	protected void onResume() {
    	txtHeader.setText("Cashier : " + saleController.getCurrentCashier().getName());
		super.onResume();
	}

	/**
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /** 
     * @see android.app.Activity#onPrepareOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.btMemberOK).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

	/**
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		
		final Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
		switch (item.getItemId()) {
		case R.id.btMemberOK:

			AlertDialog.Builder alert = new AlertDialog.Builder(this);

			alert.setTitle("Google");
			alert.setMessage("Input search");

			// Set an EditText view to get user input
			final EditText input = new EditText(this);
			alert.setView(input);

			alert.setPositiveButton("Search",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							String value = input.getText().toString();

							intent.putExtra(SearchManager.QUERY, value);
							
							if (intent.resolveActivity(getPackageManager()) != null) {
								startActivity(intent);
							} else {
								Toast bread = Toast.makeText(getApplicationContext(),"Browser not avilable", Toast.LENGTH_LONG);
								bread.show();
							}
							
						}
					});

			alert.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
						}
					});

			alert.show();

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        	
        	if(position==1)
        	{
        		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setTitle("Cashier Profile");
				Cashier cashier = saleController.getCurrentCashier();
				String s =	"ID : " + cashier.getId()+
							"\nName : " + cashier.getName()+
							"\nUsername : "+cashier.getUsername();
				builder.setMessage(s);
				
				builder.setNegativeButton("Edit",new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0,int arg1) {
								Intent intent = new Intent(getApplicationContext(), CashierEditProfileActivity.class);
								startActivity(intent);
							}
						});
				builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});

				builder.show();
        	}
            if(position==2){
            	final Intent intent = new Intent(getApplicationContext(), LoginFallAnimationActivity.class);
            	AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Sign out confirmation");
                builder.setMessage("Are you sure want to sign out?");
                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
 
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    	
                    }
                });
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
 
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    	startActivity(intent);
                    }
                });
                builder.show();
            }
        	mDrawerLayout.closeDrawers();
        }
    }

    

    /**
     * @see android.app.Activity#setTitle(java.lang.CharSequence)
     */
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }


    /**
     * @see android.app.Activity#onPostCreate(android.os.Bundle)
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    /**
     * @see android.app.Activity#onConfigurationChanged(android.content.res.Configuration)
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    
}