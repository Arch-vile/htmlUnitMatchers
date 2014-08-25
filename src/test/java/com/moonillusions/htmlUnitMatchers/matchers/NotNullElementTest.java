package com.moonillusions.htmlUnitMatchers.matchers;

import static com.moonillusions.htmlUnitMatchers.matchers.NotNullElement.notNullElement;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.moonillusions.htmlUnitMatchers.TestUtils;

public class NotNullElementTest {

	@Test
	public void match_non_null_element() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		assertThat(TestUtils.createDomNode("<span>I am text</span>"),
				notNullElement(""));
	}

	@Test
	public void mismatch_null_element() {
		assertThat(null, not(notNullElement("")));
	}

	@Test
	public void describe() {
		TestUtils.assertDescribeTo(notNullElement("HtmlSelect"),
				"given HtmlSelect element");
	}

}
