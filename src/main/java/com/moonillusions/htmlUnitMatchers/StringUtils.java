package com.moonillusions.htmlUnitMatchers;

import com.gargoylesoftware.htmlunit.html.DomNode;

public class StringUtils {

	public static String print(DomNode element) {
		StringBuffer asString  = new StringBuffer();
		asString.append(element.getClass().getSimpleName());
		asString.append("[");
		asString.append(element.asXml()
				.replaceAll("\"", "")
				.replaceAll("\n", "")
				.replaceAll(">\\s*", ">")
				.replaceAll("\\s*<", "<"));
		asString.append("]");
		return asString.toString();
	}

}
