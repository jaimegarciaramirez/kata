package me.garcia.bowling;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ScorerTest {

	@Test
	public void scoreAGutterGame() {
		assertEquals(0, Scorer.score(new Frame[]{
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0)
			})
		);
	}
	
	@Test
	public void scoreGameWithoutStrikesOrSpares() {
		assertEquals(56, Scorer.score(new Frame[]{
				new Frame(1, 2), 
				new Frame(2, 3), 
				new Frame(3, 4), 
				new Frame(4, 5), 
				new Frame(5, 4), 
				new Frame(4, 3), 
				new Frame(3, 2), 
				new Frame(2, 1), 
				new Frame(1, 2), 
				new Frame(2, 3)
			})
		);
	}
	
	@Test
	public void scoreSpares() {
		assertEquals(14, Scorer.score(new Frame[]{
				new Frame(2, 8), 
				new Frame(1, 2)
			})
		);
	}
	
	@Test
	public void scoreStrikes() {
		assertEquals(16, Scorer.score(new Frame[]{
				new Frame(10, 0), 
				new Frame(1, 2)
			})
		);
	}
	
	@Test
	public void scoreConsecutiveStrikes() {
		assertEquals(23 + 17 + 7, Scorer.score(new Frame[]{
				new Frame(10, 0), 
				new Frame(10, 0),
				new Frame(3, 4)
			})
		);
	}
	
	@Test
	public void scoreStrikeOnTheLastFrame() {
		assertEquals(30 + 25 + 25, Scorer.score(new Frame[]{
				new Frame(10, 0), 
				new Frame(10, 0),
				new Frame(10, 5, 10)
			})
		);
	}
	
	@Test
	public void scoreSpareOnTheLastFrame() {
		assertEquals(26 + 20 + 14, Scorer.score(new Frame[]{
				new Frame(10, 0), 
				new Frame(10, 0),
				new Frame(6, 4, 4)
			})
		);
	}
}
