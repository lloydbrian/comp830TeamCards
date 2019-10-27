package comp830.teamcards.hw8.observer;

//import java.util.Observable;
//import java.util.Observer;

/**
 * @author 
 * Lloyd Dagoc 2019
 * Surya Kranthi 2019
 * Valerie Therrien 2019
 *
 */
public class SubscriberThrees implements ObserverCustom {

	private PublisherImplementation myPublisher;
	
	private String obName;
	private static String className = "";
	private static String pfixLog  = "";
	private static final int UNREGISTER_THREES_REACHED_LIMIT = 6;
	private int countTrueThrees = 0;

	
	public SubscriberThrees() {
		className = this.getClass().getName();
		pfixLog = "[" + className + "]: ";
		obName = className;
	}

	public SubscriberThrees(PublisherImplementation myPublisher) {
		this();
		this.setMyPublisher(myPublisher);
		
		// subscriber registers to the publisher
		myPublisher.registerObserver(this);
	}


	/**
	 * @return the countTrueOdds
	 */
	public int getCountTrueOdds() {
		return countTrueThrees;
	}

	/**
	 * @param countTrueOdds the countTrueOdds to set
	 */
	private void setCountTrueOdds(int countTrueOdds) {
		this.countTrueThrees = countTrueOdds;
	}
	
	/**
	 * @return the myPublisher
	 */
	public PublisherImplementation getMyPublisher() {
		return myPublisher;
	}


	public void removeMeFromPublisher() {
		myPublisher.removeObserver(this);
	}
	
	/**
	 * @param myPublisher the myPublisher to set
	 */
	public void setMyPublisher(PublisherImplementation myPublisher) {
		this.myPublisher = myPublisher;
	}
	
	@Override
	public boolean notifyObserver(Event e) {
		boolean result = false;
		// check for even
		if(e.getEventData() % 3 == 0) {
			System.out.println(pfixLog + "Event Number:" + e.getEventNumber() + " is DIVISIBLE BY 3. Event Data Processed: " + e.getEventData() );
			countTrueThrees++;
			result = true;
		} else {
			result = false;
		}		

		if(countTrueThrees > UNREGISTER_THREES_REACHED_LIMIT) {
			System.out.println("");
			System.out.println(pfixLog + "Reached Limit: " + UNREGISTER_THREES_REACHED_LIMIT + ". Unregistering...");	
			removeMeFromPublisher();
		}
		
		return result;
	}

	@Override
	public void setObserverName(String ob) {
		obName = ob;		
	}

	@Override
	public String getObserverName() {
		return obName;
	}

	@Override
	public void resetCounter() {
		setCountTrueOdds(0);
	}
	
}
