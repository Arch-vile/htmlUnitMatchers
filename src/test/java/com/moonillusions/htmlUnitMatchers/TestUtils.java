package com.moonillusions.htmlUnitMatchers;

import java.io.IOException;
import java.net.MalformedURLException;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.MockWebConnection;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public final class TestUtils {

	public static HtmlElement createDomNode(String html) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		final MockWebConnection webConnection = new MockWebConnection();
        webConnection.setDefaultResponse(html);

        WebClient client = new WebClient();
        client.setWebConnection(webConnection);
        
        HtmlPage page = client.getPage("http://localhost");
        return (HtmlElement) page.getBody().getFirstChild();
	}
	
	public static DomAttr createAttribute(String name, String value) {
		return new DomAttr(null, null, name, value, false);
	}
	
	public static DomAttr createAttribute(String name, Object value) {
		return createAttribute(name, value.toString());
	}
	
	public static void assertDescribeTo(Matcher matcher, String description) {
		Description desc = new StringDescription();
		matcher.describeTo(desc);
		assertThat(desc.toString(),
				equalTo(description));
	}
	
	public static void assertDescribeMismatch(Matcher matcher, Object value, String description) {
		Description desc = new StringDescription();
		matcher.describeMismatch(value, desc);
		assertThat(desc.toString(),
				equalTo(description));
	}
	
	
}
