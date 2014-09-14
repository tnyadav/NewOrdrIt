package com.ordrit.model;

public class MenuItem {
	
	private int nodeLevel;
	private String title;
	private int icon;
	private MenuData menuData;
	
	public MenuItem(){
		
	}

	public MenuItem(int nodeLevel, String title, int icon, MenuData menuData) {
		super();
		this.nodeLevel = nodeLevel;
		this.title = title;
		this.icon = icon;
		this.menuData = menuData;
	}


	public String getTitle() {
		return title;
	}


	public int getIcon() {
		return icon;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setIcon(int icon) {
		this.icon = icon;
	}



	public int getNodeLevel() {
		return nodeLevel;
	}


	public void setNodeLevel(int nodeLevel) {
		this.nodeLevel = nodeLevel;
	}


	public MenuData getMenuData() {
		return menuData;
	}

	public void setMenuData(MenuData menuData) {
		this.menuData = menuData;
	}


	
	
	
}
