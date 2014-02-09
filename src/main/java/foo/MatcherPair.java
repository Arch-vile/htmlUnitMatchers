package foo;

import org.hamcrest.TypeSafeMatcher;
import org.w3c.dom.Node;

public class MatcherPair {
	private MyTypeSafeMatcher matcher;
	private MatchableExtractor extractor;
	
	public MatcherPair(MyTypeSafeMatcher matcher, MatchableExtractor extractor) {
		this.matcher = matcher;
		this.extractor = extractor;
	}
	public MyTypeSafeMatcher getMatcher() {
		return matcher;
	}
	public void setMatcher(MyTypeSafeMatcher matcher) {
		this.matcher = matcher;
	}

	public boolean isMatch(Object from) {
		return this.matcher.matches(extractor.extract(from));
	}
	public MatchableExtractor getExtractor() {
		return extractor;
	}
	
	
	

}
