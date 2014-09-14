package com.ordrit.activity;


import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;

import pl.polidea.treeview.InMemoryTreeStateManager;
import pl.polidea.treeview.TreeBuilder;
import pl.polidea.treeview.TreeStateManager;
import pl.polidea.treeview.TreeViewList;
import pl.polidea.treeview.demo.FancyColouredVariousSizesAdapter;
import pl.polidea.treeview.demo.TreeViewListDemo;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ordrit.R;
import com.ordrit.adapter.SeparatedListAdapter;
import com.ordrit.database.OrdrItdataBaseHelper;
import com.ordrit.fragment.HelpFragment;
import com.ordrit.fragment.ItemListFragment;
import com.ordrit.fragment.ManageUserInfoFragment;
import com.ordrit.fragment.MapDetailFragment;
import com.ordrit.fragment.MenuBagFragment;
import com.ordrit.fragment.OrderStatusFragment;
import com.ordrit.fragment.PreviousOrdersFragment;
import com.ordrit.model.Address;
import com.ordrit.model.City;
import com.ordrit.model.ItemCategory;
import com.ordrit.model.ItemSubCategory;
import com.ordrit.model.MenuData;
import com.ordrit.model.MenuData.MenuType;
import com.ordrit.model.MenuItem;
import com.ordrit.model.State;
import com.ordrit.model.Store;
import com.ordrit.model.User;
import com.ordrit.util.OrditJsonParser;
import com.ordrit.util.OrdritConstants;
import com.ordrit.util.OrdritJsonKeys;
import com.ordrit.util.SharedPreferencesUtil;
import com.ordrit.util.WebServiceProcessingTask;

public class DashboardActivity extends Activity {
	private Context context;
	private DrawerLayout mDrawerLayout;
	//private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
    public boolean isMenuOpen=false;
    public boolean updateListView=false;
	private SeparatedListAdapter adapter;
	private Gson gson;
	private List<State> statesList;
	private List<City> cityList;
	private User user;
	private String selectedStoreId;
	public static HashMap<Long, MenuItem> menuHash= new HashMap<Long, MenuItem>();
	private Map itemCategoryMap;
	 private TreeViewList treeView;
	
	  private enum TreeType implements Serializable {
	        SIMPLE,
	        FANCY
	    }

	   private final Set<Long> selected = new HashSet<Long>();

	    private static final String TAG = TreeViewListDemo.class.getSimpleName();
	   
	 
	     
	    private static final int LEVEL_NUMBER = 3;
	    private TreeStateManager<Long> manager = null;
	    private FancyColouredVariousSizesAdapter fancyAdapter;
	    private TreeType treeType;
	    private boolean collapsible;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        context=this;

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        treeView = (TreeViewList) findViewById(R.id.mainTreeView);
		/*mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
		updateMenu();*/

		// enabling action bar app icon and behaving it as toggle button
      
        setValueInMap();
      
        
		ActionBar actionBar =getActionBar();
		if (actionBar!=null) {
			getActionBar().hide();
		}
		
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, // nav menu toggle icon
				R.string.app_name, // nav drawer open - description for
									// accessibility
				R.string.app_name // nav drawer close - description for
									// accessibility
		) {
			public void onDrawerClosed(View view) {
				isMenuOpen=false;
				
			}

			public void onDrawerOpened(View drawerView) {
				isMenuOpen=true;
				/*if (updateListView) {
					updateMenu();
					updateListView=false;
				}*/
				
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(null, 0);
			
		}
		// setting assential data
		gson= new Gson();
		String states=SharedPreferencesUtil.getStringPreferences(context, OrdritConstants.STATES);
		Type listOfObject = new TypeToken<List<State>>(){}.getType();
		statesList = gson.fromJson(states, listOfObject);
		
		String cites=SharedPreferencesUtil.getStringPreferences(context, OrdritConstants.CITIES);
		Type listOfObject1 = new TypeToken<List<City>>(){}.getType();
		cityList = gson.fromJson(cites, listOfObject1);
		
		String strUser= SharedPreferencesUtil.getStringPreferences(context, OrdritConstants.USER);
	    user=gson.fromJson(strUser, User.class);
	   // setUser(user);
	
		
	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			//Toast.makeText(context, "position"+position+ "  id"+id, 1).show();
			MenuItem navDrawerItem=(MenuItem)menuHash.get(id);
			
			displayView(navDrawerItem, position);
		}
	}




	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(MenuItem navDrawerItem,int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		if (navDrawerItem==null) {
			fragment = new MapDetailFragment();
			commitFragment(fragment, null);
			mDrawerLayout.closeDrawer(treeView);
			return;
		}
		MenuData menuData=navDrawerItem.getMenuData();
		if (menuData.getMenuType()==MenuType.Menu) {
			switch (position) {
			case 0:
				fragment = new MapDetailFragment();
				break;
			case 1:
				fragment = new ManageUserInfoFragment();;
				break;
			case 2:
				fragment = new MenuBagFragment();
				break;
			case 3:
				fragment = new OrderStatusFragment();
				break;
			case 4:
				fragment = new PreviousOrdersFragment();
				break;
			case 5:
				fragment = new HelpFragment();
				break;
			default:
				break;
			}
		}else if(menuData.getSubCategory()!=null) {
			fragment =new ItemListFragment();
			Bundle bundle=new Bundle();
			bundle.putSerializable("data", menuData);
			fragment.setArguments(bundle);
		}
		if (fragment != null) {
			commitFragment(fragment, null);
			mDrawerLayout.closeDrawer(treeView);
			
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

public void commitFragment(Fragment fragment,String tag) {
		
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        if (tag!=null) {
        	 fragmentTransaction.addToBackStack(tag);
		}
       
		fragmentTransaction.commit();
		
	}
public void popFragment(String tag) {
	 
	 FragmentManager fragmentManager = getFragmentManager();
	 fragmentManager.popBackStack (tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
			
}
	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	public void clickMenu() {
		if (isMenuOpen) {
			mDrawerLayout.closeDrawer(treeView);
			
		}else {
			mDrawerLayout.openDrawer(treeView);
		    isMenuOpen=true;
		}
		
	}
	private void updateMenu() {
		
		    /*    adapter=null;
		        mDrawerList.setAdapter(null);
				adapter =new SeparatedListAdapter(context);
				adapter.addSection("Menu", new NavDrawerListAdapter(context, CommonUtils.getNavDrawerItem(context)));
				adapter.addSection("Stores", new NavDrawerListAdapter(context, CommonUtils.getNavDrawerItemStore(context)));
				mDrawerList.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				*/
				
	}

	public Gson getGson() {
		return gson;
	}

	public void setGson(Gson gson) {
		this.gson = gson;
	}

	public List<State> getStatesList() {
		return statesList;
	}

	public void setStatesList(List<State> statesList) {
		this.statesList = statesList;
	}

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	public User getUser() {
		if(user==null){
			String strUser= SharedPreferencesUtil.getStringPreferences(context, OrdritConstants.USER);
		    user=gson.fromJson(strUser, User.class);
		    	
		}
		return user;
	}

	public void setUser(User user) {
		SharedPreferencesUtil.saveStringPreferences(context, OrdritConstants.USER, gson.toJson(user));
		this.user = user;
		
		
	}

	 @Override
	public void onBackPressed() {
		
		 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);
	 
				// set title
				alertDialogBuilder.setTitle("Ordrit");
	 
				// set dialog message
				alertDialogBuilder
					.setMessage("Do you want to exit ?")
					.setCancelable(false)
					.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							dialog.cancel();
							finish();
						}
					  })
					.setNegativeButton("No",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked, just close
							// the dialog box and do nothing
							dialog.cancel();
						}
					});
	 
					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
	 
					// show it
					alertDialog.show();
	}
	 
	 public void checkCartItems(View fragmentView){
		 UILApplication uilApplication =(UILApplication) getApplication();
		 if(uilApplication.getSelectedItemList()!=null){
		 int itemsCount= uilApplication.getSelectedItemList().size();
		 RelativeLayout cartContainer= (RelativeLayout) fragmentView.findViewById(R.id.cartContainer);
		 if(itemsCount> 0){
			cartContainer.setVisibility(View.VISIBLE);
			TextView cartItemCount= (TextView) fragmentView.findViewById(R.id.cart_item_count);
			cartItemCount.setText(""+itemsCount);
			ImageView cartLogo= (ImageView)fragmentView.findViewById(R.id.cartLogo);
			cartLogo.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				commitFragment(new MenuBagFragment(), null)	;
					
				}
			});
		 }else{
			 cartContainer.setVisibility(View.GONE); 
		 }
		 }
	 }
	 @Override
		public boolean dispatchTouchEvent(MotionEvent event) {
			View currentView = getCurrentFocus();

			boolean retVal = super.dispatchTouchEvent(event);
			if (currentView instanceof EditText) {
				View cView = getCurrentFocus();
				int scrCoordinates[] = new int[2];
				cView.getLocationOnScreen(scrCoordinates);
				float xPos = event.getRawX() + cView.getLeft() - scrCoordinates[0];
				float yPos = event.getRawY() + cView.getTop() - scrCoordinates[1];

				if (event.getAction() == MotionEvent.ACTION_UP
						&& (xPos < cView.getLeft() || xPos >= cView.getRight()
								|| yPos < cView.getTop() || yPos > cView
								.getBottom())) {
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(getWindow().getCurrentFocus()
							.getWindowToken(), 0);
				}
			}
			return retVal;
		}

	public String getSelectedStoreId() {
		return selectedStoreId;
	}

	public void setSelectedStoreId(String selectedStoreId) {
		this.selectedStoreId = selectedStoreId;
	} 
	
	public boolean isUserProfileIncomplete(){
		boolean isUserProfileIncomplete= false;
		Gson gson = new Gson();
		String strUser = SharedPreferencesUtil.getStringPreferences(
				context, OrdritConstants.USER);
		
	   User user = gson.fromJson(strUser, User.class);
	   String mobileNo=user.getPhoneNumber();
	   Address address=user.getAddress();
	   if (mobileNo == null || mobileNo.isEmpty() || address == null) {
		   isUserProfileIncomplete= true;
	   }
	   return isUserProfileIncomplete;
	}
	   
    public void forceUserToCompleteProfile(){
    	
		//Start Force to enter User Details
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

			// set title
			alertDialogBuilder.setTitle("Ordrit");

			// set dialog message
			alertDialogBuilder
				.setMessage("Please complete your Account details before progressing. \nAfter completion select your favourite store for shopping.")
				.setCancelable(false)
				.setPositiveButton("OK",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
						commitFragment(new ManageUserInfoFragment(),null);
						
					}
				  });

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();
		//End to fill user details
	
   	 
   	
    }
	 public void setValueInMap() {
			
		 new WebServiceProcessingTask(this) {
				
				@Override
				public void preExecuteTask() {
				
				
					
				}
				
				@Override
				public void postExecuteTask() {
					  TreeType newTreeType = null;
				        boolean newCollapsible;
				        manager = new InMemoryTreeStateManager<Long>();
				        final TreeBuilder<Long> treeBuilder = new TreeBuilder<Long>(manager);
					List<ItemCategory> itemCategories=getItemCatogery(itemCategoryMap);
					
					 
					 long index=6;
						String[] navMenuTitles = context.getResources().getStringArray(
								R.array.nav_drawer_items);
						TypedArray navMenuIcons = context.getResources().obtainTypedArray(
								R.array.nav_drawer_icons);
					 for (long i = 0; i < 6; i++) {
						 menuHash.put(i,new MenuItem(0, navMenuTitles[(int) i], navMenuIcons.getResourceId((int) i, -1), new MenuData((int) i, MenuType.Menu, null, null, null)));
					}
					    OrdrItdataBaseHelper ordrItdataBaseHelper=new OrdrItdataBaseHelper(context);
						List<Store> list=ordrItdataBaseHelper.getAllAddedStore();
						List<ItemCategory> categoryList;
						List<ItemSubCategory> itemSubCategoryList;
						Iterator<Store> iterator = list.iterator();
						while (iterator.hasNext()) {
							 Store store=iterator.next();
							 
							 menuHash.put(index,new MenuItem(0, store.getStoreName(), navMenuIcons.getResourceId(0, -1),new MenuData(-1, MenuType.Menu, store.getId(), null, null)));
							 index++;
							 // get categoryList for store
							// categoryList=new ArrayList<ItemCategory>();
							 Iterator<ItemCategory> iteratorCategory = itemCategories.iterator();
								while (iteratorCategory.hasNext()) {
									ItemCategory itemCategory=iteratorCategory.next();
									 menuHash.put(index,new MenuItem(1, itemCategory.getName(), navMenuIcons.getResourceId(0, -1), new MenuData(-1, MenuType.Store, store.getId(), itemCategory.getId(), null)));
									 index++;
									 // get subCategory for category
									 itemSubCategoryList=itemCategory.getItemSubCategory();
									 Iterator<ItemSubCategory> iteratorSubCategory = itemSubCategoryList.iterator();
									 while (iteratorSubCategory.hasNext()) {
											ItemSubCategory itemSubCategory=iteratorSubCategory.next();
											 menuHash.put(index,new MenuItem(2, itemSubCategory.getName(), navMenuIcons.getResourceId(0, -1), new MenuData(-1, MenuType.Store, store.getId(), itemCategory.getId(), itemSubCategory.getId())));
											 index++;
											
											 
										}
								}
								
						}
			 		
						  Iterator it = menuHash.entrySet().iterator();
					        while (it.hasNext()) {
					            Map.Entry pairs = (Map.Entry)it.next();
					            MenuItem menuItem=(MenuItem) pairs.getValue();
					            treeBuilder.sequentiallyAddNextNode((Long) pairs.getKey(), menuItem.getNodeLevel());
					       /* for (int i = 0; i < DEMO_NODES.length; i++) {
					            treeBuilder.sequentiallyAddNextNode((long) i, DEMO_NODES[i]);*/
					        }
					       
					        Log.d(TAG, manager.toString());
					        newTreeType = TreeType.SIMPLE;
					        newCollapsible = true;
					        
					      
					       
					        fancyAdapter = new FancyColouredVariousSizesAdapter(DashboardActivity.this, selected, manager,
					                LEVEL_NUMBER);
					        treeView.setAdapter(fancyAdapter);
					        manager.collapseChildren(null);
					        treeView.setOnItemClickListener(new SlideMenuClickListener());
					       // treeView.setCollapsible(false);
				}
				
				@Override
				public void backgroundTask() {
				
					jSONString = connection.getHttpUrlConnectionForArray(
							OrdritConstants.SERVER_BASE_URL
									+ "item_categories?store=4"/*+storeId*/,
							SharedPreferencesUtil.getStringPreferences(
									DashboardActivity.this, OrdritJsonKeys.TAG_TOKEN));
				
					// Log.e(TAG,jSONString);
					try {
						 itemCategoryMap=OrditJsonParser.getItemCategoryMap(jSONString);
						  Log.e(TAG, ""+itemCategoryMap.size());
					} catch (JSONException e) {
						
						e.printStackTrace();
					}
				
					
				}
			}.execute();
		 
	
		 
	}
		private List<ItemCategory> getItemCatogery(Map<String, ItemCategory> itemCategoryMap) {
			List<ItemCategory> listItemvCategories = new ArrayList<ItemCategory>();
			
			 Iterator it = itemCategoryMap.entrySet().iterator();
			    while (it.hasNext()) {
			        Map.Entry pairs = (Map.Entry)it.next();
			        if (!listItemvCategories.contains((ItemCategory) pairs.getValue())) {
			        	 listItemvCategories.add((ItemCategory) pairs.getValue());
					}
			       
			       // it.remove(); // avoids a ConcurrentModificationException
			    }
			
			return listItemvCategories;
			
		}

		
		
}







