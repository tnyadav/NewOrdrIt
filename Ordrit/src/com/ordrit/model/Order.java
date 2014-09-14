package com.ordrit.model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String status;
	private String creationDate;
	private List<Item> itemsInOrder;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", status=" + status + ", creationDate="
				+ creationDate + ", itemsInOrder=" + itemsInOrder
				+ ", getId()=" + getId() + ", getStatus()=" + getStatus()
				+ ", getCreationDate()=" + getCreationDate()
				+ ", getItemsInOrder()=" + getItemsInOrder() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public List<Item> getItemsInOrder() {
		return itemsInOrder;
	}
	public void setItemsInOrder(List<Item> itemsInOrder) {
		this.itemsInOrder = itemsInOrder;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
