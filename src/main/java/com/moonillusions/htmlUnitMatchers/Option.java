package com.moonillusions.htmlUnitMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Joiner;

public class Option {
	
	private List<Attribute> attributes;
	private Object value;
	
	public Option(Object value, Attribute...attributes){
		this.value = value;
		this.attributes = Arrays.asList(attributes);
	}
	
	public Option(Object value) {
		this.value = value;
		this.attributes = new ArrayList<Attribute>();
	}
	
	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}



	public Option withAttr(String name, Object value) {
		Attribute attr = new Attribute(name, value);
		this.attributes.add(attr);
		return this;
	}

	
	@Override
	public String toString() {
		return "<option " + Joiner.on(", ").join(attributes) + ">" + getValue() + "</option>";
	}
	
}
