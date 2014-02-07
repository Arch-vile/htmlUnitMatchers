package foo;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.moonillusions.htmlUnitMatchers.StringUtils;

public abstract class MyTypeSafeMatcher<T> extends TypeSafeMatcher<T> {
	
	private MatcherPair failedMatcher;
	List<MatcherPair> matchers = new ArrayList<MatcherPair>();

	public void describeInnerMismatch(T item,
			Description mismatchDescription, int matcherDepth) {
		mismatchDescription.appendText(StringUtils.indent(matcherDepth));
		this.describeMismatchSafely(item, mismatchDescription);
	}
	
	
	@Override
	protected void describeMismatchSafely(T item, Description mismatchDescription) {
		this.mismatch(item, mismatchDescription);
		if(this.getFailedMatcher() != null) {
			this.failedMatcher.getMatcher().describeInnerMismatch(this.failedMatcher.getMatchee(), mismatchDescription,1);
		}
	}
	
	
	protected abstract void mismatch(T item, Description mismatchDescription);
	
	protected MatcherPair getFailedMatcher() {
		return this.failedMatcher;
	}
	
	protected void setFailedMatcher(MatcherPair failed) {
		this.failedMatcher = failed;
	}
	
}
