package com.ordrit.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ordrit.model.Address;
import com.ordrit.model.City;
import com.ordrit.model.Item;
import com.ordrit.model.ItemCategory;
import com.ordrit.model.ItemSubCategory;
import com.ordrit.model.Order;
import com.ordrit.model.State;
import com.ordrit.model.Store;
import com.ordrit.model.User;

public class OrditJsonParser {

	public static String getTokenStringFromJSON(String jSONOString)
			throws JSONException {
		JSONObject obj=new JSONObject(jSONOString);
		String token = null;
		token = obj.get(OrdritJsonKeys.TAG_TOKEN).toString();
		return token;
	}

	public static Map getItemCategoryMap(String jsonString) throws JSONException{
		
		Map<String, ItemCategory> itemCategoryMap = new TreeMap<String, ItemCategory>();
		// jsonString = "[{\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}]";
		Set<String> subcategoryIdsSet= new HashSet<String>();
		 try {
			JSONArray jsonArray = new JSONArray(jsonString);
			for(int i = 0; i < jsonArray.length(); i++){ 
				JSONObject subCategoryJSONObj = jsonArray.getJSONObject(i);
				if(!subcategoryIdsSet.contains(subCategoryJSONObj.getString(OrdritJsonKeys.TAG_ID))){
					subcategoryIdsSet.add(subCategoryJSONObj.getString(OrdritJsonKeys.TAG_ID));
			   JSONObject categoryObj= subCategoryJSONObj.getJSONObject(OrdritJsonKeys.TAG_CATEGORY);
			   ItemCategory itemCategory=null;
			   String categoryId= categoryObj.getString(OrdritJsonKeys.TAG_ID);
			   if(!itemCategoryMap.containsKey(categoryId)){
			    itemCategory= new ItemCategory();
			    itemCategory.setId(categoryId);
			    itemCategory.setName(categoryObj.getString(OrdritJsonKeys.TAG_NAME));
			    itemCategory.setUrl((categoryObj.getString(OrdritJsonKeys.TAG_URL)));
			    itemCategory.setItemSubCategory(new ArrayList<ItemSubCategory>());
			   }else{
			    itemCategory= itemCategoryMap.get(categoryId);
			   }
			   ItemSubCategory itemSubCategory= new ItemSubCategory(); 
			   itemSubCategory.setId(subCategoryJSONObj.getString(OrdritJsonKeys.TAG_ID));
			   itemSubCategory.setName(subCategoryJSONObj.getString(OrdritJsonKeys.TAG_NAME));
			   itemSubCategory.setUrl(subCategoryJSONObj.getString(OrdritJsonKeys.TAG_URL));
			   itemCategory.getItemSubCategory().add(itemSubCategory);
			   
			   itemCategoryMap.put(itemCategory.getId(), itemCategory);
			}
			   }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 subcategoryIdsSet=null;
		return itemCategoryMap;
	}
	
	public static List<Store> getAllStoresFromJSON(String jsonString)
			throws JSONException {
		JSONObject obj=new JSONObject(jsonString);
		JSONArray storesArray = obj.getJSONArray(OrdritJsonKeys.TAG_RESULTS);
		List<Store> storeList = new ArrayList<Store>();
		for (int i = 0; i < storesArray.length(); i++) {
			Store store = new Store();
			JSONObject storeJsonObj = storesArray.getJSONObject(i);
			store.setStoreName(storeJsonObj.getString(OrdritJsonKeys.TAG_NAME));
			store.setLocationLatLong(storeJsonObj
					.getString(OrdritJsonKeys.TAG_LOCATION));
			store.setId(storeJsonObj.getString(OrdritJsonKeys.TAG_ID));
			store.setUrl(storeJsonObj.getString(OrdritJsonKeys.TAG_URL));
			store.setPhoneNumber1(storeJsonObj
					.getString(OrdritJsonKeys.TAG_PHONENUMBER_1));
			store.setPhoneNumber2(storeJsonObj
					.getString(OrdritJsonKeys.TAG_PHONENUMBER_2));
			store.setOpenAt(storeJsonObj
					.getString(OrdritJsonKeys.TAG_OPEN_AT));
			store.setCloseAt(storeJsonObj
					.getString(OrdritJsonKeys.TAG_CLOSE_AT));
			store.setMinimumOrder(storeJsonObj
					.getString(OrdritJsonKeys.TAG_MINIMUM_ORDER));
			User merchant = new User();
			merchant.setEmailId(storeJsonObj.getJSONObject(
					OrdritJsonKeys.TAG_MERCHANT).getString(
					OrdritJsonKeys.TAG_NAME));
			merchant.setId(storeJsonObj.getJSONObject(
					OrdritJsonKeys.TAG_MERCHANT).getString(
					OrdritJsonKeys.TAG_ID));
			store.setUser(merchant);
			Address merchantAddress = new Address();
			merchantAddress.setId(storeJsonObj.getJSONObject(
					OrdritJsonKeys.TAG_ADDRESS).getString(
					OrdritJsonKeys.TAG_ID));
			merchantAddress.setStreetAddress(storeJsonObj.getJSONObject(
					OrdritJsonKeys.TAG_ADDRESS).getString(
					OrdritJsonKeys.TAG_STREET_ADDRESS));
			State state = new State();
			state.setUrl(storeJsonObj.getJSONObject(
					OrdritJsonKeys.TAG_ADDRESS).getString(
					OrdritJsonKeys.TAG_STATE));
			merchantAddress.setState(state);
			City city= new City();
			city.setUrl(storeJsonObj.getJSONObject(
					OrdritJsonKeys.TAG_ADDRESS).getString(
					OrdritJsonKeys.TAG_CITY));
			merchantAddress.setCity(city);
			merchantAddress.setPincode(storeJsonObj.getJSONObject(
					OrdritJsonKeys.TAG_ADDRESS).getString(
					OrdritJsonKeys.TAG_PINCODE));
			store.setAddress(merchantAddress);
			storeList.add(store);
		}
		return storeList;
	}
	public static User getUserFromJSON(User user,String jsonString) throws JSONException{
		JSONObject obj=new JSONObject(jsonString);
		
		user.setEmailId(obj.getString(OrdritJsonKeys.USER_EMAIL));
		user.setFirstName(obj.getString(OrdritJsonKeys.USER_FIRSTNAME));
		user.setLastName(obj.getString(OrdritJsonKeys.USER_LASTNAME));
		user.setRole(obj.getString(OrdritJsonKeys.USER_ROLE));
		user.setJoinDate(obj.getString(OrdritJsonKeys.USER_DATE_JOINED));
		user.setLastLoginDate(obj.getString(OrdritJsonKeys.USER_LAST_LOGIN));
		user.setPhoneNumber(obj.getString(OrdritJsonKeys.USER_PHONE_NUMBER));
		user.setGcmRegistrationId(obj.getString(OrdritJsonKeys.USER_GCM_REGISTRATION_ID));
		user.setId(obj.getString(OrdritJsonKeys.USER_ID));
		user.setUrl(obj.getString(OrdritJsonKeys.USER_URL));
		return user;
	}
	public static Address getMerchantAddressFromJSON(String jsonString) throws JSONException{
		JSONObject jsonObject=new JSONObject(jsonString);
		
		Address merchantAddress = new Address();

		merchantAddress.setStreetAddress(jsonObject.getString(
				OrdritJsonKeys.TAG_STREET_ADDRESS));
		State state = new State();
		state.setUrl(jsonObject.getString(
				OrdritJsonKeys.TAG_STATE));
		merchantAddress.setState(state);
		City city= new City();
		city.setUrl(jsonObject.getString(
				OrdritJsonKeys.TAG_CITY));
		merchantAddress.setCity(city);
		merchantAddress.setPincode(jsonObject.getString(
				OrdritJsonKeys.TAG_PINCODE));
		merchantAddress.setId(jsonObject.getString(
				OrdritJsonKeys.TAG_ID));
		return merchantAddress;
	}

	public static List<State> getStateFromJSONArray(String jsonString)throws JSONException {
		JSONArray jsonArray= new JSONArray(jsonString);
		List<State> states = new ArrayList<State>();
		for (int i = 0; i < jsonArray.length(); i++) {
			states.add(getStateFromJSON(jsonArray.getJSONObject(i)));
		}

		return states;
	}
	public static List<City> getCityFromJSONArray(String jsonString)throws JSONException {
		JSONArray jsonArray= new JSONArray(jsonString);
		List<City> cities = new ArrayList<City>();
		for (int i = 0; i < jsonArray.length(); i++) {
			cities.add(getCityFromJSON(jsonArray.getJSONObject(i)));
		}

		return cities;
	}
	public static State getStateFromJSON(JSONObject jsonObject) throws JSONException{
		State states=new State();
		states.setName(jsonObject.getString(OrdritJsonKeys.TAG_NAME));
		states.setUrl(jsonObject.getString(OrdritJsonKeys.TAG_URL));

		return states;
	}
	public static City getCityFromJSON(JSONObject jsonObject) throws JSONException{
		City city=new City();
		city.setName(jsonObject.getString(OrdritJsonKeys.TAG_NAME));
		city.setUrl(jsonObject.getString(OrdritJsonKeys.TAG_URL));

		return city;
	}
	public static List<Item> getItemsUnderSubCategory(String storeId, String itemSubCategoryId,String jSONString) throws JSONException{
	
		List<Item> itemList= new ArrayList<Item>();
		JSONObject jsonObj = new JSONObject(jSONString);
		JSONArray jsonArray = jsonObj.getJSONArray(OrdritJsonKeys.TAG_RESULTS);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject itemJsonObj = jsonArray.getJSONObject(i);
			String currentObjsubCategoryId= itemJsonObj.getJSONObject(OrdritJsonKeys.TAG_SUB_CATEGORY).getString(OrdritJsonKeys.TAG_ID);
			String currentObjStoreId= itemJsonObj.getJSONObject(OrdritJsonKeys.TAG_STORE).getString(OrdritJsonKeys.TAG_ID);
			if(storeId.equalsIgnoreCase(currentObjStoreId) && itemSubCategoryId.equalsIgnoreCase(currentObjsubCategoryId)){
				Item item= new Item();
				item.setId(itemJsonObj.getString(OrdritJsonKeys.TAG_ID));
				item.setPricePerUnit(itemJsonObj.getString(OrdritJsonKeys.TAG_PRICE));
				item.setUnitName(itemJsonObj.getString(OrdritJsonKeys.TAG_PRICE_UNITS));
				item.setName(itemJsonObj.getString(OrdritJsonKeys.TAG_NAME));
				item.setImageURL(OrdritConstants.SERVER_BASE_URL
						+ itemJsonObj.getJSONObject(OrdritJsonKeys.TAG_IMAGE)
								.getString(OrdritJsonKeys.TAG_IMAGE));
				itemList.add(item);
			}
		}
		return itemList;
	}
	
	public static User updateUserWithAddress(User user, String jsonString) throws JSONException{
		if(jsonString!=null && !jsonString.isEmpty()){
		JSONObject jsonObj= new JSONObject(jsonString);
		JSONArray jsonArray= jsonObj.getJSONArray(OrdritJsonKeys.TAG_RESULTS);
		for(int i=jsonArray.length()-1;i>=0;i--){
			JSONObject itemJsonObj = jsonArray.getJSONObject(i);
			if(itemJsonObj.getString(OrdritJsonKeys.TAG_USER).equalsIgnoreCase(OrdritConstants.SERVER_BASE_URL+OrdritJsonKeys.TAG_USERS+"/"+user.getId())){
				
				Address userAddress = new Address();
				userAddress.setId(itemJsonObj.getString(
						OrdritJsonKeys.TAG_ID));
				userAddress.setStreetAddress(itemJsonObj.getString(
						OrdritJsonKeys.TAG_STREET_ADDRESS));
				userAddress.setPincode(itemJsonObj.getString(
						OrdritJsonKeys.TAG_PINCODE));
				
				City city= new City();
				city.setUrl(itemJsonObj.getString(
						OrdritJsonKeys.TAG_CITY));
				userAddress.setCity(city);
				State state = new State();
				state.setName(itemJsonObj.getJSONObject(
						OrdritJsonKeys.TAG_STATE).getString(
						OrdritJsonKeys.TAG_NAME));
				state.setUrl(itemJsonObj.getJSONObject(
						OrdritJsonKeys.TAG_STATE).getString(
						OrdritJsonKeys.TAG_URL));
				userAddress.setState(state);
				user.setAddress(userAddress);
				break;
			}	
			
		}
		}		
		return user;
	}
	
	public static User updateUserWithPersonalInfo(User user, String jsonString) throws JSONException{
		JSONObject itemJsonObj= new JSONObject(jsonString);
		user.setEmailId(itemJsonObj.getString(
				OrdritJsonKeys.USER_EMAIL));
		user.setFirstName(itemJsonObj.getString(
				OrdritJsonKeys.USER_FIRSTNAME));
		user.setLastName(itemJsonObj.getString(
				OrdritJsonKeys.USER_LASTNAME));
		user.setPhoneNumber(itemJsonObj.getString(
				OrdritJsonKeys.USER_PHONE_NUMBER));
		user.setId(itemJsonObj.getString(
				OrdritJsonKeys.USER_ID));
		user.setUrl(itemJsonObj.getString(
				OrdritJsonKeys.USER_URL));
		return user;
				
	}
	
	//Get pending orders for showing in Order Status
	public static List<Order> getPendingOrders(String jsonString) {
		List<Order> orders= new ArrayList<Order>();
		try{
		//jsonString="{\"count\": 39, \"next\": null, \"previous\": null, \"results\": [{\"customer\": {\"email\": \"kishansahu87@gmail.com\", \"role\": \"CST\", \"first_name\": \"first\", \"last_name\": \"last\", \"date_joined\": \"2014-05-20T07:58:44.602Z\", \"last_login\": \"2014-05-20T07:58:44.602Z\", \"phone_number\": \"6372842999\", \"gcm_registration_id\": \"\", \"id\": 8, \"url\": \"http://staging.ankursethi.in/users/8\"}, \"customer_address\": {\"user\": \"http://staging.ankursethi.in/users/8\", \"street_address\": \"B23, Baker Street\", \"city\": \"http://staging.ankursethi.in/cities/1\", \"state\": {\"name\": \"Haryana\", \"created_on\": \"2014-04-22T11:31:07.235Z\", \"id\": 1, \"url\": \"http://staging.ankursethi.in/states/1\"}, \"pin_code\": \"110035\", \"created_on\": \"2014-06-25T19:11:19.965Z\", \"id\": 16, \"url\": \"http://staging.ankursethi.in/user_addresses/16\"}, \"store\": {\"name\": \"Webchutney Store\", \"merchant\": {\"name\": \"nishantv.pandey@gmail.com\", \"user\": \"http://staging.ankursethi.in/users/3\", \"stores\": [\"http://staging.ankursethi.in/stores/4\"], \"created_on\": \"2014-04-29T08:45:13.096Z\", \"id\": 3, \"url\": \"http://staging.ankursethi.in/merchants/3\"}, \"address\": {\"street_address\": \"88, Bin Laden Avenue , Karachi\", \"city\": \"http://staging.ankursethi.in/cities/1\", \"state\": \"http://staging.ankursethi.in/states/1\", \"pin_code\": \"12008\", \"created_on\": \"2014-05-05T17:45:28.660Z\", \"id\": 2, \"url\": \"http://staging.ankursethi.in/store_addresses/2\"}, \"location\": \"POINT (77.0835639354858131 28.4985138838636587)\", \"opens_at\": \"09:00:00\", \"closes_at\": \"17:00:00\", \"minimum_order\": \"250.00\", \"estimated_delivery_time\": 60, \"sub_category\": null, \"created_on\": \"2014-05-05T17:45:29.001Z\", \"id\": 4, \"url\": \"http://staging.ankursethi.in/stores/4\", \"phone_number_1\": \"9958746143\", \"phone_number_2\": \"98653241\"}, \"status\": \"PEN\", \"merchant\": {\"name\": \"nishantv.pandey@gmail.com\", \"user\": \"http://staging.ankursethi.in/users/3\", \"stores\": [\"http://staging.ankursethi.in/stores/4\"], \"created_on\": \"2014-04-29T08:45:13.096Z\", \"id\": 3, \"url\": \"http://staging.ankursethi.in/merchants/3\"}, \"item_orders\": [{\"item\": {\"name\": \"Butter Spread \", \"description\": \"butter-spread-25-200g\", \"price\": \"25.00\", \"price_units\": \"UN\", \"store\": \"http://staging.ankursethi.in/stores/4\", \"image\": \"http://staging.ankursethi.in/product_images/42\", \"merchant\": \"http://staging.ankursethi.in/merchants/3\", \"sub_category\": \"http://staging.ankursethi.in/item_sub_categories/22\", \"created_on\": \"2014-06-02T13:44:11.766Z\", \"id\": 32, \"url\": \"http://staging.ankursethi.in/inventory_items/32\"}, \"order\": \"http://staging.ankursethi.in/orders/41\", \"quantity\": 2, \"created_on\": \"2014-06-26T08:43:33.842Z\", \"id\": 49, \"url\": \"http://staging.ankursethi.in/item_orders/49\"}, {\"item\": {\"name\": \"Amul Gold Milk\", \"description\": \"amul-uht-milk-gold-60-1ltr\", \"price\": \"60.00\", \"price_units\": \"UN\", \"store\": \"http://staging.ankursethi.in/stores/4\", \"image\": \"http://staging.ankursethi.in/product_images/49\", \"merchant\": \"http://staging.ankursethi.in/merchants/3\", \"sub_category\": \"http://staging.ankursethi.in/item_sub_categories/24\", \"created_on\": \"2014-06-02T15:55:27.121Z\", \"id\": 53, \"url\": \"http://staging.ankursethi.in/inventory_items/53\"}, \"order\": \"http://staging.ankursethi.in/orders/41\", \"quantity\": 1, \"created_on\": \"2014-06-26T08:43:33.844Z\", \"id\": 50, \"url\": \"http://staging.ankursethi.in/item_orders/50\"}, {\"item\": {\"name\": \"Amul Rose Milk\", \"description\": \"amul-milk-rose-20-200ml\", \"price\": \"20.00\", \"price_units\": \"UN\", \"store\": \"http://staging.ankursethi.in/stores/4\", \"image\": \"http://staging.ankursethi.in/product_images/77\", \"merchant\": \"http://staging.ankursethi.in/merchants/3\", \"sub_category\": \"http://staging.ankursethi.in/item_sub_categories/26\", \"created_on\": \"2014-06-02T16:03:28.712Z\", \"id\": 62, \"url\": \"http://staging.ankursethi.in/inventory_items/62\"}, \"order\": \"http://staging.ankursethi.in/orders/41\", \"quantity\": 1, \"created_on\": \"2014-06-26T08:43:33.845Z\", \"id\": 51, \"url\": \"http://staging.ankursethi.in/item_orders/51\"}], \"created_on\": \"2014-06-26T08:43:33.812Z\", \"coupon_code\": \"abx\", \"id\": 41, \"url\": \"http://staging.ankursethi.in/orders/41\"}, {\"customer\": {\"email\": \"kishansahu87@gmail.com\", \"role\": \"CST\", \"first_name\": \"first\", \"last_name\": \"last\", \"date_joined\": \"2014-05-20T07:58:44.602Z\", \"last_login\": \"2014-05-20T07:58:44.602Z\", \"phone_number\": \"6372842999\", \"gcm_registration_id\": \"\", \"id\": 8, \"url\": \"http://staging.ankursethi.in/users/8\"}, \"customer_address\": {\"user\": \"http://staging.ankursethi.in/users/8\", \"street_address\": \"B23, Baker Street\", \"city\": \"http://staging.ankursethi.in/cities/1\", \"state\": {\"name\": \"Haryana\", \"created_on\": \"2014-04-22T11:31:07.235Z\", \"id\": 1, \"url\": \"http://staging.ankursethi.in/states/1\"}, \"pin_code\": \"110035\", \"created_on\": \"2014-06-25T19:11:19.965Z\", \"id\": 16, \"url\": \"http://staging.ankursethi.in/user_addresses/16\"}, \"store\": {\"name\": \"Webchutney Store\", \"merchant\": {\"name\": \"nishantv.pandey@gmail.com\", \"user\": \"http://staging.ankursethi.in/users/3\", \"stores\": [\"http://staging.ankursethi.in/stores/4\"], \"created_on\": \"2014-04-29T08:45:13.096Z\", \"id\": 3, \"url\": \"http://staging.ankursethi.in/merchants/3\"}, \"address\": {\"street_address\": \"88, Bin Laden Avenue , Karachi\", \"city\": \"http://staging.ankursethi.in/cities/1\", \"state\": \"http://staging.ankursethi.in/states/1\", \"pin_code\": \"12008\", \"created_on\": \"2014-05-05T17:45:28.660Z\", \"id\": 2, \"url\": \"http://staging.ankursethi.in/store_addresses/2\"}, \"location\": \"POINT (77.0835639354858131 28.4985138838636587)\", \"opens_at\": \"09:00:00\", \"closes_at\": \"17:00:00\", \"minimum_order\": \"250.00\", \"estimated_delivery_time\": 60, \"sub_category\": null, \"created_on\": \"2014-05-05T17:45:29.001Z\", \"id\": 4, \"url\": \"http://staging.ankursethi.in/stores/4\", \"phone_number_1\": \"9958746143\", \"phone_number_2\": \"98653241\"}, \"status\": \"PEN\", \"merchant\": {\"name\": \"nishantv.pandey@gmail.com\", \"user\": \"http://staging.ankursethi.in/users/3\", \"stores\": [\"http://staging.ankursethi.in/stores/4\"], \"created_on\": \"2014-04-29T08:45:13.096Z\", \"id\": 3, \"url\": \"http://staging.ankursethi.in/merchants/3\"}, \"item_orders\": [{\"item\": {\"name\": \"Butter Spread \", \"description\": \"butter-spread-25-200g\", \"price\": \"25.00\", \"price_units\": \"UN\", \"store\": \"http://staging.ankursethi.in/stores/4\", \"image\": \"http://staging.ankursethi.in/product_images/42\", \"merchant\": \"http://staging.ankursethi.in/merchants/3\", \"sub_category\": \"http://staging.ankursethi.in/item_sub_categories/22\", \"created_on\": \"2014-06-02T13:44:11.766Z\", \"id\": 32, \"url\": \"http://staging.ankursethi.in/inventory_items/32\"}, \"order\": \"http://staging.ankursethi.in/orders/41\", \"quantity\": 2, \"created_on\": \"2014-06-26T08:43:33.842Z\", \"id\": 49, \"url\": \"http://staging.ankursethi.in/item_orders/49\"}, {\"item\": {\"name\": \"Amul Gold Milk\", \"description\": \"amul-uht-milk-gold-60-1ltr\", \"price\": \"60.00\", \"price_units\": \"UN\", \"store\": \"http://staging.ankursethi.in/stores/4\", \"image\": \"http://staging.ankursethi.in/product_images/49\", \"merchant\": \"http://staging.ankursethi.in/merchants/3\", \"sub_category\": \"http://staging.ankursethi.in/item_sub_categories/24\", \"created_on\": \"2014-06-02T15:55:27.121Z\", \"id\": 53, \"url\": \"http://staging.ankursethi.in/inventory_items/53\"}, \"order\": \"http://staging.ankursethi.in/orders/41\", \"quantity\": 1, \"created_on\": \"2014-06-26T08:43:33.844Z\", \"id\": 50, \"url\": \"http://staging.ankursethi.in/item_orders/50\"}, {\"item\": {\"name\": \"Amul Rose Milk\", \"description\": \"amul-milk-rose-20-200ml\", \"price\": \"20.00\", \"price_units\": \"UN\", \"store\": \"http://staging.ankursethi.in/stores/4\", \"image\": \"http://staging.ankursethi.in/product_images/77\", \"merchant\": \"http://staging.ankursethi.in/merchants/3\", \"sub_category\": \"http://staging.ankursethi.in/item_sub_categories/26\", \"created_on\": \"2014-06-02T16:03:28.712Z\", \"id\": 62, \"url\": \"http://staging.ankursethi.in/inventory_items/62\"}, \"order\": \"http://staging.ankursethi.in/orders/41\", \"quantity\": 1, \"created_on\": \"2014-06-26T08:43:33.845Z\", \"id\": 51, \"url\": \"http://staging.ankursethi.in/item_orders/51\"}], \"created_on\": \"2014-06-26T08:43:33.812Z\", \"coupon_code\": \"abx\", \"id\": 41, \"url\": \"http://staging.ankursethi.in/orders/41\"}]}";
		JSONObject jsonObj = new JSONObject(jsonString);
		JSONArray jsonArray = jsonObj.getJSONArray(OrdritJsonKeys.TAG_RESULTS);
		
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject itemOrdersJsonObj = jsonArray.getJSONObject(i);
			
			if(itemOrdersJsonObj.getString(OrdritJsonKeys.TAG_STATUS).equalsIgnoreCase(OrdritJsonKeys.TAG_PENDING)){
				Order order= new Order();
				order.setId(itemOrdersJsonObj.getString(OrdritJsonKeys.TAG_ID));
				order.setStatus(OrdritJsonKeys.TAG_PENDING);
				order.setCreationDate(itemOrdersJsonObj.getString(OrdritJsonKeys.TAG_CREATED_ON));
				JSONArray itemOrdersJSONArray = itemOrdersJsonObj.getJSONArray(OrdritJsonKeys.TAG_ITEM_ORDERS);
				List<Item> itemsInOrder= new ArrayList<Item>();
				for (int j = 0; j < itemOrdersJSONArray.length(); j++) {
					JSONObject itemJsonObj = itemOrdersJSONArray.getJSONObject(j);
					Item item = new Item();
					item.setItemQuantity(itemJsonObj.getString(OrdritJsonKeys.TAG_QUANTITY));
					item.setName(itemJsonObj.getJSONObject(OrdritJsonKeys.TAG_ITEM).getString(OrdritJsonKeys.TAG_NAME));
					item.setPricePerUnit(itemJsonObj.getJSONObject(OrdritJsonKeys.TAG_ITEM).getString(OrdritJsonKeys.TAG_PRICE));
					itemsInOrder.add(item);
				}
				order.setItemsInOrder(itemsInOrder);
				orders.add(order);
			}
			
			
		}
		}catch(Exception e){
			
		}
		return orders;
	}
	
	
	
	//Get previous orders for showing in Previous Orders
		public static List<Order> getPreviousOrders(String jsonString) {
			List<Order> orders= new ArrayList<Order>();
			try{
			//jsonString="{\"count\": 39, \"next\": null, \"previous\": null, \"results\": [{\"customer\": {\"email\": \"kishansahu87@gmail.com\", \"role\": \"CST\", \"first_name\": \"first\", \"last_name\": \"last\", \"date_joined\": \"2014-05-20T07:58:44.602Z\", \"last_login\": \"2014-05-20T07:58:44.602Z\", \"phone_number\": \"6372842999\", \"gcm_registration_id\": \"\", \"id\": 8, \"url\": \"http://staging.ankursethi.in/users/8\"}, \"customer_address\": {\"user\": \"http://staging.ankursethi.in/users/8\", \"street_address\": \"B23, Baker Street\", \"city\": \"http://staging.ankursethi.in/cities/1\", \"state\": {\"name\": \"Haryana\", \"created_on\": \"2014-04-22T11:31:07.235Z\", \"id\": 1, \"url\": \"http://staging.ankursethi.in/states/1\"}, \"pin_code\": \"110035\", \"created_on\": \"2014-06-25T19:11:19.965Z\", \"id\": 16, \"url\": \"http://staging.ankursethi.in/user_addresses/16\"}, \"store\": {\"name\": \"Webchutney Store\", \"merchant\": {\"name\": \"nishantv.pandey@gmail.com\", \"user\": \"http://staging.ankursethi.in/users/3\", \"stores\": [\"http://staging.ankursethi.in/stores/4\"], \"created_on\": \"2014-04-29T08:45:13.096Z\", \"id\": 3, \"url\": \"http://staging.ankursethi.in/merchants/3\"}, \"address\": {\"street_address\": \"88, Bin Laden Avenue , Karachi\", \"city\": \"http://staging.ankursethi.in/cities/1\", \"state\": \"http://staging.ankursethi.in/states/1\", \"pin_code\": \"12008\", \"created_on\": \"2014-05-05T17:45:28.660Z\", \"id\": 2, \"url\": \"http://staging.ankursethi.in/store_addresses/2\"}, \"location\": \"POINT (77.0835639354858131 28.4985138838636587)\", \"opens_at\": \"09:00:00\", \"closes_at\": \"17:00:00\", \"minimum_order\": \"250.00\", \"estimated_delivery_time\": 60, \"sub_category\": null, \"created_on\": \"2014-05-05T17:45:29.001Z\", \"id\": 4, \"url\": \"http://staging.ankursethi.in/stores/4\", \"phone_number_1\": \"9958746143\", \"phone_number_2\": \"98653241\"}, \"status\": \"PEN\", \"merchant\": {\"name\": \"nishantv.pandey@gmail.com\", \"user\": \"http://staging.ankursethi.in/users/3\", \"stores\": [\"http://staging.ankursethi.in/stores/4\"], \"created_on\": \"2014-04-29T08:45:13.096Z\", \"id\": 3, \"url\": \"http://staging.ankursethi.in/merchants/3\"}, \"item_orders\": [{\"item\": {\"name\": \"Butter Spread \", \"description\": \"butter-spread-25-200g\", \"price\": \"25.00\", \"price_units\": \"UN\", \"store\": \"http://staging.ankursethi.in/stores/4\", \"image\": \"http://staging.ankursethi.in/product_images/42\", \"merchant\": \"http://staging.ankursethi.in/merchants/3\", \"sub_category\": \"http://staging.ankursethi.in/item_sub_categories/22\", \"created_on\": \"2014-06-02T13:44:11.766Z\", \"id\": 32, \"url\": \"http://staging.ankursethi.in/inventory_items/32\"}, \"order\": \"http://staging.ankursethi.in/orders/41\", \"quantity\": 2, \"created_on\": \"2014-06-26T08:43:33.842Z\", \"id\": 49, \"url\": \"http://staging.ankursethi.in/item_orders/49\"}, {\"item\": {\"name\": \"Amul Gold Milk\", \"description\": \"amul-uht-milk-gold-60-1ltr\", \"price\": \"60.00\", \"price_units\": \"UN\", \"store\": \"http://staging.ankursethi.in/stores/4\", \"image\": \"http://staging.ankursethi.in/product_images/49\", \"merchant\": \"http://staging.ankursethi.in/merchants/3\", \"sub_category\": \"http://staging.ankursethi.in/item_sub_categories/24\", \"created_on\": \"2014-06-02T15:55:27.121Z\", \"id\": 53, \"url\": \"http://staging.ankursethi.in/inventory_items/53\"}, \"order\": \"http://staging.ankursethi.in/orders/41\", \"quantity\": 1, \"created_on\": \"2014-06-26T08:43:33.844Z\", \"id\": 50, \"url\": \"http://staging.ankursethi.in/item_orders/50\"}, {\"item\": {\"name\": \"Amul Rose Milk\", \"description\": \"amul-milk-rose-20-200ml\", \"price\": \"20.00\", \"price_units\": \"UN\", \"store\": \"http://staging.ankursethi.in/stores/4\", \"image\": \"http://staging.ankursethi.in/product_images/77\", \"merchant\": \"http://staging.ankursethi.in/merchants/3\", \"sub_category\": \"http://staging.ankursethi.in/item_sub_categories/26\", \"created_on\": \"2014-06-02T16:03:28.712Z\", \"id\": 62, \"url\": \"http://staging.ankursethi.in/inventory_items/62\"}, \"order\": \"http://staging.ankursethi.in/orders/41\", \"quantity\": 1, \"created_on\": \"2014-06-26T08:43:33.845Z\", \"id\": 51, \"url\": \"http://staging.ankursethi.in/item_orders/51\"}], \"created_on\": \"2014-06-26T08:43:33.812Z\", \"coupon_code\": \"abx\", \"id\": 41, \"url\": \"http://staging.ankursethi.in/orders/41\"}, {\"customer\": {\"email\": \"kishansahu87@gmail.com\", \"role\": \"CST\", \"first_name\": \"first\", \"last_name\": \"last\", \"date_joined\": \"2014-05-20T07:58:44.602Z\", \"last_login\": \"2014-05-20T07:58:44.602Z\", \"phone_number\": \"6372842999\", \"gcm_registration_id\": \"\", \"id\": 8, \"url\": \"http://staging.ankursethi.in/users/8\"}, \"customer_address\": {\"user\": \"http://staging.ankursethi.in/users/8\", \"street_address\": \"B23, Baker Street\", \"city\": \"http://staging.ankursethi.in/cities/1\", \"state\": {\"name\": \"Haryana\", \"created_on\": \"2014-04-22T11:31:07.235Z\", \"id\": 1, \"url\": \"http://staging.ankursethi.in/states/1\"}, \"pin_code\": \"110035\", \"created_on\": \"2014-06-25T19:11:19.965Z\", \"id\": 16, \"url\": \"http://staging.ankursethi.in/user_addresses/16\"}, \"store\": {\"name\": \"Webchutney Store\", \"merchant\": {\"name\": \"nishantv.pandey@gmail.com\", \"user\": \"http://staging.ankursethi.in/users/3\", \"stores\": [\"http://staging.ankursethi.in/stores/4\"], \"created_on\": \"2014-04-29T08:45:13.096Z\", \"id\": 3, \"url\": \"http://staging.ankursethi.in/merchants/3\"}, \"address\": {\"street_address\": \"88, Bin Laden Avenue , Karachi\", \"city\": \"http://staging.ankursethi.in/cities/1\", \"state\": \"http://staging.ankursethi.in/states/1\", \"pin_code\": \"12008\", \"created_on\": \"2014-05-05T17:45:28.660Z\", \"id\": 2, \"url\": \"http://staging.ankursethi.in/store_addresses/2\"}, \"location\": \"POINT (77.0835639354858131 28.4985138838636587)\", \"opens_at\": \"09:00:00\", \"closes_at\": \"17:00:00\", \"minimum_order\": \"250.00\", \"estimated_delivery_time\": 60, \"sub_category\": null, \"created_on\": \"2014-05-05T17:45:29.001Z\", \"id\": 4, \"url\": \"http://staging.ankursethi.in/stores/4\", \"phone_number_1\": \"9958746143\", \"phone_number_2\": \"98653241\"}, \"status\": \"PEN\", \"merchant\": {\"name\": \"nishantv.pandey@gmail.com\", \"user\": \"http://staging.ankursethi.in/users/3\", \"stores\": [\"http://staging.ankursethi.in/stores/4\"], \"created_on\": \"2014-04-29T08:45:13.096Z\", \"id\": 3, \"url\": \"http://staging.ankursethi.in/merchants/3\"}, \"item_orders\": [{\"item\": {\"name\": \"Butter Spread \", \"description\": \"butter-spread-25-200g\", \"price\": \"25.00\", \"price_units\": \"UN\", \"store\": \"http://staging.ankursethi.in/stores/4\", \"image\": \"http://staging.ankursethi.in/product_images/42\", \"merchant\": \"http://staging.ankursethi.in/merchants/3\", \"sub_category\": \"http://staging.ankursethi.in/item_sub_categories/22\", \"created_on\": \"2014-06-02T13:44:11.766Z\", \"id\": 32, \"url\": \"http://staging.ankursethi.in/inventory_items/32\"}, \"order\": \"http://staging.ankursethi.in/orders/41\", \"quantity\": 2, \"created_on\": \"2014-06-26T08:43:33.842Z\", \"id\": 49, \"url\": \"http://staging.ankursethi.in/item_orders/49\"}, {\"item\": {\"name\": \"Amul Gold Milk\", \"description\": \"amul-uht-milk-gold-60-1ltr\", \"price\": \"60.00\", \"price_units\": \"UN\", \"store\": \"http://staging.ankursethi.in/stores/4\", \"image\": \"http://staging.ankursethi.in/product_images/49\", \"merchant\": \"http://staging.ankursethi.in/merchants/3\", \"sub_category\": \"http://staging.ankursethi.in/item_sub_categories/24\", \"created_on\": \"2014-06-02T15:55:27.121Z\", \"id\": 53, \"url\": \"http://staging.ankursethi.in/inventory_items/53\"}, \"order\": \"http://staging.ankursethi.in/orders/41\", \"quantity\": 1, \"created_on\": \"2014-06-26T08:43:33.844Z\", \"id\": 50, \"url\": \"http://staging.ankursethi.in/item_orders/50\"}, {\"item\": {\"name\": \"Amul Rose Milk\", \"description\": \"amul-milk-rose-20-200ml\", \"price\": \"20.00\", \"price_units\": \"UN\", \"store\": \"http://staging.ankursethi.in/stores/4\", \"image\": \"http://staging.ankursethi.in/product_images/77\", \"merchant\": \"http://staging.ankursethi.in/merchants/3\", \"sub_category\": \"http://staging.ankursethi.in/item_sub_categories/26\", \"created_on\": \"2014-06-02T16:03:28.712Z\", \"id\": 62, \"url\": \"http://staging.ankursethi.in/inventory_items/62\"}, \"order\": \"http://staging.ankursethi.in/orders/41\", \"quantity\": 1, \"created_on\": \"2014-06-26T08:43:33.845Z\", \"id\": 51, \"url\": \"http://staging.ankursethi.in/item_orders/51\"}], \"created_on\": \"2014-06-26T08:43:33.812Z\", \"coupon_code\": \"abx\", \"id\": 41, \"url\": \"http://staging.ankursethi.in/orders/41\"}]}";
			JSONObject jsonObj = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObj.getJSONArray(OrdritJsonKeys.TAG_RESULTS);
			int length= jsonArray.length();
			if(length >= 5){
				length=5;
			}
			
			
			for (int i = 0; i < length; i++) {
				JSONObject itemOrdersJsonObj = jsonArray.getJSONObject(i);
				
				if(!itemOrdersJsonObj.getString(OrdritJsonKeys.TAG_STATUS).equalsIgnoreCase(OrdritJsonKeys.TAG_PENDING)){
					Order order= new Order();
					order.setId(itemOrdersJsonObj.getString(OrdritJsonKeys.TAG_ID));
					order.setStatus(itemOrdersJsonObj.getString(OrdritJsonKeys.TAG_STATUS));
					order.setCreationDate(itemOrdersJsonObj.getString(OrdritJsonKeys.TAG_CREATED_ON));
					JSONArray itemOrdersJSONArray = itemOrdersJsonObj.getJSONArray(OrdritJsonKeys.TAG_ITEM_ORDERS);
					List<Item> itemsInOrder= new ArrayList<Item>();
					for (int j = 0; j < itemOrdersJSONArray.length(); j++) {
						JSONObject itemJsonObj = itemOrdersJSONArray.getJSONObject(j);
						Item item = new Item();
						item.setItemQuantity(itemJsonObj.getString(OrdritJsonKeys.TAG_QUANTITY));
						item.setName(itemJsonObj.getJSONObject(OrdritJsonKeys.TAG_ITEM).getString(OrdritJsonKeys.TAG_NAME));
						item.setPricePerUnit(itemJsonObj.getJSONObject(OrdritJsonKeys.TAG_ITEM).getString(OrdritJsonKeys.TAG_PRICE));
						itemsInOrder.add(item);
					}
					order.setItemsInOrder(itemsInOrder);
					orders.add(order);
				}
				
				
			}
			}catch(Exception e){
				
			}
			return orders;
		}
}







