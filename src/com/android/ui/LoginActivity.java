package com.android.ui;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.android.softspectproject.R;
import com.database.CashierBookDataManager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;
public class LoginActivity extends Activity {
	private Button btLogin;
	private String FILENAME="cashier.txt";
	private String content = "1,Sikarin Larnamwong,b5510546174,sikarin1993\n2,Krittayout Techasombooranakit,b5510545976,benzsk130";
	private CashierBookDataManager fileDataManager;
	private FileInputStream fileInputStream;
	private FileOutputStream fileOutputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btLogin = (Button)findViewById(R.id.btLogin);
        fileDataManager = CashierBookDataManager.getInstance();
        btLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
				
				
				
				
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }
    
}
