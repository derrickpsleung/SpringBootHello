package com.hello.model;

public enum Role {

	ADMIN("A"),
	NORMAL_USER("NU");
	
	private String value;
	
	private Role (String val) {
		this.setValue(val);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
