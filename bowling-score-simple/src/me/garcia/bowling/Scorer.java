package me.garcia.bowling;

public class Scorer {
	private static final int NUMBER_OF_FRAMES = 10;
	private Frame[] frames;
	private int currentFrameIndex;
	
	public Scorer(Frame[] frames) {
		if (frames.length < NUMBER_OF_FRAMES) {
			throw new IllegalArgumentException("Expected to have " + 
					NUMBER_OF_FRAMES + " frames");
		}
		this.frames = frames;
	}
	
	public int score() {
		int totalScore = 0;
		currentFrameIndex = 0;
		for (Frame currentFrame : frames) {
			int frameScore = currentFrame.getRoll1() + currentFrame.getRoll2();
			if (isLastFrame()) {
				frameScore += currentFrame.getRoll3();
			} else if (currentFrame.isSpare()) {
				frameScore += nextFrame().getRoll1();
			} else if (currentFrame.isStrike()) {
				if (nextFrame().isStrike()) {
					frameScore += nextFrame().getRoll1();
					if (isNextFrameTheLastFrame()) {
						frameScore += nextFrame().getRoll2();
					} else {
						frameScore += nextNextFrame().getRoll1();
					}
				} else {
					frameScore += nextFrame().getRoll1() + nextFrame().getRoll2();
				}
			}
			currentFrameIndex++;
			totalScore += frameScore;
		}
		return totalScore;
	}

	private boolean isLastFrame() {
		return (currentFrameIndex + 1) == NUMBER_OF_FRAMES;
	}
	
	private boolean isNextFrameTheLastFrame() {
		return (currentFrameIndex + 2) == NUMBER_OF_FRAMES;
	}
	
	private Frame nextFrame() {
		return frames[currentFrameIndex + 1];
	}
	
	private Frame nextNextFrame() {
		return frames[currentFrameIndex + 2];
	}
}
