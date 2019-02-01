package com.ace.contactUs;

public class Fields {
	private String label;
	private String checkbox;
	private String required;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRequired() {
		return required;
	}
	public void setRequired(String required) {
		this.required = required;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getCheckbox() {
		return checkbox;
	}
	public void setCheckbox(String checkbox) {
		this.checkbox = checkbox;
	}
	public Fields(String label, String checkbox, String required, String name) {
		super();
		this.label = label;
		this.checkbox = checkbox;
		this.required = null;
		this.name = null;
	}
	
	
	
}
