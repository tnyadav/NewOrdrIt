package com.ordrit.fragment;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ordrit.R;
import com.ordrit.model.User;
import com.ordrit.util.CommonUtils;
import com.ordrit.util.FragmentConstant;
import com.ordrit.util.OrditJsonParser;
import com.ordrit.util.OrdritConstants;
import com.ordrit.util.OrdritJsonKeys;
import com.ordrit.util.SharedPreferencesUtil;
import com.ordrit.util.ValidationUtils;
import com.ordrit.util.WebServiceProcessingTask;

public class UpdateAccountFragment extends BaseFragment {
 
	private final String tag = "UpdateAccountFragment";
	private View updateAccountFragment;
	private Button updateAccountBack,btnUpdateAccountUpdate;
	private EditText etUpdateAccountFirstName, etUpdateAccountLastName,
			etUpdateAccountMobileNumber, etUpdateAccountUserEmailId;
	/*private TextView txtUpdateAccountFirstNameError,
			txtUpdateAccountLastNameError, txtUpdateAccountMobileNumberError,
			txtUpdateAccountUserEmailIdError;*/
	private User user;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		updateAccountFragment = inflater.inflate(
				R.layout.fragment_update_account, container, false);
		
		return updateAccountFragment;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		 user=dashboardActivity.getUser();
		setupUiComponent();
	}

	@Override
	void setupUiComponent() {
			updateAccountBack = (Button) updateAccountFragment
				.findViewById(R.id.updateAccountBack);
		updateAccountBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dashboardActivity
						.popFragment(FragmentConstant.UPDATE_ACCOUNT_FRAGMENT);
			}
		});
		btnUpdateAccountUpdate= (Button) updateAccountFragment
				.findViewById(R.id.btnUpdateAccountUpdate);
		btnUpdateAccountUpdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				


				final String strUpdateAccountFirstName = etUpdateAccountFirstName.getText().toString();
				final String strUpdateAccountLastName =etUpdateAccountLastName.getText().toString();
				final String strUpdateAccountMobileNumber=etUpdateAccountMobileNumber.getText().toString();
				final String strUpdateAccountUserEmailId=etUpdateAccountUserEmailId.getText().toString();
				// validation if true
				if (!ValidationUtils.isEmpty(strUpdateAccountFirstName)) {
					showText("Please Enter First Name");
					return;
				}
				if (!ValidationUtils.isEmpty(strUpdateAccountLastName)) {
					showText("Please Enter Last Name");
					return;
				}
				if (!ValidationUtils.isEmpty(strUpdateAccountMobileNumber)) {
					showText("Please Enter Phone Number");
					return;
				}
				
				new WebServiceProcessingTask(dashboardActivity) {
					
					@Override
					public void preExecuteTask() {
					TAG=tag;
					
					}
					
					@Override
					public void postExecuteTask() {
						
						try {
							user=OrditJsonParser.getUserFromJSON(user,jSONString);
							dashboardActivity.setUser(user);
							setData();
							Toast.makeText(dashboardActivity, "Account Updated Successfully", Toast.LENGTH_LONG).show();
						} catch (JSONException e) {
							e.printStackTrace();
							Toast.makeText(dashboardActivity, "Account Updation failed, Please try again later", Toast.LENGTH_LONG).show();
							
						}
						
					}
					
					@Override
					public void backgroundTask() {
					 
						List<NameValuePair> list=new ArrayList<NameValuePair>();
						
						list.add(new BasicNameValuePair(OrdritJsonKeys.USER_FIRSTNAME, strUpdateAccountFirstName));
						list.add(new BasicNameValuePair(OrdritJsonKeys.USER_LASTNAME, strUpdateAccountLastName));
						list.add(new BasicNameValuePair(OrdritJsonKeys.USER_PHONE_NUMBER, strUpdateAccountMobileNumber));
		                list.add(new BasicNameValuePair(OrdritJsonKeys.TAG_PINCODE, strUpdateAccountUserEmailId));
						
						
						jSONString  = connection.patchHttpUrlConnection(CommonUtils.getParamListJSONString(list),OrdritConstants.SERVER_BASE_URL
								+ OrdritConstants.USERS+"/"+user.getId(),
						SharedPreferencesUtil.getStringPreferences(
								dashboardActivity, OrdritJsonKeys.TAG_TOKEN));
								
						
					}
				}.execute();
			
				
			}
		});
		etUpdateAccountFirstName = (EditText) updateAccountFragment
				.findViewById(R.id.etUpdateAccountFirstName);

		etUpdateAccountLastName = (EditText) updateAccountFragment
				.findViewById(R.id.etUpdateAccountLastName);

		etUpdateAccountMobileNumber = (EditText) updateAccountFragment
				.findViewById(R.id.etUpdateAccountMobileNumber);

		etUpdateAccountUserEmailId = (EditText) updateAccountFragment
				.findViewById(R.id.etUpdateAccountUserEmailId);
		
		// error
	/*	
		txtUpdateAccountFirstNameError=(TextView) updateAccountFragment
		.findViewById(R.id.txtUpdateAccountFirstNameError);
		
		txtUpdateAccountLastNameError=(TextView) updateAccountFragment
				.findViewById(R.id.txtUpdateAccountLastNameError);
		
		txtUpdateAccountMobileNumberError=(TextView) updateAccountFragment
				.findViewById(R.id.txtUpdateAccountMobileNumberError);
		txtUpdateAccountUserEmailIdError=(TextView) updateAccountFragment
				.findViewById(R.id.txtUpdateAccountUserEmailIdError);*/
		setData();
		dashboardActivity.checkCartItems(updateAccountFragment);
	}
	private void  setData() {
		if (user!=null) {
			etUpdateAccountFirstName.setText(user.getFirstName());
			etUpdateAccountLastName.setText(user.getLastName());
			etUpdateAccountMobileNumber.setText(user.getPhoneNumber());
			etUpdateAccountUserEmailId.setText(user.getEmailId());
			
		}
	}
	private void showText(String text) {
		Toast.makeText(dashboardActivity, text, Toast.LENGTH_SHORT).show();
	}
}
