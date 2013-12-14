package com.moonillusions.htmlUnitMatchers;


import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.google.common.base.Joiner;

import static org.hamcrest.Matchers.equalTo;

public class HasAttributes extends TypeSafeMatcher<DomNode>  {

	private List<Attribute> attributes;
	
	public HasAttributes() {
	}
	
	public HasAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public void describeTo(Description description) {
		description.appendText("DomNode with attributes " + Joiner.on(", ").join(attributes));
	}


	@Override
	protected boolean matchesSafely(DomNode domNode) {
		NamedNodeMap mp = domNode.getAttributes();
		
		if(mp.getLength() != this.attributes.size()) return false;
		
		for(Attribute attribute : this.attributes){
			Node node = mp.getNamedItem(attribute.getName());
			String nodeValue = node.getNodeValue();
			if(!nodeValue.equals(attribute.getValue().toString()))
				return false;
		}
		
		return true;
	}
	
	@Factory
	public static <T> Matcher<DomNode> hasAttributes(Object...attr) {
	    return new HasAttributes(extractAttributes(attr));
	}

	private static List<Attribute> extractAttributes(Object[] attr) {
		List<Attribute> list = new ArrayList<Attribute>();
		for(int i = 0; i< attr.length; i+=2){
			list.add(new Attribute(attr[i].toString(),attr[i+1]));
		}
		return list;
	}

}
