package com.moonillusions.htmlUnitMatchers.utils;

public class AttributeUtil {

	/**
	 * 
	 * @param attribute
	 *            Attribute in format "name=value" or if no value then "name"
	 */
	public static String getName(String attribute) {
		if (attribute.contains("=")) {
			return attribute.substring(0, attribute.indexOf('='));
		} else {
			return attribute;
		}
	}

	/**
	 * 
	 * @param attribute
	 *            Attribute in format "name=value" or if no value then "name"
	 */
	public static String getValue(String attribute) {
		if (attribute.contains("=")) {
			return attribute.substring(attribute.indexOf('=') + 1);
		} else {
			return null;
		}

	}

	public static String toString(String attribute) {
		return toString(getName(attribute), getValue(attribute));
	}

	public static String toString(String attributeName, String attributeValue) {
		return String.format("name: [%s] value: [%s]", attributeName,
				attributeValue);
	}

}
