package com.android.iansoft.AdsLover;

import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.facebook.*;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphPlace;
import com.facebook.model.GraphUser;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.*;

public class LoginActivity extends FragmentActivity {
	
	private LoginButton loginButton;
	private MainFragment mainFragment;
	private UiLifecycleHelper uiHelper;
	private static final String TAG = "MainFragment";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//uiHelper = new UiLifecycleHelper(getActivity(), callback);
		uiHelper.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			// Add the fragment on initial activity setup
			mainFragment = new MainFragment();
			getSupportFragmentManager()
			.beginTransaction()
			.add(android.R.id.content, mainFragment)
			.commit();
		} else {
			// Or set the fragment from restored state info
			mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(android.R.id.content);
		}
		
		setContentView(R.layout.login);
		loginButton = (LoginButton)findViewById(R.id.login_button);
	}

	@Override
	public void onResume() {
		super.onResume();
		uiHelper.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		uiHelper.onSaveInstanceState(outState);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}
	
	public class MainFragment extends Fragment {
	
		private Session.StatusCallback callback = new Session.StatusCallback() {
			@Override
			public void call(Session session, SessionState state, Exception exception) {
				onSessionStateChange(session, state, exception);
			}
		};
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.main, container, false);
			
			LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
			loginButton.setFragment(this);
			loginButton.setReadPermissions(Arrays.asList("user_likes", "user_status"));
			
			return view;
		}
		
		@Override
		public void onResume() {
			super.onResume();
			Session session = Session.getActiveSession();
			if (session != null && (session.isOpened() || session.isClosed())) {
				onSessionStateChange(session, session.getState(), null);
			}
			uiHelper.onResume();
		}
		
		private void onSessionStateChange(Session session, SessionState state, Exception exception) {
			if (state.isOpened()) {
				Log.i(TAG, "Logged in...");
			} else if (state.isClosed()) {
			Log.i(TAG, "Logged out...");
		}
}
	}
}