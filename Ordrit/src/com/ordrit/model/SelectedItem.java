package com.ordrit.model;

public class SelectedItem {
	private Item item;
	private String quantity;
	
	
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "SelectedItem [item=" + item + ", quantity=" + quantity + "]";
	}
}
