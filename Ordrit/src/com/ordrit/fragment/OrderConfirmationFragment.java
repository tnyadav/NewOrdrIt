package com.ordrit.fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ordrit.R;


public class OrderConfirmationFragment extends BaseFragment {

	private View orderConfirmationFragment;
	private Button manageAccountMenu;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final String orderID = getArguments().getString("orderID");    
		orderConfirmationFragment = inflater.inflate(R.layout.fragment_order_confirmation,
				container, false);
		
		TextView msg= (TextView)orderConfirmationFragment.findViewById(R.id.orderResponseMsg);	
	    msg.setText(Html
			.fromHtml("You will receive a notification once the order is out for delivery \n\n"
					+ "<font color=\"red\"><b>Your Order Id is "
					+ orderID
					+ ".</b></font>  Note it for further reference\nKeep calm and carry on."));
	setupUiComponent();
	return orderConfirmationFragment;
	}

	@Override
	void setupUiComponent() {
		manageAccountMenu = (Button) orderConfirmationFragment
				.findViewById(R.id.manageAccountMenu);
		manageAccountMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dashboardActivity.clickMenu();
			}
		});
	}

	

}
