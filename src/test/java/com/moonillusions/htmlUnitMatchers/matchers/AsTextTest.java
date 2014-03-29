package com.moonillusions.htmlUnitMatchers.matchers;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.moonillusions.htmlUnitMatchers.TestUtils;

import static com.moonillusions.htmlUnitMatchers.matchers.AsText.asText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class AsTextTest {

	@Test
	public void matches() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		assertThat(TestUtils.createDomNode("<span>I am text</span>"),
				asText("I am text"));

	}

	@Test
	public void matchesInnerElements() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		assertThat(
				TestUtils
						.createDomNode("<div>I am div<span>I am span</span></div>"),
				asText("I am divI am span"));

	}

	@Test
	public void mismatchIfTextDiffer() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		assertThat(TestUtils.createDomNode("<span>Text here</span>"),
				not(asText("I am text")));
	}

	@Test
	public void mismatchIfNoText() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		assertThat(TestUtils.createDomNode("<span></span>"),
				not(asText("Should be some text")));
	}

	@Test
	public void mismatchIfText() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		assertThat(TestUtils.createDomNode("<span>Should be no text</span>"),
				not(asText("")));
	}

	@Test
	public void describe() {
		TestUtils.assertDescribeTo(asText("I am text"),
				"has as text: [I am text]");
	}

	@Test
	public void mismatchDescription() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		TestUtils
				.assertDescribeMismatch(asText("I am text"),
						TestUtils.createDomNode("<span>Some text</span>"),
						"did not match [Some text] on HtmlSpan[<span>Some text</span>]");
	}
}
