package foo;

import java.util.Arrays;
import java.util.List;

import org.apache.http.annotation.Immutable;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.moonillusions.htmlUnitMatchers.Attribute;
import com.moonillusions.htmlUnitMatchers.StringUtils;

public class IsAttribute extends MyTypeSafeMatcher<Node> {

	Attribute attribute;
	private Node failed;
	
	private String failReason;
	
	public IsAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public void describeTo(Description arg0) {
		arg0.appendText("Has attribute: " + attribute);
	}
	
	@Override
	protected boolean matchesSafely(Node node) {
		if(!matchesName(node)) {
			this.failReason = "name=" + attribute.getName();
			this.failed = node;
			return false;
		}
		
		if(!matchesValue(node)) {
			this.failReason = "value=" + attribute.getValue();
			this.failed = node;
			return false;
		}
		return true;
	}

	private boolean matchesName(Node node) {
		return node.getNodeName().equals(attribute.getName());
	}

	private boolean matchesValue(Node node) {
		return node.getNodeValue().equals(attribute.getValue());
	}

	@Override
	protected void mismatch(Node item, Description mismatchDescription) {
		mismatchDescription.appendText("On attribute: " + item + " did not match expected " + failReason);
	}

}
