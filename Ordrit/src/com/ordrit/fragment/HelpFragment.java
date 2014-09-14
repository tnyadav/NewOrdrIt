package com.ordrit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.ordrit.R;

public class HelpFragment extends BaseFragment {

	private View helpFragment;
	private Button btnBack;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		helpFragment = inflater.inflate(R.layout.fragment_help, container,
				false);
		
		return helpFragment;
	}
@Override
public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
	setupUiComponent();
}
	@Override
	void setupUiComponent() {
		btnBack = (Button) helpFragment.findViewById(R.id.menu);
		btnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dashboardActivity.clickMenu();
			}
		});
		WebView webView = (WebView) helpFragment.findViewById(R.id.text);
		webView.loadUrl("file:///android_asset/help.html");
		
		
	}


}