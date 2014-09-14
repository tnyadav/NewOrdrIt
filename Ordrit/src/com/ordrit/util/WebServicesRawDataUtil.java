package com.ordrit.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ordrit.activity.UILApplication;
import com.ordrit.model.User;

public class WebServicesRawDataUtil {

	// Getting a User’s Authentication Token

	public static String getUsersAuthenticationTokenJSONObjectString(String email, String password) {
		String userCredentialsString = new String();
		JSONObject userObject = new JSONObject();
		try {
			userObject.put(OrdritJsonKeys.USER_PASSWORD, password);
			userObject.put(OrdritJsonKeys.USER_EMAIL, email);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		userCredentialsString = userObject.toString();
		return userCredentialsString;
	}
	public static String getSuperTokenJSONObjectString() {
		String userCredentialsString = new String();
		
		JSONObject userObject = new JSONObject();
		try {
			userObject.put(OrdritJsonKeys.USER_PASSWORD,"anothercastle");
			userObject.put(OrdritJsonKeys.USER_EMAIL,"bowser@ordrit.in");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		userCredentialsString = userObject.toString();
		return userCredentialsString;
	}
	public static String getEmailIdJSONObjectString(String emailId) {
		String userCredentialsString = new String();
		
		JSONObject userObject = new JSONObject();
		try {
			userObject.put(OrdritJsonKeys.USER_EMAIL,emailId);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		userCredentialsString = userObject.toString();
		return userCredentialsString;
	}
	
	public static String editUserDetailsJSONObjectString(User user){
		String userEditDetailsString = new String();
		
		JSONObject userObject = new JSONObject();
		try {
			//userObject.put(OrdritJsonKeys.USER_PASSWORD, user.getPassword());
			userObject.put(OrdritJsonKeys.USER_EMAIL, user.getEmailId());
			userObject.put(OrdritJsonKeys.USER_FIRSTNAME, user.getFirstName());
			userObject.put(OrdritJsonKeys.USER_LASTNAME, user.getLastName());
			userObject.put(OrdritJsonKeys.USER_LAST_LOGIN, user.getLastLoginDate());
			userObject.put(OrdritJsonKeys.USER_DATE_JOINED, user.getJoinDate());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		userEditDetailsString = userObject.toString();
		
		return userEditDetailsString;
	}
	
	public static String placeOrderJSONObjectString(User user, UILApplication uilApplication, String orderedItemsListString){
		
		JSONObject orderObject = new JSONObject();
		try {
			
			orderObject.put(OrdritJsonKeys.TAG_CUSTOMER, "/" + OrdritConstants.USERS + "/"	+ user.getId());
			orderObject.put(OrdritJsonKeys.TAG_CUSTOMER_ADDRESS, "/"+ OrdritConstants.USERS_ADDRESS + "/"+ user.getAddress().getId());
			orderObject.put(OrdritJsonKeys.TAG_STORE, "/" + OrdritConstants.STORES + "/"
					+ uilApplication.getStoreId());
			orderObject.put(OrdritJsonKeys.TAG_COUPON_CODE, "abx");
			orderObject.put(OrdritConstants.ITEMS,new JSONArray(orderedItemsListString));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return orderObject.toString();
	}
	
}