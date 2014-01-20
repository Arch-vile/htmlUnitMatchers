package com.moonillusions.htmlUnitMatchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.hamcrest.TypeSafeMatcher;

import com.gargoylesoftware.htmlunit.html.DomNode;

public abstract class BaseElementMatcher<T extends DomNode> extends TypeSafeMatcher<T> {
	
	private String mismatchError = "";
	private Matcher failingMatcher;
	private Object failingItem;
	
	BaseTester tester;
	
	
	public Object getFailingItem() {
		return failingItem;
	}

	public void setFailingItem(Object failingItem) {
		this.failingItem = failingItem;
	}

	protected Matcher getFailingMatcher() {
		return failingMatcher;
	}

	protected void setFailingMatcher(Matcher failingMatcher) {
		this.failingMatcher = failingMatcher;
	}

	protected void setMismatchMessage(String messsage) {
		this.mismatchError = messsage;
	}
	
	public void describeTo(Description description) {
		failingMatcher.describeTo(getDescribeTo(description).appendText("<"));
		description.appendText(">");
	}
	
	
	protected abstract Description getDescribeTo(Description description);
	
	
	@Override
	protected void describeMismatchSafely(T item, Description mismatchDescription) {
		//failingMatcher.describeMismatch(getFailingItem(), mismatchDescription);
		//mismatchDescription.appendText(mismatchError.isEmpty() ? "" : " " + mismatchError).appendText(" in ").appendText(StringUtils.print(item));
		mismatchDescription.appendText(tester.getMismatch());
	}
	
	protected boolean applyMatcher(Object actual, Matcher matcher) {
		if(!matcher.matches(actual)) {
			setFailingMatcher(matcher);
			setFailingItem(actual);
			return false;
		}
		return true;
	}
	

}
