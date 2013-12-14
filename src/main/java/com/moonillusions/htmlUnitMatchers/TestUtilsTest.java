package com.moonillusions.htmlUnitMatchers;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void createDomNode_withoutHTMLFrame() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlElement node = TestUtils.createDomNode("<span attr1='value1' attr2=\"value2\">text</span>");
		assertThat(node.getNodeName(), equalTo("span"));
		assertThat(node.getAttributes().getLength(), equalTo(2));
		assertThat(node.getAttributes().getNamedItem("attr2").getNodeValue(), equalTo("value2"));
	}
	
	@Test
	public void createDomNode_withHTMLFrame() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlElement node = TestUtils.createDomNode("<html><body><span attr1='value1' attr2=\"value2\">text</span></body></html>");
		assertThat(node.getNodeName(), equalTo("span"));
		assertThat(node.getAttributes().getLength(), equalTo(2));
		assertThat(node.getAttributes().getNamedItem("attr2").getNodeValue(), equalTo("value2"));
	}

}
