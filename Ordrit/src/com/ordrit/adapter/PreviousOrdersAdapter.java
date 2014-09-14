package com.ordrit.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ordrit.R;
import com.ordrit.model.Item;
import com.ordrit.model.Order;

public class PreviousOrdersAdapter extends ArrayAdapter<Order> {

	private List<Order> pendingOrdersList;
	private int resourceId;
	private LayoutInflater inflater;

	private Context context;

	public PreviousOrdersAdapter(Context context, int resourceId,
			List<Order> pendingOrdersList) {
		super(context, resourceId, pendingOrdersList);
		this.context = context;
		this.pendingOrdersList = pendingOrdersList;
		this.resourceId = resourceId;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	private class ViewHolder {
		TextView orderId;
		TextView orderCreationDate;
		LinearLayout itemDetailsSubContainer;
		LinearLayout itemDetailsContainer;
		LinearLayout orderStatusButton;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final Order order = pendingOrdersList.get(position);
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(resourceId, parent, false);
			holder.orderId = (TextView) convertView.findViewById(R.id.orderId);
			holder.orderCreationDate = (TextView) convertView.findViewById(R.id.orderCreationDate);
			holder.itemDetailsSubContainer = (LinearLayout) convertView.findViewById(R.id.itemDetailsSubContainer);
			holder.itemDetailsContainer = (LinearLayout) convertView.findViewById(R.id.itemDetailsContainer);
			holder.orderStatusButton = (LinearLayout) convertView.findViewById(R.id.orderStatusButton);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.orderStatusButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(holder.itemDetailsContainer.getVisibility() == View.VISIBLE){
					holder.itemDetailsContainer.setVisibility(View.GONE);
				}else{
					holder.itemDetailsContainer.setVisibility(View.VISIBLE);
				}
				
			}
		});
		holder.orderId.setText(order.getId());
		holder.orderCreationDate.setText(order.getCreationDate());
		for(Item item: order.getItemsInOrder()){
	    View itemSubDetail=	inflater.inflate(R.layout.item_sub_detail, null);
	    TextView itemName= (TextView) itemSubDetail.findViewById(R.id.itemName);
	    itemName.setText(item.getName());
	    TextView itemQuantity= (TextView) itemSubDetail.findViewById(R.id.itemQuantity);
	    itemQuantity.setText(item.getItemQuantity());
	    TextView itemPrice= (TextView) itemSubDetail.findViewById(R.id.itemPrice);
	    itemPrice.setText(item.getPricePerUnit());
	    holder.itemDetailsSubContainer.addView(itemSubDetail);
		}
		return convertView;
	}

	
}
