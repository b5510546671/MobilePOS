package com.database;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.R.string;
import android.content.Context;
import android.content.res.Resources.Theme;
import android.widget.Toast;

public class CashierBookDataManager {
	
	private static CashierBookDataManager fileDataManager;
	private FileOutputStream fileOutputStream;
	private FileInputStream fileInputStream;

	
	public static CashierBookDataManager getInstance(){
		if(fileDataManager==null) fileDataManager = new CashierBookDataManager();
		return fileDataManager;
	}  
	
	
	public void writefile(FileOutputStream fos,String content) throws FileNotFoundException,IOException 
	{

		this.fileOutputStream = fos;
		fileOutputStream.write(content.getBytes());
		fileOutputStream.close();

	}

	public String readfile(FileInputStream fis)throws FileNotFoundException, IOException {

		this.fileInputStream = fis;
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);

		String sLine = null;
		String output = "";
		while ((sLine = br.readLine()) != null) {
			output += sLine;
		}
		return output;

	}
}