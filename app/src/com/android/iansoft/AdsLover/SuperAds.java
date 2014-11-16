package com.android.iansoft.AdsLover;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.iansoft.AdsLover.AdsManager;
import com.startapp.android.publish.Ad;
import com.startapp.android.publish.AdEventListener;
import com.startapp.android.publish.nativead.NativeAdDetails;
import com.startapp.android.publish.nativead.NativeAdPreferences;
import com.startapp.android.publish.nativead.NativeAdPreferences.NativeAdBitmapSize;
import com.startapp.android.publish.nativead.StartAppNativeAd;
import com.startapp.android.publish.StartAppAd;

public class SuperAds extends Activity {
	CountDownTimer countDownTimer;
	private boolean isOnSuperAds = true;
	
	private StartAppAd startAppAd = new StartAppAd(this);
	
	private TextView txtFreeApp = null;
	private ImageView imgFreeApp = null;
	private NativeAdDetails nativeAd = null;
	private StartAppNativeAd startAppNativeAd = new StartAppNativeAd(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.super_ads);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		AdsManager.onShowSplash(this, savedInstanceState);
		onShowNativeAd();
		AdsManager.onShowSlider(this);
	}
	
	private void onSuperAds() {
		Intent intent;
		if (isOnSuperAds)
			intent = new Intent(this, SuperAds.class);
		else
			intent = new Intent(this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}
	
	/** Native Ad Callback */
	private AdEventListener nativeAdListener = new AdEventListener() {
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
				nativeAd.sendImpression(SuperAds.this);
				if (imgFreeApp != null && txtFreeApp != null) {
					// Set button as enabled
					imgFreeApp.setEnabled(true);
					txtFreeApp.setEnabled(true);
					// Set ad's image
					imgFreeApp.setImageBitmap(nativeAd.getImageBitmap());
					// Set ad's title
					txtFreeApp.setText(nativeAd.getTitle());
				}
			}
			countDownTimer = new CountDownTimer(3000, 1000) {
				public void onTick(long millisUntilFinished) {}
				public void onFinish() {
					onSuperAds();
				}
			}.start();
		}
		
		@Override
		public void onFailedToReceiveAd(Ad ad) {
			
			// Error occurred while loading the native ad
			if (txtFreeApp != null) {
				txtFreeApp.setText("Error while loading Native Ad");
			}
		}
	};
	
	private void onShowNativeAd() {
		imgFreeApp = (ImageView)findViewById(R.id.imgFreeApp);
		txtFreeApp = (TextView)findViewById(R.id.txtFreeApp);
		if (txtFreeApp != null) {
			txtFreeApp.setText("Loading Native Ad...");
		}
		startAppNativeAd.loadAd(
			new NativeAdPreferences()
				.setAdsNumber(1)
				.setAutoBitmapDownload(true)
				.setImageSize(NativeAdBitmapSize.SIZE150X150),
			nativeAdListener);
	}
	
	public void freeAppClick(View view){
		if (nativeAd != null){
			nativeAd.sendClick(this);
		}
	}
	
	public void btnOffSuperAdsClick(View view) {
		isOnSuperAds = false;
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