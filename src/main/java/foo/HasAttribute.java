package foo;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.moonillusions.htmlUnitMatchers.Attribute;

public class HasAttribute extends MyTypeSafeMatcher<DomNode> {

	private Attribute attribute;
	private int index;
	private Node failedAttribute;
	
	private String failReason;
	
	public HasAttribute(Attribute attribute, int index) {
		this.attribute = attribute;
		this.index = index;
	}

	@Override
	protected void describe(Description arg0) {
		arg0.appendText("Has attribute on index " + this.index + ": " + attribute);
	}
	
	@Override
	protected void mismatch(DomNode item, Description mismatchDescription) {
		mismatchDescription.appendText("On " + item + " on attribute " + failedAttribute + " did not match expected " + this.failReason );
	}
	
	@Override
	protected boolean match(DomNode element) {
		
		Node attr = element.getAttributes().item(this.index);
		
		if(!matchesName(attr)) {
			this.failReason = "name=" + this.attribute.getName();
			this.failedAttribute = attr;
			return false;
		}
		
		if(!matchesValue(attr)) {
			this.failReason = "value=" + this.attribute.getValue();
			this.failedAttribute = attr;
			return false;
		}
		return true;
	}

	private boolean matchesName(Node node) {
		return node.getNodeName().equals(this.attribute.getName());
	}

	private boolean matchesValue(Node node) {
		return node.getNodeValue().equals(this.attribute.getValue());
	}
	
	@Factory
	public static HasAttribute hasAttribute(Attribute attribute, int index){
		return new HasAttribute(attribute, index);
	}

	

}
