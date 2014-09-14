package com.ordrit.fragment;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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

import com.ordrit.R;
import com.ordrit.activity.HomeActivity;
import com.ordrit.util.OrdritConstants;
import com.ordrit.util.OrdritJsonKeys;
import com.ordrit.util.ValidationUtils;
import com.ordrit.util.WebServiceProcessingTask;
import com.ordrit.util.WebServicesRawDataUtil;

public class ForgotPasswordFragment extends Fragment {

	private static final String tag="ForgotPasswordFragment";
	private View forgotPasswordFragment;
	private HomeActivity mainActivity = null;
	private Button buttonRecoverPassword;
	private EditText editTextEmailId;
	private TextView textEmailIdError;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mainActivity = (HomeActivity) getActivity();
		mainActivity.getActionBar().hide();
		forgotPasswordFragment = inflater.inflate(R.layout.fragment_forgot_password, container,
				false);
		
		// EditText
		editTextEmailId=  (EditText) forgotPasswordFragment.findViewById(R.id.editTextEmailId);
		
		textEmailIdError=  (TextView) forgotPasswordFragment.findViewById(R.id.textEmailIdError);
		
		
		
		//RecoverPassword Button
		buttonRecoverPassword= (Button) forgotPasswordFragment.findViewById(R.id.buttonRecoverPassword);
		buttonRecoverPassword.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				textEmailIdError.setVisibility(View.GONE);
				if(!fieldValidation()){
					
					
					new WebServiceProcessingTask(mainActivity) {
						
						@Override
						public void preExecuteTask() {
							TAG=tag;
							
							
						}
						
						@Override
						public void postExecuteTask() {
							if(!jSONString.isEmpty()){
							
							JSONObject jsonObject;
							try {
								jsonObject = new JSONObject(jSONString);

							} catch (JSONException e) {
								e.printStackTrace();
								Toast.makeText(mainActivity,
										"Server not responds.. ",
										Toast.LENGTH_LONG).show();
								return;
							}
							
							// error
							try {
								if (jsonObject
										.get(OrdritJsonKeys.TAG_DETAIL).toString().equalsIgnoreCase("not found")) {
									textEmailIdError.setVisibility(View.VISIBLE);
									textEmailIdError.setText("We couldn't find a OrdrIt account associated with "+ editTextEmailId.getText().toString());
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else{
							 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
										(Context)mainActivity);
						 
									// set title
									alertDialogBuilder.setTitle("Ordrit");
						 
									// set dialog message
									alertDialogBuilder
										.setMessage("Your password reset link is successfully delivered to your Email Id.")
										.setCancelable(false)
										.setPositiveButton("OK",new DialogInterface.OnClickListener() {
											public void onClick(DialogInterface dialog,int id) {
												dialog.cancel();
												redirectToLogin();
											}
										  });
						 
										// create alert dialog
										AlertDialog alertDialog = alertDialogBuilder.create();
						 
										// show it
										alertDialog.show();
						}
						}
						
						@Override
						public void backgroundTask() {
							
							jSONString  = connection.postHttpUrlConnection(
									WebServicesRawDataUtil
											.getEmailIdJSONObjectString(editTextEmailId.getText().toString()),
									OrdritConstants.SERVER_BASE_URL+ OrdritConstants.USER_FORGOT_PASSWORD);	
						}
					}.execute();
					
					
				}
			}
		});
		
		return forgotPasswordFragment;
	}
	
	private boolean fieldValidation(){
		boolean containsError = false;
		
		
		if(editTextEmailId.getText().length()== 0){
			textEmailIdError.setVisibility(View.VISIBLE);
			textEmailIdError.setText(mainActivity.getResources().getString(R.string.empty_email_error));
			containsError = true;
		}else if(!ValidationUtils.isEmailValid(editTextEmailId.getText().toString())){
			textEmailIdError.setVisibility(View.VISIBLE);
			textEmailIdError.setText(mainActivity.getResources().getString(R.string.invalid_email));
			containsError = true;
		}
		return containsError;
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
