package com.ordrit.adapter;

import java.util.HashMap;
import java.util.List;

import com.ordrit.R;
import com.ordrit.model.ItemCategory;
import com.ordrit.model.ItemSubCategory;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Context context;
	private List<ItemCategory> categoriesList; // header titles
	private int itemLayoutId;
	private int groupLayoutId;

	public ExpandableListAdapter(List<ItemCategory> categoriesList,
			Context context) {

		this.itemLayoutId = R.layout.chield_item;
		this.groupLayoutId = R.layout.list_group;
		this.categoriesList = categoriesList;
		this.context = context;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return categoriesList.get(groupPosition).getItemSubCategory()
				.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return categoriesList.get(groupPosition).getItemSubCategory()
				.get(childPosition).hashCode();
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		View v = convertView;

		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.chield_item, parent, false);
		}

		TextView itemName = (TextView) v.findViewById(R.id.lblListItem);
		
		ItemSubCategory det = categoriesList.get(groupPosition).getItemSubCategory()
				.get(childPosition);

		itemName.setText(det.getName());
		

		return v;

	}

	@Override
	public int getChildrenCount(int groupPosition) {
		int size = categoriesList.get(groupPosition).getItemSubCategory().size();
		System.out.println("Child for group [" + groupPosition + "] is ["
				+ size + "]");
		return size;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return categoriesList.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return categoriesList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return categoriesList.get(groupPosition).hashCode();
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		View v = convertView;

		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.list_group, parent, false);
		}

		TextView groupName = (TextView) v.findViewById(R.id.lblListHeader);

		ItemCategory cat = categoriesList.get(groupPosition);

		groupName.setText(cat.getName());
			return v;

	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}