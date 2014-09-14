package com.ordrit.model;


public class User {
	
	private String emailId;
	private String firstName;
	private String lastName;
	private String role;
	private String joinDate;
	private String lastLoginDate;
	private String id;
	private String phoneNumber;
	private String gcmRegistrationId;
	private String url;
	private Address address;
	private String token;
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [emailId=" + emailId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", role=" + role + ", joinDate="
				+ joinDate + ", lastLoginDate=" + lastLoginDate + ", id=" + id
				+ ", phoneNumber=" + phoneNumber + ", gcmRegistrationId="
				+ gcmRegistrationId + ", url=" + url + "]";
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getGcmRegistrationId() {
		return gcmRegistrationId;
	}
	public void setGcmRegistrationId(String gcmRegistrationId) {
		this.gcmRegistrationId = gcmRegistrationId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
