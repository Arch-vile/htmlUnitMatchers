package foo;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Description;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.moonillusions.htmlUnitMatchers.StringUtils;

public abstract class MyCombiningTypeSafeMatcher<T> extends MyTypeSafeMatcher<T>{

	private MatcherPair failedMatcher;
	private List<MatcherPair> matchers = new ArrayList<MatcherPair>();
	
	@Override
	public void describe(Description desc) {
		desc.appendText("Matches all:");
		
	}
	
	@Override
	protected void mismatch(T item, Description mismatchDescription) {
		mismatchDescription.appendText("Failed on matcher: ");
	}
	
	@Override
	protected void describeMismatchSafely(T item, Description mismatchDescription) {
		super.describeMismatchSafely(item, mismatchDescription);
		this.failedMatcher.getMatcher().describeMismatchSafely(this.failedMatcher.getExtractor().extract(item), mismatchDescription, 1);
	}
	
	@Override
	public void describeTo(Description arg0) {
		super.describeTo(arg0);
		for(MatcherPair pair : this.matchers) {
			pair.getMatcher().describeTo(arg0,1);
		}
	}
	
	@Override
	protected boolean match(T arg0) {
		//this.matchers = createMatcherPairs(arg0);
		for(MatcherPair pair : this.matchers) {
			if(!pair.isMatch(arg0)) {
				this.failedMatcher = pair;
				return false;
			}
		}
		
		return true;
	}
	
	protected void addMatcher(MatcherPair pair) {
		this.matchers.add(pair);
	}
	
	//protected abstract List<MatcherPair> createMatcherPairs(T arg0);
	
}
