package com.ordrit.model;

import java.io.Serializable;

public class MenuData implements Serializable {

	 public enum MenuType implements Serializable {
	        Menu,
	        Store
	    }
	private int position;
	private MenuType menuType;
	private String store;
	private String category;
	private String subCategory;

	public MenuData(int position, MenuType menuType, String store,
			String category, String subCategory) {
		super();
		this.position = position;
		this.menuType = menuType;
		this.store = store;
		this.category = category;
		this.subCategory = subCategory;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public MenuType getMenuType() {
		return menuType;
	}
	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	@Override
	public String toString() {
		return "MenuData [position=" + position + ", menuType=" + menuType
				+ ", store=" + store + ", category=" + category
				+ ", subCategory=" + subCategory + "]";
	}
	
}
