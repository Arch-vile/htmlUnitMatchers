package foo;

public class MatchableExtractor<M,F> {
	
	protected int index;
	
	public MatchableExtractor() {
	}
	
	public MatchableExtractor(int index) {
		this.index = index;
	}

	public M extract(F from) {
		return (M) from;
	}
	
}
