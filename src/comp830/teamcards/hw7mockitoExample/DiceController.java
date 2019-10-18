package comp830.teamcards.hw7mockitoExample;


public class DiceController {
	
	static DiceController instance = null;
	
	private Dice[] dice = new Dice[2];
	private int numberOfRolls = 36;
	private int[] histogramCounts = new int[13];
	
	public void setDiceObj(Dice d, int pos) {
		dice[pos] = d;
	}
	
	public DiceController() {
		dice[0] = new Dice(6);
		dice[1] = new Dice(6);
		for (int i = 0; i < histogramCounts.length; i++) {
			histogramCounts[i] = 0;
		}
	}
	
	public static DiceController getInstance() {
		if (instance == null) {
			instance = new DiceController();
		}
		
		return instance;
	}
	
	public String showDistribution() {
		String ret = "";
		
		//get the distribution
		for (int i=0; i < numberOfRolls; i++) {
			histogramCounts[getDiceRollTotal()] += 1;
		}
		
		//print out the histogram
		for (int i = 2; i < histogramCounts.length; i++) {
			System.out.println( i + ": " + getHistogramString(histogramCounts[i]));
			ret = ret +  i + ": " + getHistogramString(histogramCounts[i]) + "\n";
		}
		
		return ret;
	}
	
	private String getHistogramString(int length) {
		String ret = "";
		for (int x = 0; x < length; x++) {
			ret += "*";
		}
		return ret;
	}
	
	// Example of a private 'helper' function
	private int getDiceRollTotal() {
		return dice[0].roll() + dice[1].roll();
	}
	

}
