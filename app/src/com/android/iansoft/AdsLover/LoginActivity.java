package com.android.iansoft.AdsLover;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.Signature;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.facebook.widget.LoginButton;

public class LoginActivity extends Activity {
	
	private LoginButton loginButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.login);
		loginButton = (LoginButton)findViewById(R.id.loginButton);
		
		//showHashKey(this);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.v("ianyuen", "");
		
	}
	
	public static void showHashKey(Context context) {    
		try {
			PackageInfo info = context.getPackageManager().getPackageInfo("com.android.iansoft.AdsLover", PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				Log.v("ianyuen", Base64.encodeToString(md.digest(), Base64.DEFAULT));
			}
		} catch (NameNotFoundException e) {	
		} catch (NoSuchAlgorithmException e) {
		}
	}
}