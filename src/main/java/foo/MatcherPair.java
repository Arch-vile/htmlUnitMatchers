package foo;

import org.hamcrest.Description;

public class MatcherPair<T,F> {
	private MyTypeSafeMatcher<T> matcher;
	private MatchableExtractor<T,F> extractor;
	
	public MatcherPair(MyTypeSafeMatcher<T> matcher, MatchableExtractor<T,F> extractor) {
		this.matcher = matcher;
		this.extractor = extractor;
	}
	public MyTypeSafeMatcher<T> getMatcher() {
		return matcher;
	}
	public void setMatcher(MyTypeSafeMatcher<T> matcher) {
		this.matcher = matcher;
	}

	public boolean isMatch(F from) {
		return this.matcher.matches(extractor.extract(from));
	}
	public MatchableExtractor<T,F> getExtractor() {
		return extractor;
	}
	public T getMatchable(F from) {
		return this.extractor.extract(from);
	}
	
	public void proxyDescribeMismatchSafely(F from,
			Description mismatchDescription, int i) {
		getMatcher().describeMismatchSafely(getMatchable(from),
				mismatchDescription, i);

	}
	
	
	

}
