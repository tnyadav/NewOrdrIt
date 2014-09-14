package com.ordrit.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ordrit.R;
import com.ordrit.activity.UILApplication;
import com.ordrit.model.Item;
import com.ordrit.model.SelectedItem;
import com.ordrit.util.FragmentConstant;
import com.ordrit.util.OrdritConstants;

public class ItemDetailFragment extends BaseFragment {
	
	private static final String tag="ItemListFragment";
	private View itemDetailFragment;
	private Button itemDetailBack,buttonAddToBagOrder;
	private String storeId;
	TextView textItemName,itemPrice;
	ImageView imageViewItemImage;
    private Item item;
    private UILApplication uilApplication;
    private	List<SelectedItem> selectedItemList;
    private int qaintity=1;
    
    // number picker
    private Button upButton;
    private Button downButton;
    private EditText editText;
    private final int uprange = 100;
    private final int downrange = 1;
    private int values = 1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		itemDetailFragment=inflater.inflate(R.layout.fragment_item_details, container,false);
		Bundle bundle=getArguments();
		item = (Item)bundle.getSerializable(OrdritConstants.ITEM);
		storeId=bundle.getString(OrdritConstants.STORE_ID);
		setupUiComponent();
		return itemDetailFragment;
	}
	@Override
	void setupUiComponent() {

		uilApplication =(UILApplication) dashboardActivity.getApplication();
		selectedItemList=uilApplication.getSelectedItemList();
		
		itemDetailBack=(Button)itemDetailFragment.findViewById(R.id.itemDetailBack); 
		itemDetailBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dashboardActivity.popFragment(FragmentConstant.ITEM_DETAIL_FRAGMENT);
			}
		});
		buttonAddToBagOrder=(Button)itemDetailFragment.findViewById(R.id.buttonAddToBagOrder); 
		buttonAddToBagOrder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				SelectedItem selectedItem= new SelectedItem();
				selectedItem.setItem(item);
				selectedItem.setQuantity(String.valueOf(editText.getText().toString()));
				String previousStoreId=uilApplication.getStoreId();
				if (selectedItemList==null) {
					
					selectedItemList=new ArrayList<SelectedItem>();
				
			}
			
				if (previousStoreId!=null) {
					
					if (storeId.equals(previousStoreId)) {
						int i=searchItem(selectedItemList, item);	
						if (i!=-1) {
							SelectedItem tempSelectedItem=selectedItemList.get(i);
							tempSelectedItem.setQuantity(selectedItem.getQuantity());
							dashboardActivity.popFragment(FragmentConstant.ITEM_DETAIL_FRAGMENT);
							Toast.makeText(dashboardActivity, "Item successfully added to the cart", Toast.LENGTH_SHORT).show();
							dashboardActivity.setSelectedStoreId(storeId);
						}else {
							selectedItemList.add(selectedItem);
							uilApplication.setSelectedItemList(selectedItemList);
							uilApplication.setStoreId(storeId);
							dashboardActivity.popFragment(FragmentConstant.ITEM_DETAIL_FRAGMENT);
							Toast.makeText(dashboardActivity, "Item successfully added to the cart", Toast.LENGTH_SHORT).show();
							dashboardActivity.setSelectedStoreId(storeId);
						}
						
					} else {
						Toast.makeText(dashboardActivity, "You have a item list", Toast.LENGTH_SHORT).show();
						
					}
					
					
						
				}else {
					selectedItemList.add(selectedItem);
					uilApplication.setSelectedItemList(selectedItemList);
					uilApplication.setStoreId(storeId);
					dashboardActivity.popFragment(FragmentConstant.ITEM_DETAIL_FRAGMENT);
					dashboardActivity.setSelectedStoreId(storeId);
					Toast.makeText(dashboardActivity, "Item successfully added to the cart", Toast.LENGTH_SHORT).show();
				}
				
				
				
				
			}
		});
       
		textItemName=(TextView)itemDetailFragment.findViewById(R.id.textItemName); 
		textItemName.setText(item.getName());
		itemPrice=(TextView)itemDetailFragment.findViewById(R.id.itemPrice); 
		itemPrice.setText(item.getPricePerUnit());
		imageViewItemImage=(ImageView)itemDetailFragment.findViewById(R.id.imageViewItemImage);
		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.displayImage(item.getImageURL(), imageViewItemImage);
	/*	NumberPicker numberPicker = (NumberPicker)itemDetailFragment.findViewById(R.id.numberPicker);
	        numberPicker.setMaxValue(100);    
	        numberPicker.setMinValue(1);        
	        numberPicker.setWrapSelectorWheel(true);
	        numberPicker.setOnValueChangedListener( new NumberPicker.
	            OnValueChangeListener() {
	            @Override
	            public void onValueChange(NumberPicker picker, int
	                oldVal, int newVal) {
	            	qaintity=newVal;
	            }
	        });*/
	        nunberPicker();
	        dashboardActivity.checkCartItems(itemDetailFragment);
	}
private int searchItem(List<SelectedItem> selectedItemList,Item item){
	int i =-1;
	if (selectedItemList!=null&&selectedItemList.size()>0) {
		for (int j = 0; j < selectedItemList.size(); j++) {
			SelectedItem selectedItem=selectedItemList.get(j);
			Item temp=selectedItem.getItem();
			if (item.getId().equals(temp.getId())) {
				i=j;
				break;
			}
		}
	}

	return i;
	
}

	private void nunberPicker() {

		upButton = (Button) itemDetailFragment.findViewById(R.id.upButton);
		downButton = (Button) itemDetailFragment.findViewById(R.id.downButton);
		editText = (EditText) itemDetailFragment
				.findViewById(R.id.numberEditText);

		upButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				if (values >= downrange && values <= uprange)
					values++;
				if (values > uprange)
					values = downrange;
				editText.setText("" + values);

			}
		});

		downButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				if (values >= downrange && values <= uprange)
					values--;

				if (values < downrange)
					values = uprange;

				editText.setText(values + "");
			}
		});

	}
}
