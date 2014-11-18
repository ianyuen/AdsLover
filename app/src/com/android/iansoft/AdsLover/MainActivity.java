package com.android.iansoft.AdsLover;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.util.Log;

import com.android.iansoft.AdsLover.StartAppManager;
import com.android.iansoft.AdsLover.LoginActivity;
import com.facebook.android.Facebook;
import com.facebook.AppEventsLogger;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;

public class MainActivity extends Activity {

	private Facebook facebook = null;
	private StartAppAd startAppAd = new StartAppAd(this);
	private StartAppManager mStartAppManager = new StartAppManager(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StartAppSDK.init(this, "103774315", "211118112", true);

		setContentView(R.layout.main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		facebook = new Facebook(getResources().getString(R.string.facebook_app_id));
		if (!facebook.isSessionValid()) {
			Intent intent = new Intent(this, LoginActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
		}
		
		mStartAppManager.onShowSlider();
		//mStartAppManager.onShowNativeAd(this);
		mStartAppManager.onShowSplash(savedInstanceState);
	}
	
	public void btnOnSuperAdsClick(View view) {
		Intent intent = new Intent(this, SuperAds.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}

	@Override
	public void onResume() {
		super.onResume();
		startAppAd.onResume();
		AppEventsLogger.activateApp(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		startAppAd.onPause();
		AppEventsLogger.activateApp(this);
	}

	@Override
	public void onBackPressed() {
		startAppAd.onBackPressed();
		super.onBackPressed();
	}
}