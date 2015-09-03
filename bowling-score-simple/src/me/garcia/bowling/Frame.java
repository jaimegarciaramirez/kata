package me.garcia.bowling;

public class Frame {
	private static final int TOTAL_PINS = 10;
	private int roll1;
	private int roll2;
	private int roll3;
	
	public Frame(int roll1, int roll2) {
		super();
		this.roll1 = roll1;
		this.roll2 = roll2;
	}
	
	public Frame(int roll1, int roll2, int roll3) {
		super();
		this.roll1 = roll1;
		this.roll2 = roll2;
		this.roll3 = roll3;
	}

	public int getRoll1() {
		return roll1;
	}

	public int getRoll2() {
		return roll2;
	}
	
	public int getRoll3() {
		return roll3;
	}

	public boolean isSpare() {
		return getRoll1() != TOTAL_PINS && (getRoll1() + getRoll2() == TOTAL_PINS);
	}
	
	public boolean isStrike() {
		return getRoll1() == TOTAL_PINS;
	}

	@Override
	public String toString() {
		return "Frame [getRoll1()=" + getRoll1() + ", getRoll2()=" + getRoll2() + ", getRoll3()=" + getRoll3()
				+ ", isSpare()=" + isSpare() + ", isStrike()=" + isStrike() + "]";
	}
}
