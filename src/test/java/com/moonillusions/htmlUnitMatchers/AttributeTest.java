package com.moonillusions.htmlUnitMatchers;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlElement;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.equalTo;

public class AttributeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void createFromNode() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlElement span = TestUtils.createDomNode("<span attr1=\"1\">foo</span>");
		Node node = span.getAttributes().getNamedItem("attr1");
		Attribute attribute = new Attribute(node);
		assertThat(attribute.getName(), equalTo("attr1"));
		assertThat(attribute.getValue().toString(), equalTo("1"));
	}
	
	
	@Test
	public void equals() {
		Attribute attr1 = new Attribute("attr1",1);
		Attribute attr2 = new Attribute("attr1",1);
		assertThat(attr1, equalTo(attr2));
	}

}
