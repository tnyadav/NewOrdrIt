package com.ordrit.fragment;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ordrit.R;
import com.ordrit.activity.DashboardActivity;
import com.ordrit.activity.HomeActivity;
import com.ordrit.model.City;
import com.ordrit.model.State;
import com.ordrit.model.User;
import com.ordrit.util.CalibriTextView;
import com.ordrit.util.OrditJsonParser;
import com.ordrit.util.OrdritConstants;
import com.ordrit.util.OrdritJsonKeys;
import com.ordrit.util.SharedPreferencesUtil;
import com.ordrit.util.WebServiceProcessingTask;
import com.ordrit.util.WebServicesRawDataUtil;

public class LoginFragment extends Fragment {
    private final String tag= "LoginFragment";
	private View loginFragment;
	private HomeActivity mainActivity = null;
	private EditText editTextUserName, editTextPassword=null;
	private CalibriTextView editTextUserNameError, passwordError, SignUpText=null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mainActivity = (HomeActivity) getActivity();
		mainActivity.getActionBar().hide();
		loginFragment = inflater.inflate(R.layout.fragment_login, container,
				false);
		TextView forgotPasswordLink = (TextView) loginFragment
				.findViewById(R.id.forgotPasswordLink);
		
		editTextUserName=(EditText) loginFragment.findViewById(R.id.editTextUserName);
		editTextUserNameError= (CalibriTextView) loginFragment.findViewById(R.id.textUserNameError);
		passwordError= (CalibriTextView) loginFragment.findViewById(R.id.passwordError);
		editTextPassword=(EditText) loginFragment.findViewById(R.id.editTextPassword);
		SignUpText= (CalibriTextView) loginFragment.findViewById(R.id.signUpLink);
		SignUpText.setText(Html.fromHtml(getString(R.string.sign_up_link)));

		Button loginButton = (Button) loginFragment
				.findViewById(R.id.buttonLogin);
		
		SignUpText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				View vi= (LinearLayout) mainActivity.findViewById(R.id.application_container);
				Animation anim = AnimationUtils.loadAnimation(
						mainActivity, R.anim.bottom_to_top);
				vi.startAnimation(anim);
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				Fragment userSignUpFragment = new UserSignUp();
				
				fragmentTransaction.replace(R.id.application_container,
						userSignUpFragment);
				fragmentTransaction.addToBackStack("loginScreen");
				fragmentTransaction.commit();
				
			}
		});
		
		forgotPasswordLink.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				View vi= (LinearLayout) mainActivity.findViewById(R.id.application_container);
				Animation anim = AnimationUtils.loadAnimation(
						mainActivity, R.anim.bottom_to_top);
				vi.startAnimation(anim);
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				Fragment forgotPasswordFragment = new ForgotPasswordFragment();
				
				fragmentTransaction.replace(R.id.application_container,
						forgotPasswordFragment);
				fragmentTransaction.addToBackStack("loginScreen");
				fragmentTransaction.commit();
				
			}
		});
		
		loginButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				boolean errorFound= false;
				if(editTextUserName.getText().toString().length()==0){
					editTextUserNameError.setText(mainActivity.getResources().getString(R.string.username_field_empty));
					editTextUserNameError.setVisibility(View.VISIBLE);
					errorFound= true;
				}
				
				if(editTextPassword.getText().toString().length()==0){
					passwordError.setText(mainActivity.getResources().getString(R.string.password_field_empty));
					passwordError.setVisibility(View.VISIBLE);
					errorFound= true;
				}
				
				if(!errorFound){
					
						
						new WebServiceProcessingTask(mainActivity) {
							
							@Override
							public void preExecuteTask() {
								TAG=tag;
							
								
							}
							
							@Override
							public void postExecuteTask() {
								
						
							try {
							JSONObject jsonObject =	new JSONObject(jSONString);
								if (jsonObject.has(OrdritJsonKeys.TAG_TOKEN)) {
									 String token = null;
									 token = OrditJsonParser
											.getTokenStringFromJSON(jSONString);
									if (token != null) {
										SharedPreferencesUtil
												.saveStringPreferences(
														mainActivity,
														OrdritJsonKeys.TAG_TOKEN,
														token);
												getUserData();
									}
								}else {
									 Toast.makeText(getActivity(), "Username or Password is incorrect.", Toast.LENGTH_LONG).show();
								}
								
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								 Toast.makeText(getActivity(), "Server not responds. Please try again later.", Toast.LENGTH_LONG).show();
							}
								
								
	
							}
							
							@Override
							public void backgroundTask() {
								
								jSONString  = connection.postHttpUrlConnection(
										WebServicesRawDataUtil
												.getUsersAuthenticationTokenJSONObjectString(
														editTextUserName
																.getText()
																.toString(),
														editTextPassword
																.getText()
																.toString()),
										OrdritConstants.API_TOKEN_URL);	
								
							}
						}.execute();
						
					
				}
				
				}
		});

		return loginFragment;
	}
	private void getUserData() {
		
		new WebServiceProcessingTask(mainActivity) {
			JSONArray jsonArray;
			List<State> statesList;
			List<City> cityList;
			boolean success=false;
			
			@Override
			public void preExecuteTask() {
			TAG=tag;
			
			
			}
			
			@Override
			public void postExecuteTask() {
				if (success) {
					startActivity(new Intent(mainActivity,
							DashboardActivity.class));
					mainActivity.finish();	
				}else {
					Toast.makeText(mainActivity, "Problem in fetching user data..", Toast.LENGTH_LONG).show();
				}
				
			
			    
			}
			
			@Override
			public void backgroundTask() {
			
				Gson gson=new Gson();
				jSONString = connection.getHttpUrlConnectionForArray(
						OrdritConstants.SERVER_BASE_URL
								+ OrdritConstants.STATES,
						SharedPreferencesUtil.getStringPreferences(
								getActivity(), OrdritJsonKeys.TAG_TOKEN));
				try {
					//get state
					statesList = OrditJsonParser.getStateFromJSONArray(jSONString);
					SharedPreferencesUtil.saveStringPreferences(getActivity(), OrdritConstants.STATES, gson.toJson(statesList));
					
				jSONString = connection.getHttpUrlConnectionForArray(
						OrdritConstants.SERVER_BASE_URL
								+ OrdritConstants.CITIES,
						SharedPreferencesUtil.getStringPreferences(
								getActivity(), OrdritJsonKeys.TAG_TOKEN));
				// get city
				cityList = OrditJsonParser.getCityFromJSONArray(jSONString);
				SharedPreferencesUtil.saveStringPreferences(getActivity(), OrdritConstants.CITIES, gson.toJson(cityList));		
				User user= new User();
				jSONString = connection.getHttpUrlConnection(OrdritConstants.SERVER_BASE_URL+ 
						OrdritConstants.USERS+"/"+ OrdritConstants.CURRENT,SharedPreferencesUtil.getStringPreferences(
						getActivity(), OrdritJsonKeys.TAG_TOKEN));
				//get user data
				user= OrditJsonParser.updateUserWithPersonalInfo(user, jSONString);
				
				jSONString = connection.getHttpUrlConnection(OrdritConstants.SERVER_BASE_URL+ 
						OrdritConstants.USERS_ADDRESS+"?"+OrdritConstants.USER+"="+ user.getId(),SharedPreferencesUtil.getStringPreferences(
						getActivity(), OrdritJsonKeys.TAG_TOKEN));
					
					user.setToken(SharedPreferencesUtil.getStringPreferences(
							getActivity(), OrdritJsonKeys.TAG_TOKEN));
					user= OrditJsonParser.updateUserWithAddress(user, jSONString);
					SharedPreferencesUtil.saveStringPreferences(getActivity(), OrdritConstants.USER, gson.toJson(user));
					success=true;
				} catch (JSONException e) {
					success=false;
					Log.e(tag,"**"+e.toString());
					Log.e(tag,"**error in fetching after login data");
				}
				
			}
		}.execute();
		
	}
	
}
