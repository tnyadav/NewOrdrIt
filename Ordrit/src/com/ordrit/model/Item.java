package com.ordrit.model;

import java.io.Serializable;

public class Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String imageURL;
	private String pricePerUnit;
	private String unitName;
	private String itemQuantity;
	
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
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", imageURL=" + imageURL
				+ ", pricePerUnit=" + pricePerUnit + ", unitName=" + unitName
				+ "]";
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(String pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(String itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	
	
}
