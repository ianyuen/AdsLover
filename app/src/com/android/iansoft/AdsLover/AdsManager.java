package com.android.iansoft.AdsLover;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.startapp.android.publish.Ad;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.splash.SplashConfig;
import com.startapp.android.publish.splash.SplashConfig.Theme;

public class AdsManager{
	
	private static Context context;
	private static boolean loadNativeSuccess;
	private static StartAppAd startAppAd = new StartAppAd(context);
	
	public static void onShowSplash(Activity activity, Bundle bundle) {
		StartAppAd.showSplash(activity, bundle);
	}
	
	public static void onShowSlider(Activity activity) {
		StartAppAd.showSlider(activity);
	}
	
	public static void onResume() {
		startAppAd.onResume();
	}

	public static void onPause() {
		startAppAd.onPause();
	}

	public static void onBackPressed() {
		startAppAd.onBackPressed();
	}
}