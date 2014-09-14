package com.ordrit.fragment;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ordrit.activity.DashboardActivity;
import com.ordrit.model.Address;
import com.ordrit.model.City;
import com.ordrit.model.State;
import com.ordrit.model.User;
import com.ordrit.util.OrdritConstants;
import com.ordrit.util.SharedPreferencesUtil;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

public abstract class BaseFragment extends Fragment{
	
	public DashboardActivity dashboardActivity;
	

	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		dashboardActivity=(DashboardActivity) activity;
		
		
	}
	
	abstract void setupUiComponent(); 

}
