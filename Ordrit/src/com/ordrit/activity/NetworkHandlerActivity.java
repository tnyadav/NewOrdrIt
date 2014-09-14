package com.ordrit.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ordrit.R;
import com.ordrit.util.ServerConnection;

public class NetworkHandlerActivity extends Activity {

	private Button buttonNetworkRetry,buttonNetworkExit;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_network);
		ActionBar actionBar =getActionBar();
		if (actionBar!=null) {
			getActionBar().hide();
		}
		initVar();

	}

	private void initVar() {
		buttonNetworkRetry = (Button) findViewById(R.id.buttonNetworkRetry);
		buttonNetworkRetry.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (ServerConnection.isNetworkAvailable(NetworkHandlerActivity.this)) {
					startActivity( new Intent(NetworkHandlerActivity.this, HomeActivity.class));
				    NetworkHandlerActivity.this.finish();
				} else {
					Toast.makeText(NetworkHandlerActivity.this, "No network connection.", Toast.LENGTH_SHORT).show();
					
				}
			}
		});
		buttonNetworkExit = (Button) findViewById(R.id.buttonNetworkExit);
		buttonNetworkExit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}

}
