package com.ordrit.fragment;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.Duration;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.wallet.LineItem.Role;
import com.ordrit.R;
import com.ordrit.activity.HomeActivity;
import com.ordrit.util.CommonUtils;
import com.ordrit.util.FragmentConstant;
import com.ordrit.util.OrditJsonParser;
import com.ordrit.util.OrdritConstants;
import com.ordrit.util.OrdritJsonKeys;
import com.ordrit.util.SharedPreferencesUtil;
import com.ordrit.util.ValidationUtils;
import com.ordrit.util.WebServiceProcessingTask;
import com.ordrit.util.WebServicesRawDataUtil;

public class UserSignUp extends Fragment {

	private static final String tag="UserSignUp";
	private View signUpFragment;
	private HomeActivity mainActivity = null;
	private Button buttonSignUp;
	private EditText userNameEditText, passwordEditText,
			confirmPasswordEditText/*, emailIdEditText*/;
	private TextView editTextUserNameError,
			passwordError/*, editTextUserEmailIdError*/;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mainActivity = (HomeActivity) getActivity();
		mainActivity.getActionBar().hide();
		signUpFragment = inflater.inflate(R.layout.fragment_signup, container,
				false);
		
	/*	TextView headingText = (TextView) signUpFragment
				.findViewById(R.id.signupHeading);
		headingText.setText(Html.fromHtml(getString(R.string.grocery_msg)));*/
		// EditText
		userNameEditText=  (EditText) signUpFragment.findViewById(R.id.editTextUserName);
		passwordEditText=  (EditText) signUpFragment.findViewById(R.id.editTextPassword);
		confirmPasswordEditText=  (EditText) signUpFragment.findViewById(R.id.editTextConfirmPassword);
		//emailIdEditText=  (EditText) signUpFragment.findViewById(R.id.editTextUserEmailId);
		
		editTextUserNameError=  (TextView) signUpFragment.findViewById(R.id.textUserNameError);
		passwordError=  (TextView) signUpFragment.findViewById(R.id.passwordError);
		//editTextUserEmailIdError=  (TextView) signUpFragment.findViewById(R.id.textUserEmailIdError);
		
		
		//Sing Up Button
		buttonSignUp= (Button) signUpFragment.findViewById(R.id.buttonSignUp);
		buttonSignUp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				clearAllErrors();
				if(!fieldValidation()){
					
					
					new WebServiceProcessingTask(mainActivity) {
						
						@Override
						public void preExecuteTask() {
							TAG=tag;
							
							
						}
						
						@Override
						public void postExecuteTask() {
							JSONObject jsonObject;
							try {
								jsonObject = new JSONObject(jSONString);

							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								Toast.makeText(mainActivity,
										"Server not responds.. ",
										Toast.LENGTH_LONG).show();
								return;
							}
							// success
							String id = null;
							try {
								id = jsonObject
										.getString(OrdritJsonKeys.USER_ID);
								if (id != null && !id.isEmpty()) {
									redirectToLogin();
									Toast.makeText(mainActivity,
											R.string.sucess_regis,
											Toast.LENGTH_LONG).show();

								}
							} catch (JSONException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							// error
							try {
								if (jsonObject
										.getJSONArray(OrdritJsonKeys.USER_EMAIL) != null) {
									String serverMessage = jsonObject
											.getJSONArray(
													OrdritJsonKeys.USER_EMAIL)
											.getString(0);
									if (serverMessage != null
											&& !serverMessage.isEmpty()) {
										Toast.makeText(mainActivity,
												serverMessage,
												Toast.LENGTH_LONG).show();
										//redirectToLogin();
									}
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
						
						@Override
						public void backgroundTask() {
							
							jSONString  = connection.postHttpUrlConnection(
									WebServicesRawDataUtil
											.getSuperTokenJSONObjectString(),
									OrdritConstants.API_TOKEN_URL);	
							
							String token = null;
							
							try {
								token = OrditJsonParser
										.getTokenStringFromJSON(jSONString);
							} catch (Exception e) {
								e.printStackTrace();
							}
							if (token != null) {
								SharedPreferencesUtil
										.saveStringPreferences(
												mainActivity,
												OrdritJsonKeys.TAG_TOKEN,
												token);
						//create user here
								
								final String strUserName=userNameEditText.getText().toString();
							    final String strPassword=passwordEditText.getText().toString();
							  //  final String strEmail=emailIdEditText.getText().toString();
							    final String strRole="CST";
							    
							    List<NameValuePair> list=new ArrayList<NameValuePair>();
								
								list.add(new BasicNameValuePair(OrdritConstants.USERS_NAME, strUserName));
								list.add(new BasicNameValuePair(OrdritJsonKeys.USER_PASSWORD, strPassword));
								list.add(new BasicNameValuePair(OrdritJsonKeys.USER_EMAIL, strUserName));
				                list.add(new BasicNameValuePair(OrdritJsonKeys.USER_ROLE, strRole));
				                
				                jSONString  = connection.postHttpUrlConnection(CommonUtils.getParamListJSONString(list),OrdritConstants.SERVER_BASE_URL
										+ OrdritConstants.USERS,
								SharedPreferencesUtil.getStringPreferences(
										getActivity(), OrdritJsonKeys.TAG_TOKEN));	
				                
								
								
							} 
							
							
							
							
						}
					}.execute();
					
					
				}
			}
		});
		
		return signUpFragment;
	}
	
	private boolean fieldValidation(){
		boolean containsError = false;
		if(userNameEditText.getText().length()== 0){
			editTextUserNameError.setVisibility(View.VISIBLE);
			editTextUserNameError.setText(mainActivity.getResources().getString(R.string.empty_username_error));
			containsError = true;
		}
	else if(!ValidationUtils.isEmailValid(userNameEditText.getText().toString())){
		editTextUserNameError.setVisibility(View.VISIBLE);
		editTextUserNameError.setText(mainActivity.getResources().getString(R.string.invalid_email));
		containsError = true;
	}
		if(passwordEditText.getText().length()== 0){
			passwordError.setVisibility(View.VISIBLE);
			passwordError.setText(mainActivity.getResources().getString(R.string.empty_password_error));
			containsError = true;
		}else{
			if(!passwordEditText.getText().toString().equals(confirmPasswordEditText.getText().toString())){
				passwordError.setVisibility(View.VISIBLE);
				passwordError.setText(mainActivity.getResources().getString(R.string.unmatch_password_error));
				containsError = true;
			}
		}
		
		/*if(emailIdEditText.getText().length()== 0){
			editTextUserEmailIdError.setVisibility(View.VISIBLE);
			editTextUserEmailIdError.setText(mainActivity.getResources().getString(R.string.empty_email_error));
			containsError = true;
		}else if(!ValidationUtils.isEmailValid(emailIdEditText.getText().toString())){
			editTextUserEmailIdError.setVisibility(View.VISIBLE);
			editTextUserEmailIdError.setText(mainActivity.getResources().getString(R.string.invalid_email));
			containsError = true;
		}*/
		return containsError;
	}
	
	private void clearAllErrors(){
		editTextUserNameError.setVisibility(View.GONE);
		passwordError.setVisibility(View.GONE);
		//editTextUserEmailIdError.setVisibility(View.GONE);
		
	}
	private void redirectToLogin() {
		View vi= (LinearLayout) mainActivity.findViewById(R.id.application_container);
		Animation anim = AnimationUtils.loadAnimation(
				mainActivity, R.anim.bottom_to_top);
		vi.startAnimation(anim);
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		
		Fragment userloginFragment = new LoginFragment();
		fragmentTransaction.replace(R.id.application_container,
				userloginFragment);
		fragmentTransaction.commit();
	}

}
