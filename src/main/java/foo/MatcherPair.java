package foo;

import org.hamcrest.TypeSafeMatcher;
import org.w3c.dom.Node;

public class MatcherPair {
	private TypeSafeMatcher matcher;
	private Object matchee;
	public MatcherPair(TypeSafeMatcher matcher, Object matchee) {
		this.matcher = matcher;
		this.matchee = matchee;
	}
	public TypeSafeMatcher getMatcher() {
		return matcher;
	}
	public void setMatcher(TypeSafeMatcher matcher) {
		this.matcher = matcher;
	}
	public Object getMatchee() {
		return matchee;
	}
	public void setMatchee(Object matchee) {
		this.matchee = matchee;
	}
	public boolean isMatch() {
		return this.matcher.matches(matchee);
	}
	
	

}
