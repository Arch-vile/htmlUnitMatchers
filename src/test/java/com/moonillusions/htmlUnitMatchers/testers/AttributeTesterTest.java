package com.moonillusions.htmlUnitMatchers.testers;


import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.net.MalformedURLException;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.moonillusions.htmlUnitMatchers.TestUtils;

import static org.hamcrest.Matchers.equalTo;

import static com.moonillusions.htmlUnitMatchers.AsText.asText;;

public class AttributeTesterTest {
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testMatch() throws IOException {
        assertThat(TestUtils.createDomNode("<span>I am the text</span>"), asText("I am the text"));
	}
	
	@Test
	public void testMismatch() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		Matcher<DomNode> asText = asText("I am the text2");
		assertThat(asText.matches(TestUtils.createDomNode("<span>I am the text</span>")), equalTo(false));
	}
	
	@Test
	public void testMismatchEmpty() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		Matcher<DomNode> asText = asText("");
		assertThat(asText.matches(TestUtils.createDomNode("<span>I am the text</span>")), equalTo(false));
	}
	
	@Test
	public void testMatchEmpty() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		assertThat(TestUtils.createDomNode("<span></span>"),asText(""));
	}
	
	@Test
	public void testMismatchNull() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		Matcher<DomNode> asText = asText(null);
		assertThat(asText.matches(TestUtils.createDomNode("<span></span>")), equalTo(false));
	}

	@Test
	public void testMismatchDescription() {
		Matcher<DomNode> asText = asText("No match");
		Description description = new StringDescription();
		asText.describeTo(description);
		assertThat(description.toString(), equalTo("Expected DomNode with asText() value 'No match'"));
	}
	
}
