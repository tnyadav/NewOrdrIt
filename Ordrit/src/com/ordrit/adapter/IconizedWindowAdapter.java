package com.ordrit.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;
import com.ordrit.R;

public class IconizedWindowAdapter implements InfoWindowAdapter {
	LayoutInflater inflater = null;

	public IconizedWindowAdapter(LayoutInflater inflater) {
		this.inflater = inflater;
	}

	@Override
	public View getInfoWindow(Marker marker) {
		return (null);
	}

	@Override
	public View getInfoContents(Marker marker) {

		View popup = inflater.inflate(R.layout.custome_info_window, null);
		final ImageView image= (ImageView) popup.findViewById(R.id.image);
		TextView textView = (TextView) popup.findViewById(R.id.textView_detail);
		textView.setText(marker.getTitle());
		textView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				
			}
		});

		return popup;
	}
}