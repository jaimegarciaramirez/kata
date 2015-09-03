package me.garcia.bowling;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ScorerTest {

	@Test(expected=IllegalArgumentException.class)
	public void scorerExpectsTenFrames() {
		new Scorer(new Frame[]{
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
		});
	}
	
	@Test
	public void scoreAGutterGame() {
		Scorer scorer = new Scorer(new Frame[]{
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
		});
		
		assertEquals(0, scorer.score());
	}
	
	@Test
	public void scoreGameWithoutStrikesOrSpares() {
		Scorer scorer = new Scorer(new Frame[]{
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
		});
		
		assertEquals(56, scorer.score());
	}
	
	@Test
	public void scoreSpares() {
		Scorer scorer = new Scorer(new Frame[]{
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(2, 8), 
				new Frame(1, 2)
		});
		
		assertEquals(14, scorer.score());
	}
	
	@Test
	public void scoreStrikes() {
		Scorer scorer = new Scorer(new Frame[]{
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(10, 0), 
				new Frame(1, 2)
		});
		assertEquals(16, scorer.score());
	}
	
	@Test
	public void scoreConsecutiveStrikes() {
		Scorer scorer = new Scorer(new Frame[]{
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(10, 0), 
				new Frame(10, 0),
				new Frame(3, 4)
		});
		assertEquals(23 + 17 + 7, scorer.score());
	}
	
	@Test
	public void scoreStrikeOnTheLastFrame() {
		Scorer scorer = new Scorer(new Frame[]{
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(10, 0), 
				new Frame(10, 0),
				new Frame(10, 5, 10)
		});
		
		assertEquals(30 + 25 + 25, scorer.score());
	}
	
	@Test
	public void scoreSpareOnTheLastFrame() {
		Scorer scorer = new Scorer(new Frame[]{
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(0, 0), 
				new Frame(10, 0), 
				new Frame(10, 0),
				new Frame(6, 4, 4)
		});
		assertEquals(26 + 20 + 14, scorer.score());
	}
}
