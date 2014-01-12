package com.moonillusions.htmlUnitMatchers;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.w3c.dom.Node;

public class Attribute {

	private String name;
	private String value;
	
	public Attribute(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public Attribute(Node item) {
		this.name = item.getNodeName();
		this.value = item.getNodeValue();
	}
	
	public Attribute(String name, Object value) {
		this.name = name;
		this.value = value.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.name + "='" + this.value + "'";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Attribute)) {
			return false;
		}
		
		Attribute compareTo = (Attribute)obj;
		return new EqualsBuilder()
				.append(value, compareTo.value)
				.append(name, compareTo.name)
				.isEquals();
	}

}
