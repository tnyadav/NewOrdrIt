package com.ordrit.newmodel;

import com.google.gson.annotations.Expose;

public class SubCategory {

	@Expose
	private String name;
	@Expose
	private String category;
	@Expose
	private String created_on;
	@Expose
	private String id;
	@Expose
	private String url;

	public String getName() {
	return name;
	}

	public void setName(String name) {
	this.name = name;
	}

	public String getCategory() {
	return category;
	}

	public void setCategory(String category) {
	this.category = category;
	}

	public String getCreated_on() {
	return created_on;
	}

	public void setCreated_on(String created_on) {
	this.created_on = created_on;
	}

	public String getId() {
	return id;
	}

	public void setId(String id) {
	this.id = id;
	}

	public String getUrl() {
	return url;
	}

	public void setUrl(String url) {
	this.url = url;
	}
	
}
