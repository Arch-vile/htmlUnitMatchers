package com.moonillusions.htmlUnitMatchers;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.MockWebConnection;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public final class TestUtils {

	public static HtmlElement createDomNode(String html) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		final MockWebConnection webConnection = new MockWebConnection();
        webConnection.setDefaultResponse(html);

        WebClient client = new WebClient();
        client.setWebConnection(webConnection);
        
        HtmlPage page = client.getPage("http://localhost");
        return (HtmlElement) page.getBody().getFirstChild();
	}
	
}
