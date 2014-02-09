package foo;

import java.util.List;

import org.hamcrest.Description;

import com.moonillusions.htmlUnitMatchers.StringUtils;

public abstract class MyCombiningTypeSafeMatcher<T> extends MyTypeSafeMatcher<T>{

	private MatcherPair failedMatcher;
	private List<MatcherPair> matchers;
	
	@Override
	protected void describeMismatchSafely(T item, Description mismatchDescription) {
		super.describeMismatchSafely(item, mismatchDescription);
		this.failedMatcher.getMatcher().describeMismatchSafely(this.failedMatcher.getMatchee(), mismatchDescription, 1);
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
		this.matchers = createMatcherPairs(arg0);
		for(MatcherPair pair : this.matchers) {
			if(!pair.isMatch()) {
				this.failedMatcher = pair;
				return false;
			}
		}
		
		return true;
	}
	
	
	protected abstract List<MatcherPair> createMatcherPairs(T arg0);
	
}
