package com.ordrit.model;

import java.io.Serializable;
import java.util.List;

public class ItemCategory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String url;
	private List<ItemSubCategory> itemSubCategory;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public List<ItemSubCategory> getItemSubCategory() {
		return itemSubCategory;
	}
	public void setItemSubCategory(List<ItemSubCategory> itemSubCategory) {
		this.itemSubCategory = itemSubCategory;
	}
	@Override
	public String toString() {
		return "ItemCategory [id=" + id + ", name=" + name + ", url=" + url
				+ ", itemSubCategory=" + itemSubCategory + "]";
	}

	
}
