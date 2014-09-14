package com.ordrit.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ordrit.R;
import com.ordrit.activity.UILApplication;
import com.ordrit.model.Item;
import com.ordrit.model.Order;

public class OrderStatusAdapter extends ArrayAdapter<Order> {

	private List<Order> pendingOrdersList;
	private int resourceId;
	private LayoutInflater inflater;

	private Context context;

	public OrderStatusAdapter(Context context, int resourceId,
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
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final Order order = pendingOrdersList.get(position);
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(resourceId, parent, false);
			holder.orderId = (TextView) convertView.findViewById(R.id.orderId);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.orderId.setText(order.getId());
		return convertView;
	}

	
}
