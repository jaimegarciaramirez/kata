package me.garcia.bowling;

public class Scorer {

	public static int score(Frame[] frames) {
		int totalScore = 0;
		for (int index = 0; index < frames.length; index++) {
			Frame frame = frames[index];
			totalScore += frame.getRoll1() + frame.getRoll2();
			if (frame.isSpare()) {
				if (isLastFrame(index, frames)) {
					totalScore += frame.getRoll3();
				} else {
					totalScore += frames[index + 1].getRoll1();
				}
			}
			if (frame.isStrike()) {
				if (isLastFrame(index, frames)) {
					totalScore += frame.getRoll3();
				} else if (frames[index + 1].isStrike()) {
					totalScore += frames[index + 1].getRoll1();
					if (isLastFrame(index + 1, frames)) {
						totalScore += frames[index + 1].getRoll2();
					} else {
						totalScore += frames[index + 2].getRoll1();
					}
				} else {
					totalScore += frames[index + 1].getRoll1() + frames[index + 1].getRoll2();
				}
			}
		}
		return totalScore;
	}

	private static boolean isLastFrame(int index, Frame[] frames) {
		return index == frames.length - 1;
	}
}
