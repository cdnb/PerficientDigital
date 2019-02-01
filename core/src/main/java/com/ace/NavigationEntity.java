package com.ace;

import java.util.List;

public class NavigationEntity {
	private String lab;
	private String menuPath;
	private List<NavigationEntity> childPage;

	public String getLab() {
		return lab;
	}

	public void setLab(String lab) {
		this.lab = lab;
	}

	public String getMenuPath() {
		return menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

	public List<NavigationEntity> getChildPage() {
		return childPage;
	}

	public void setChildPage(List<NavigationEntity> childPage) {
		this.childPage = childPage;
	}

	public NavigationEntity(String lab, String menuPath) {
		super();
		this.lab = lab;
		this.menuPath = menuPath;
		this.childPage = null;
	}
	
}
