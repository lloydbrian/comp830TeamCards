package comp830.teamcards.hw7mockitoExample;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
	
	private int numberOfSides;
	private ThreadLocalRandom rand;
	
	public Dice(int sides) {
		numberOfSides = sides;
	}
	
	public int roll () {
		return ThreadLocalRandom.current().nextInt(1, numberOfSides);
	}

}
