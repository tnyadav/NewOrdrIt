package com.ordrit.model;

public class Store {

	private String storeName;
	private String locationLatLong;
	private String id;
	private String Url;
	private String phoneNumber1;
	private String phoneNumber2;
	private String openAt;
	private String closeAt;
	private String minimumOrder;
	private User user;
	private Address address;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	
	public String getLocationLatLong() {
		return locationLatLong;
	}

	public void setLocationLatLong(String locationLatLong) {
		this.locationLatLong = locationLatLong;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getPhoneNumber1() {
		return phoneNumber1;
	}

	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

	public String getStoreName() {
		return storeName;
	}

	@Override
	public String toString() {
		return "Store [storeName=" + storeName + ", locationLatLong="
				+ locationLatLong + ", id=" + id + ", Url=" + Url
				+ ", phoneNumber1=" + phoneNumber1 + ", phoneNumber2="
				+ phoneNumber2 + ", user=" + user + ", address=" + address
				+ "]";
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getOpenAt() {
		return openAt;
	}

	public void setOpenAt(String openAt) {
		this.openAt = openAt;
	}

	public String getCloseAt() {
		return closeAt;
	}

	public void setCloseAt(String closeAt) {
		this.closeAt = closeAt;
	}

	public String getMinimumOrder() {
		return minimumOrder;
	}

	public void setMinimumOrder(String minimumOrder) {
		this.minimumOrder = minimumOrder;
	}

}
