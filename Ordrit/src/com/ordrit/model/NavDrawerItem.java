package com.ordrit.model;

public class NavDrawerItem {
	
	private String title;
	private int icon;
	private String id ;
	
	public NavDrawerItem(){}

	
	public NavDrawerItem(String title, int icon,String id){
		this.title = title;
		this.icon = icon;
		this.id=id;
		
	}


	public String getTitle() {
		return title;
	}


	public int getIcon() {
		return icon;
	}


	public String getId() {
		return id;
	}


	



	@Override
	public String toString() {
		return "NavDrawerItem [title=" + title + ", icon=" + icon + ", id="
				+ id + "]";
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setIcon(int icon) {
		this.icon = icon;
	}


	public void setId(String id) {
		this.id = id;
	}


	
	
	
}
