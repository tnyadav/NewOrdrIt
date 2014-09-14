package com.ordrit.newmodel;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class StoreDetail {

	@Expose
	private List<Category> sub_categories = new ArrayList<Category>();

	public List<Category> getSub_categories() {
		return sub_categories;
	}

	public void setSub_categories(List<Category> sub_categories) {
		this.sub_categories = sub_categories;
	}

	
}
