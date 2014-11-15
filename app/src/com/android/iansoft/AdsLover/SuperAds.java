package com.android.iansoft.AdsLover;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.util.Log;

import com.startapp.android.publish.banner.Banner;

public class SuperAds extends Activity {
	CountDownTimer countDownTimer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.super_ads);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		countDownTimer = new CountDownTimer(5000, 1000) {
			public void onTick(long millisUntilFinished) {
				Log.v("ianyuen", "remain: " + millisUntilFinished / 1000);
			}
			public void onFinish() {
				onSuperAds();
			}
		}.start();
	}
	
	private void onSuperAds() {
		Intent intent = new Intent(this, SuperAds.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}