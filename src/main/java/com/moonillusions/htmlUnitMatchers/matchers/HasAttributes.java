package com.moonillusions.htmlUnitMatchers.matchers;

import java.util.ArrayList;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;

import com.gargoylesoftware.htmlunit.html.DomNode;

import static com.moonillusions.htmlUnitMatchers.matchers.HasAttribute.hasAttribute;

public class HasAttributes extends AllOf<DomNode> {

	public HasAttributes(Iterable<Matcher<? super DomNode>> arg0) {
		super(arg0);
	}

	@Factory
	public static HasAttributes hasAttributes(String[]... attributes) {
		ArrayList<Matcher<? super DomNode>> matchers = new ArrayList<Matcher<? super DomNode>>();

		for (String[] attribute : attributes) {
			HasAttribute hasAttr = hasAttribute(attribute[0], attribute[1]);
			matchers.add(hasAttr);
		}

		return new HasAttributes(matchers);
	}
}
