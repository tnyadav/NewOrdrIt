package com.ordrit.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ordrit.R;
import com.ordrit.fragment.LoginFragment;
import com.ordrit.fragment.WelcomeFragment;
import com.ordrit.model.Address;
import com.ordrit.util.OrdritConstants;
import com.ordrit.util.OrdritJsonKeys;
import com.ordrit.util.ServerConnection;
import com.ordrit.util.SharedPreferencesUtil;

public class HomeActivity extends Activity {
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_main);
		if (!ServerConnection.isNetworkAvailable(this)) {
			startActivity( new Intent(HomeActivity.this, NetworkHandlerActivity.class));
			finish();
			return;
		}
		
		
		String token=SharedPreferencesUtil.getStringPreferences(
				this, OrdritJsonKeys.TAG_TOKEN);
		if (token!=null&&!token.isEmpty()) {
			Intent intent=new Intent(this, DashboardActivity.class);
			startActivity(intent);
			finish();
		}else {
			FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager
					.beginTransaction();
			Fragment fragment = new WelcomeFragment();
			
				boolean goToLogin=getIntent().getBooleanExtra(OrdritConstants.GO_LOGIN, false);
				if (goToLogin) {
					fragment=new LoginFragment();
				}
				
			
			fragmentTransaction.replace(R.id.application_container,
					fragment);
			fragmentTransaction.commit();	
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		View currentView = getCurrentFocus();

		boolean retVal = super.dispatchTouchEvent(event);
		if (currentView instanceof EditText) {
			View cView = getCurrentFocus();
			int scrCoordinates[] = new int[2];
			cView.getLocationOnScreen(scrCoordinates);
			float xPos = event.getRawX() + cView.getLeft() - scrCoordinates[0];
			float yPos = event.getRawY() + cView.getTop() - scrCoordinates[1];

			if (event.getAction() == MotionEvent.ACTION_UP
					&& (xPos < cView.getLeft() || xPos >= cView.getRight()
							|| yPos < cView.getTop() || yPos > cView
							.getBottom())) {
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(getWindow().getCurrentFocus()
						.getWindowToken(), 0);
			}
		}
		return retVal;
	}
}
