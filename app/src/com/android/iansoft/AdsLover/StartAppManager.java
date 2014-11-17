package com.android.iansoft.AdsLover;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.startapp.android.publish.Ad;
import com.startapp.android.publish.AdEventListener;
import com.startapp.android.publish.nativead.NativeAdDetails;
import com.startapp.android.publish.nativead.NativeAdPreferences;
import com.startapp.android.publish.nativead.NativeAdPreferences.NativeAdBitmapSize;
import com.startapp.android.publish.nativead.StartAppNativeAd;
import com.startapp.android.publish.StartAppAd;

public class StartAppManager {
	
	private static Context context;
	private static Activity mActivity;
	private static boolean loadNativeSuccess;
	private static StartAppAd startAppAd = null;
	private static TextView txtNativeApp = null;
	private static ImageView imgNativeApp = null;
	private static NativeAdDetails nativeAd = null;
	private static StartAppNativeAd startAppNativeAd = null;
	
	public StartAppManager(Activity activity) {
		mActivity = activity;
	}
	
	public static void onShowSplash(Bundle bundle) {
		startAppAd.showSplash(mActivity, bundle);
	}
	
	public static void onShowSlider() {
		startAppAd.showSlider(mActivity);
	}
	
	private static AdEventListener nativeAdListener = new AdEventListener() {
		@Override
		public void onReceiveAd(Ad ad) {
			// Get the native ad
			ArrayList<NativeAdDetails> nativeAdsList = startAppNativeAd.getNativeAds();
			if (nativeAdsList.size() > 0){
				nativeAd = nativeAdsList.get(0);
			}
			
			// Verify that an ad was retrieved
			if (nativeAd != null) {
				// When ad is received and displayed - we MUST send impression
				nativeAd.sendImpression(context);
				txtNativeApp.setText(nativeAd.getTitle());
				imgNativeApp.setImageBitmap(nativeAd.getImageBitmap());
			}
		}
		
		@Override
		public void onFailedToReceiveAd(Ad ad) {
		}
	};
	
	public static void onShowNativeAd(Activity activity) {
		txtNativeApp = new TextView(activity);
		imgNativeApp = new ImageView(activity);
		startAppNativeAd = new StartAppNativeAd(activity);
		startAppNativeAd.loadAd(new NativeAdPreferences(), nativeAdListener);
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