package com.android.iansoft.AdsLover;

import android.app.Activity;
import android.os.Bundle;

import com.facebook.widget.LoginButton;

public class LoginActivity extends Activity {
	
	private LoginButton loginButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.login);
		loginButton = (LoginButton)findViewById(R.id.loginButton);
	}
}