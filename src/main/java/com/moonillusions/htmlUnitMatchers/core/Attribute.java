package com.moonillusions.htmlUnitMatchers.core;

public class Attribute {

	private String name = "";
	private String value = "";

	public Attribute(String attribute) {
		if (attribute != null) {
			if (attribute.contains("=")) {
				this.name = attribute.substring(0, attribute.indexOf('='));
				this.value = attribute.substring(attribute.indexOf('=') + 1);
			} else {
				this.name = attribute;
			}
		}
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public String toString() {
		return String.format("name: [%s] value: [%s]", name, value);
	}

}
