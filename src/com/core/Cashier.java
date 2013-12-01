package com.core;

import java.io.Serializable;


import android.content.Context;

public class Cashier implements Serializable{
    
    private int id;
    private String name;
    private String username;
    private String password;
    
    public Cashier(int id,String name,String userName,String passWord) {
    	this.id  =id;
    	this.name = name;
    	this.username = userName;
	}
    
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String user) {
		this.username = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
