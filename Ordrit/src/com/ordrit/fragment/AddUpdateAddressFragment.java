package com.ordrit.fragment;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ordrit.R;
import com.ordrit.adapter.CityListAdapter;
import com.ordrit.adapter.StateListAdapter;
import com.ordrit.model.Address;
import com.ordrit.model.City;
import com.ordrit.model.State;
import com.ordrit.model.User;
import com.ordrit.util.CommonUtils;
import com.ordrit.util.FragmentConstant;
import com.ordrit.util.OrditJsonParser;
import com.ordrit.util.OrdritConstants;
import com.ordrit.util.OrdritJsonKeys;
import com.ordrit.util.SharedPreferencesUtil;
import com.ordrit.util.ValidationUtils;
import com.ordrit.util.WebServiceProcessingTask;

public class AddUpdateAddressFragment extends BaseFragment {
	private final String tag = "AddUpdateAddressFragment";
	private View addUpdateAddressFragment;
	private Button addUpdateAddressBack,btnAddUpdateAddressSaveOrUpdate;
	
	private EditText etAddUpdateAddressHomeOrApartmentName,
	/*etAddUpdateAddressStreet1,*/etAddUpdateAddressState,etAddUpdateAddressCity,
	etAddUpdateAddressZipcode;
	
/*	private TextView txtAddUpdateAddressHomeOrApartmentNameError,
	txtAddUpdateAddressStreet1Error,txtAddUpdateAddressStateError,
	txtAddUpdateAddressCityOrZipcodeError;*/
	private Address address;
	List<State> statesList;
	List<City> cityList;
	User user;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		addUpdateAddressFragment = inflater.inflate(
				R.layout.fragment_add_or_update_address, container, false);
	
		return addUpdateAddressFragment;
	}
@Override
public void onActivityCreated(Bundle savedInstanceState) {
	
	super.onActivityCreated(savedInstanceState);
	statesList = dashboardActivity.getStatesList();
	cityList = dashboardActivity.getCityList();
	user=dashboardActivity.getUser();
	if(user.getAddress()!= null){
	address=user.getAddress();
	}
	setupUiComponent();
	
}
	@Override
	void setupUiComponent() {
		addUpdateAddressBack = (Button) addUpdateAddressFragment
				.findViewById(R.id.addUpdateAddressBack);
		addUpdateAddressBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dashboardActivity
						.popFragment(FragmentConstant.ADD_UPDATE_ADDRESS_FRAGMENT);
			}
		});
		btnAddUpdateAddressSaveOrUpdate = (Button) addUpdateAddressFragment
				.findViewById(R.id.btnAddUpdateAddressSaveOrUpdate);
		if (address==null) {
			btnAddUpdateAddressSaveOrUpdate.setText("Save");
		}else {
			btnAddUpdateAddressSaveOrUpdate.setText("Update");
		}
		btnAddUpdateAddressSaveOrUpdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				final String strAddUpdateAddressHomeOrApartmentName = etAddUpdateAddressHomeOrApartmentName.getText().toString();
				final String strAddUpdateAddressState =getStateUrl(etAddUpdateAddressState.getText().toString());
				final String strAddUpdateAddressCity=getCityUrl(etAddUpdateAddressCity.getText().toString());
				final String strAddUpdateAddressZipcode=etAddUpdateAddressZipcode.getText().toString();
				// validation if true
				if (!ValidationUtils.isEmpty(strAddUpdateAddressHomeOrApartmentName)) {
					showText("Please Enter Home or Apartment Name");
					return;
				}
				if (!ValidationUtils.isEmpty(strAddUpdateAddressState)) {
					showText("Please Select State");
					return;
				}
				if (!ValidationUtils.isEmpty(strAddUpdateAddressCity)) {
					showText("Please Select City");
					return;
				}
				if (!ValidationUtils.isEmpty(strAddUpdateAddressZipcode)) {
					showText("Please Enter Zipcode");
					return;
				}
				new WebServiceProcessingTask(dashboardActivity) {
					
					@Override
					public void preExecuteTask() {
					TAG=tag;
					}
					
					@Override
					public void postExecuteTask() {
						if (null!=address) {
						user.setAddress(address);
						dashboardActivity.setUser(user);
						setAddress();
						showText("Address Updated Successfully");
						}else {
							Toast.makeText(dashboardActivity, "Address Updation failed, Please try again later", Toast.LENGTH_LONG).show();
							
						}
					}
					
					@Override
					public void backgroundTask() {
					 
						List<NameValuePair> list=new ArrayList<NameValuePair>();
						list.add(new BasicNameValuePair(OrdritJsonKeys.TAG_USER, OrdritConstants.SERVER_BASE_URL
								+ OrdritConstants.USERS+"/"+user.getId()));
						list.add(new BasicNameValuePair(OrdritJsonKeys.TAG_STREET_ADDRESS, strAddUpdateAddressHomeOrApartmentName));
						list.add(new BasicNameValuePair(OrdritJsonKeys.TAG_CITY, strAddUpdateAddressCity));
						list.add(new BasicNameValuePair(OrdritJsonKeys.TAG_STATE, strAddUpdateAddressState));
		                list.add(new BasicNameValuePair(OrdritJsonKeys.TAG_PINCODE, strAddUpdateAddressZipcode));
						
						
						jSONString  = connection.postHttpUrlConnection(CommonUtils.getParamListJSONString(list),OrdritConstants.SERVER_BASE_URL
								+ OrdritConstants.USERS_ADDRESS,
						SharedPreferencesUtil.getStringPreferences(
								dashboardActivity, OrdritJsonKeys.TAG_TOKEN));
								
						try {
							address = OrditJsonParser.getMerchantAddressFromJSON(jSONString);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}.execute();
			}
		});
		
        //edittext
		etAddUpdateAddressHomeOrApartmentName = (EditText) addUpdateAddressFragment
				.findViewById(R.id.etAddUpdateAddressHomeOrApartmentName);
		etAddUpdateAddressState = (EditText) addUpdateAddressFragment
				.findViewById(R.id.etAddUpdateAddressState);
		etAddUpdateAddressState.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			showStateDialog();
				
			}
		});
		etAddUpdateAddressCity = (EditText) addUpdateAddressFragment
				.findViewById(R.id.etAddUpdateAddressCity);
		
		etAddUpdateAddressCity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			showCityDialog();
				
			}
		});
		
		etAddUpdateAddressZipcode = (EditText) addUpdateAddressFragment
				.findViewById(R.id.etAddUpdateAddressZipcode);
		
		
		//error textview
	/*	txtAddUpdateAddressHomeOrApartmentNameError = (TextView) addUpdateAddressFragment
				.findViewById(R.id.txtAddUpdateAddressHomeOrApartmentNameError);
			txtAddUpdateAddressStateError = (TextView) addUpdateAddressFragment
				.findViewById(R.id.txtAddUpdateAddressStateError);
		txtAddUpdateAddressCityOrZipcodeError = (TextView) addUpdateAddressFragment
				.findViewById(R.id.txtAddUpdateAddressCityOrZipcodeError);*/
		setAddress();
		dashboardActivity.checkCartItems(addUpdateAddressFragment);
	}
	public void setAddress() {
		if (null!=address) {
			
			etAddUpdateAddressHomeOrApartmentName.setText(address.getStreetAddress());
			String stateName= getStateName(address.getState().getUrl());
			etAddUpdateAddressState.setText(stateName);
			address.getState().setName(stateName);
			String ciString= getCityName(address.getCity().getUrl());
			address.getCity().setName(ciString);
			etAddUpdateAddressCity.setText(ciString);
			etAddUpdateAddressZipcode.setText(address.getPincode());
			user.setAddress(address);
			dashboardActivity.setUser(user);
		}
	}

	private void showStateDialog() {
		final Dialog dialog = new Dialog(dashboardActivity);
		dialog.getWindow();
	    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_states_list);
        final TextView dialogTitle=(TextView)dialog.findViewById(R.id.dialogTitle);
        dialogTitle.setText("State");
        final ListView lv = (ListView) dialog.findViewById(R.id.lv);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				etAddUpdateAddressState.setText(statesList.get(position).getName());
				dialog.dismiss();
				
			}
		});
		dialog.setCancelable(true);
		
		if (statesList!=null) {
			StateListAdapter adapter = new StateListAdapter(dashboardActivity, R.layout.states_list_item,statesList);
		    lv.setAdapter(adapter);	
		} 
	
		dialog.show();
	}
	private void showCityDialog() {
		final Dialog dialog = new Dialog(dashboardActivity);
		dialog.getWindow();
	    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_states_list);
        final TextView dialogTitle=(TextView)dialog.findViewById(R.id.dialogTitle);
        dialogTitle.setText("Cities");
       final ListView lv = (ListView) dialog.findViewById(R.id.lv);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				etAddUpdateAddressCity.setText(cityList.get(position).getName());
				dialog.dismiss();
				
			}
		});
		dialog.setCancelable(true);
		if (cityList!=null) {
			CityListAdapter adapter = new CityListAdapter(dashboardActivity, R.layout.states_list_item,cityList);
		    lv.setAdapter(adapter);	
		} 
	
		dialog.show();
	}
	private String getStateUrl(String name) {
		String url = null;
		for (int i = 0; i < statesList.size(); i++) {
			State states =statesList.get(i);
			if (states.getName().equals(name)) {
				url=states.getUrl();
				break;
			}
		}
		
		return url;
	}
	private String getCityUrl(String name) {
		String url = null;
		for (int i = 0; i < cityList.size(); i++) {
			City city =cityList.get(i);
			if (city.getName().equals(name)) {
				url=city.getUrl();
				break;
			}
		}
		
		return url;
	}
	private String getStateName(String url) {
		String name = null;
		for (int i = 0; i < statesList.size(); i++) {
			State states =statesList.get(i);
			if (states.getUrl().equals(url)) {
				name=states.getName();
				break;
			}
		}
		
		return name;
	}
	private String getCityName(String url) {
		String name = null;
		for (int i = 0; i < cityList.size(); i++) {
			City city =cityList.get(i);
			if (city.getUrl().equals(url)) {
				name=city.getName();
				break;
			}
		}
		
		return name;
	}
	private void showText(String text) {
		Toast.makeText(dashboardActivity, text, Toast.LENGTH_SHORT).show();
	}
}
