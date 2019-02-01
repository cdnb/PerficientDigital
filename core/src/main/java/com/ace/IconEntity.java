package com.ace;

public class IconEntity {

	private String iconClass;
	private String iconLink;
	public String getIconClass() {
		return iconClass;
	}
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}
	public String getIconLink() {
		return iconLink;
	}
	public void setIconLink(String iconLink) {
		this.iconLink = iconLink;
	}
	public IconEntity(String iconClass, String iconLink) {
		super();
		this.iconClass = iconClass;
		this.iconLink = iconLink;
	}
	
	
}
