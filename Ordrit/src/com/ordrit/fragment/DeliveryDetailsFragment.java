package com.ordrit.fragment;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ordrit.R;
import com.ordrit.activity.UILApplication;
import com.ordrit.model.User;
import com.ordrit.util.CommonUtils;
import com.ordrit.util.FragmentConstant;
import com.ordrit.util.OrdritConstants;
import com.ordrit.util.OrdritJsonKeys;
import com.ordrit.util.SharedPreferencesUtil;
import com.ordrit.util.WebServiceProcessingTask;


public class DeliveryDetailsFragment extends BaseFragment {

	private View deliveryDetailsFragment;
	boolean status;
	String orderID;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		deliveryDetailsFragment = inflater.inflate(R.layout.fragment_delivery_details,
				container, false);
		
		return deliveryDetailsFragment;
	}
@Override
public void onActivityCreated(Bundle savedInstanceState) {
	super.onActivityCreated(savedInstanceState);
	setupUiComponent();
	
}
	@Override
	void setupUiComponent() {
		
		Button itemDetailBack= (Button) deliveryDetailsFragment.findViewById(R.id.itemDetailBack);
		itemDetailBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dashboardActivity.popFragment(FragmentConstant.ITEM_DELIVERY_DETAIL_FRAGMENT);
			}
		});
		TextView textChangeAddress= (TextView) deliveryDetailsFragment.findViewById(R.id.textChangeAddress);
		textChangeAddress.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				dashboardActivity.commitFragment(new AddUpdateAddressFragment(),FragmentConstant.ADD_UPDATE_ADDRESS_FRAGMENT);
			}
		});
		Gson gson = new Gson();
		User user = gson.fromJson(SharedPreferencesUtil.getStringPreferences(
				getActivity(), OrdritConstants.USER), User.class);
		final String orderString = getArguments().getString("orderString");    
		TextView userNameText=(TextView) deliveryDetailsFragment.findViewById(R.id.textViewTextUserName);
		userNameText.setText(user.getFirstName()+" " + user.getLastName());
		TextView AddressText=(TextView) deliveryDetailsFragment.findViewById(R.id.textViewTextAddress);
		if(user.getAddress()!=null){
			AddressText.setText(user.getAddress().getStreetAddress() + "\n"
					+ CommonUtils.getCityNameFromUrl(user.getAddress().getCity().getUrl(), dashboardActivity)
				    + " "
					+ user.getAddress().getPincode() + " ("
					+ user.getAddress().getState().getName() + ")");    
			textChangeAddress.setText("Change Address");
		}else {
			AddressText.setText("Address not found. Please address first");
			textChangeAddress.setText("Add Address");
		}
		
		Button placeOrderButton= (Button) deliveryDetailsFragment.findViewById(R.id.buttonNext);
		placeOrderButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!orderString.isEmpty()){
				placeOrder(orderString);
				}
			}
		});
		
	}

	private void placeOrder(final String jsonString) {
		 
		new WebServiceProcessingTask(dashboardActivity) {
			
			@Override
			public void preExecuteTask() {
			
			}
			
			@Override
			public void postExecuteTask() {
				if(status== true){
					 UILApplication uilApplication =(UILApplication) dashboardActivity.getApplication();
					 uilApplication.setSelectedItemList(null);
					 Fragment orderConfirmationFragment= new OrderConfirmationFragment();
					 FragmentManager fm=getFragmentManager();
					 android.app.FragmentTransaction ft=fm.beginTransaction();
					 Bundle args = new Bundle();
					 args.putString("orderID", orderID);
					 orderConfirmationFragment.setArguments(args);
					 ft.replace(R.id.frame_container, orderConfirmationFragment);
					 ft.commit();
				}else{
					Toast.makeText(dashboardActivity, "Some error occurred, please try again.", Toast.LENGTH_SHORT).show();
				}
			    
			}
			
			@Override
			public void backgroundTask() {
			
				jSONString = connection.postHttpUrlConnection(jsonString,
						OrdritConstants.SERVER_BASE_URL
								+ OrdritConstants.ORDERS,
						SharedPreferencesUtil.getStringPreferences(
								getActivity(), OrdritJsonKeys.TAG_TOKEN));
				
					try {
						JSONObject responseObj= new JSONObject(jSONString);
						orderID= responseObj.getString(OrdritJsonKeys.TAG_ID);
						String statusResponse= responseObj.getString(OrdritJsonKeys.TAG_STATUS);
						if(statusResponse.equalsIgnoreCase("PEN")){
							status= true;
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		}.execute();
		
	}
	
}
