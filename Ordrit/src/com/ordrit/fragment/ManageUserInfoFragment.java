package com.ordrit.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ordrit.R;
import com.ordrit.activity.HomeActivity;
import com.ordrit.activity.UILApplication;
import com.ordrit.database.OrdrItDataBase;
import com.ordrit.database.OrdrItdataBaseHelper;
import com.ordrit.util.FragmentConstant;
import com.ordrit.util.OrdritConstants;
import com.ordrit.util.SharedPreferencesUtil;

public class ManageUserInfoFragment extends BaseFragment {

	private View manageUserInfoFragment;
	private Button manageAccountMenu,btnManageAccountLogout;
	private LinearLayout containerLinkManageAccount,containerLinkManageAddress;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		manageUserInfoFragment = inflater.inflate(
				R.layout.fragment_manage_users_info, container, false);
		setupUiComponent();
		return manageUserInfoFragment;
	}

	@Override
	void setupUiComponent() {

		manageAccountMenu = (Button) manageUserInfoFragment
				.findViewById(R.id.manageAccountMenu);
		manageAccountMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dashboardActivity.clickMenu();
			}
		});
		
		btnManageAccountLogout = (Button) manageUserInfoFragment
				.findViewById(R.id.btnManageAccountLogout);
		btnManageAccountLogout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			
				SharedPreferencesUtil.clearAllSharedPreferencesList(dashboardActivity);
				OrdrItdataBaseHelper ordrItdataBaseHelper =new OrdrItdataBaseHelper(dashboardActivity);
				ordrItdataBaseHelper.open();
				ordrItdataBaseHelper.deleteTable(OrdrItDataBase.TABLE_STORE);
				ordrItdataBaseHelper.close();
				UILApplication uilApplication =(UILApplication) dashboardActivity.getApplication();
				uilApplication.setSelectedItemList(null);
				Intent intent=new Intent(dashboardActivity, HomeActivity.class);
				intent.putExtra(OrdritConstants.GO_LOGIN, true);
				startActivity(intent);
				dashboardActivity.finish();
			}
		});
		containerLinkManageAccount =(LinearLayout) manageUserInfoFragment
				.findViewById(R.id.containerLinkManageAccount);
		containerLinkManageAccount.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				UpdateAccountFragment updateAccountFragment = new UpdateAccountFragment();
				dashboardActivity.commitFragment(updateAccountFragment,FragmentConstant.UPDATE_ACCOUNT_FRAGMENT);
			}
		});
		containerLinkManageAddress =(LinearLayout) manageUserInfoFragment
				.findViewById(R.id.containerLinkManageAddress);
		containerLinkManageAddress.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AddUpdateAddressFragment addUpdateAddressFragment = new AddUpdateAddressFragment();
				dashboardActivity.commitFragment(addUpdateAddressFragment,FragmentConstant.ADD_UPDATE_ADDRESS_FRAGMENT);
			}
		});
		dashboardActivity.checkCartItems(manageUserInfoFragment);
	}

}
