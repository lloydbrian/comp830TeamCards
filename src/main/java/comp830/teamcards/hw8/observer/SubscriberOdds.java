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
public class SubscriberOdds implements ObserverCustom {

	private PublisherImplementation myPublisher;
	
	private String obName;
	private static String className = "";
	private static String pfixLog  = "";
	
	private int countTrue = 0;
	
	public SubscriberOdds() {
		className = this.getClass().getName();
		pfixLog = "[" + className + "]: ";
		obName = className;
	}

	public SubscriberOdds(PublisherImplementation myPublisher) {
		this();
		this.setMyPublisher(myPublisher);
		
		// subscriber registers to the publisher
		myPublisher.registerObserver(this);
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
		// check for odd
		if(e.getEventData() % 2 == 1) {
			System.out.println(pfixLog + "Event Number:" + e.getEventNumber() + " is ODD. Event Data Processed: " + e.getEventData() );
			countTrue++;
			return true;
		} else {
			System.out.println(pfixLog + "Event Number:" + e.getEventNumber() + " is not ODD. Event Data Processed: " + e.getEventData() );
			return false;
		}		
	}

	@Override
	public void setObserverName(String ob) {
		obName = ob;		
	}

	@Override
	public String getObserverName() {
		return obName;
	}
}
