package com.ordrit.model;

import javax.security.auth.PrivateCredentialPermission;

import android.R.integer;

public class MenuBagItem {
	
	public MenuBagItem(String title, int icon) {
		super();
		this.title = title;
		this.icon = icon;
	}
	private String title;
	private int icon;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	@Override
	public String toString() {
		return "NavigationItem [title=" + title + ", icon=" + icon + "]";
	}

}
