package com.moonillusions.htmlUnitMatchers.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.moonillusions.htmlUnitMatchers.core.ElementMatcher;

public class HasAttribute extends ElementMatcher<DomNode> {

	private DomAttr attribute;
	private int index;
	private Node failedAttribute;
	
	private String failReason;
	
	public HasAttribute(DomAttr attribute, int index) {
		this.attribute = attribute;
		this.index = index;
	}

	@Override
	protected void describe(Description arg0) {
		arg0.appendText("Has attribute on index " + this.index + ": " + attribute);
	}
	
	@Override
	protected void mismatch(DomNode item, Description mismatchDescription) {
		if(failedAttribute != null) {
			mismatchDescription.appendText(String.format("On %s on attribute %s did not match expected %s",item,this.failedAttribute,this.failReason));
		} else {
			mismatchDescription.appendText(String.format("On %s did not have attribute on index %s", item, this.index));
		}
	}
	
	@Override
	protected boolean match(DomNode element) {
		
		Node attr = element.getAttributes().item(this.index);
		if(attr == null) {
			return false;
		}
		
		if(!matchesName(attr)) {
			this.failReason = "name=" + this.attribute.getName();
			this.failedAttribute = attr;
			return false;
		}
		
		if(!matchesValue(attr)) {
			this.failReason = "value=" + this.attribute.getValue();
			this.failedAttribute = attr;
			return false;
		}
		return true;
	}

	private boolean matchesName(Node node) {
		return node.getNodeName().equals(this.attribute.getName());
	}

	private boolean matchesValue(Node node) {
		return node.getNodeValue().equals(this.attribute.getValue());
	}
	
	@Factory
	public static HasAttribute hasAttribute(DomAttr attribute, int index){
		return new HasAttribute(attribute, index);
	}

	

}