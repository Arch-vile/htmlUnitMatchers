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
//		mismatchDescription.appendText(StringUtils.indent(matcherDepth));
		this.failedMatcher.getMatcher().describeMismatchSafely(this.failedMatcher.getMatchee(), mismatchDescription);
	}
	
	
//	protected void describeInnerMismatch(T item,
//			Description mismatchDescription, int matcherDepth) {
//		mismatchDescription.appendText(StringUtils.indent(matcherDepth));
//		this.describeMismatchSafely(item, mismatchDescription);
//	}
	
	@Override
	public void describeTo(Description arg0) {
		super.describeTo(arg0);
		for(MatcherPair pair : this.matchers) {
			arg0.appendText("\n");
			arg0.appendDescriptionOf(pair.getMatcher());
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
