package com.android.iansoft.AdsLover;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.startapp.android.publish.Ad;
import com.startapp.android.publish.AdDisplayListener;
import com.startapp.android.publish.AdEventListener;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;
import com.startapp.android.publish.nativead.NativeAdDetails;
import com.startapp.android.publish.nativead.NativeAdPreferences;
import com.startapp.android.publish.nativead.NativeAdPreferences.NativeAdBitmapSize;
import com.startapp.android.publish.nativead.StartAppNativeAd;
import com.startapp.android.publish.splash.SplashConfig;
import com.startapp.android.publish.splash.SplashConfig.Theme;

public class MainActivity extends Activity {
	
	/** StartAppAd object declaration */
	private StartAppAd startAppAd = new StartAppAd(this);
	
	/** StartApp Native Ad declaration */
	private StartAppNativeAd startAppNativeAd = new StartAppNativeAd(this);
	private NativeAdDetails nativeAd = null;
	
	private ImageView imgFreeApp = null;
	private TextView txtFreeApp = null;
	
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
			if (nativeAd != null){
				
				// When ad is received and displayed - we MUST send impression
				nativeAd.sendImpression(MainActivity.this);
				
				if (imgFreeApp != null && txtFreeApp != null){
					
					// Set button as enabled
					imgFreeApp.setEnabled(true);
					txtFreeApp.setEnabled(true);
					
					// Set ad's image
					imgFreeApp.setImageBitmap(nativeAd.getImageBitmap());
					
					// Set ad's title
					txtFreeApp.setText(nativeAd.getTitle());
				}
			}
		}
		
		@Override
		public void onFailedToReceiveAd(Ad ad) {
			
			// Error occurred while loading the native ad
			if (txtFreeApp != null) {
				txtFreeApp.setText("Error while loading Native Ad");
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StartAppSDK.init(this, "103774315", "211118112", true);
		
		setContentView(R.layout.main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		onShowSplash(savedInstanceState);
		/** Add Slider **/
		StartAppAd.showSlider(this);
		onShowNativeAd();
	}
	
	/** Create Splash Ad **/
	private void onShowSplash(Bundle savedInstanceState) {
		StartAppAd.showSplash(this, savedInstanceState,
			new SplashConfig()
				.setTheme(Theme.SKY)
				.setLogo(R.drawable.logo360x360)
				.setAppName("StartApp Example")
		);
	}
	
	/** 
	* Load Native Ad with the following parameters:
	* 1. Only 1 Ad
	* 2. Download ad image automatically
	* 3. Image size of 150x150px
	*/
	private void onShowNativeAd() {
		/** Initialize Native Ad views **/
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

	/**
	 * Runs when the native ad is clicked (either the image or the title).
	 * @param view
	 */
	public void freeAppClick(View view){
		if (nativeAd != null){
			nativeAd.sendClick(this);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		startAppAd.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		startAppAd.onPause();
	}

	@Override
	public void onBackPressed() {
		startAppAd.onBackPressed();
		super.onBackPressed();
	}
}