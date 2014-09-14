package com.ordrit.newmodel;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Category {
	@Expose
	private String name;
	@Expose
	private String created_on;
	@Expose
	private String id;
	@Expose
	private String url;
	@Expose
	private List<SubCategory> sub_categories = new ArrayList<SubCategory>();

	public String getName() {
	return name;
	}

	public void setName(String name) {
	this.name = name;
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

	public List<SubCategory> getSub_categories() {
	return sub_categories;
	}

	public void setSub_categories(List<SubCategory> sub_categories) {
	this.sub_categories = sub_categories;
	}

}
