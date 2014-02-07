package foo;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Description;

public abstract class MyCombiningTypeSafeMatcher<T> extends MyTypeSafeMatcher<T>{

	private MatcherPair failedMatcher;
	private List<MatcherPair> matchers = new ArrayList<MatcherPair>();

	@Override
	protected void describeMismatchSafely(T item, Description mismatchDescription) {
		this.mismatch(item, mismatchDescription);
		if(this.getFailedMatcher() != null) {
			this.failedMatcher.getMatcher().describeInnerMismatch(this.failedMatcher.getMatchee(), mismatchDescription,1);
		}
	}
	
	protected MatcherPair getFailedMatcher() {
		return this.failedMatcher;
	}
	
	protected void setFailedMatcher(MatcherPair failed) {
		this.failedMatcher = failed;
	}
	
	protected List<MatcherPair> getMatchers() {
		return this.matchers;
	}
	
	protected void addMatcher(MatcherPair pair) {
		this.matchers.add(pair);
	}
	
	
	
}
