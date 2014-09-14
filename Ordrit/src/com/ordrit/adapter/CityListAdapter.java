package com.ordrit.adapter;

import java.util.ArrayList;
import java.util.List;

import com.ordrit.R;
import com.ordrit.model.City;
import com.ordrit.model.State;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CityListAdapter extends ArrayAdapter<City> {

	private Context context;
	private List<City> list;

	public CityListAdapter(Context context, int textViewResourceId,
			List<City> items) {
		super(context, textViewResourceId, items);
		this.context = context;
		this.list = items;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.states_list_item, null);
		}

		City city = list.get(position);
		if (city != null) {
			// My layout has only one TextView
			TextView itemView = (TextView) view
					.findViewById(R.id.stateListItem);
			if (itemView != null) {
				// do whatever you want with your string and long
				itemView.setText(city.getName());
			}
		}

		return view;
	}

}
