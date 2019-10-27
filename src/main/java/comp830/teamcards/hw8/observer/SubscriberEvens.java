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
public class SubscriberEvens implements ObserverCustom {

	private PublisherImplementation myPublisher;
	
	private String obName;
	private static String className = "";
	private static String pfixLog  = "";
	
	public SubscriberEvens() {
		className = this.getClass().getName();
		pfixLog = "[" + className + "]: ";
		obName = className;
	}

	public SubscriberEvens(PublisherImplementation myPublisher) {
		this();
		this.setMyPublisher(myPublisher);
		
		// subscriber registers to the publisher
		myPublisher.registerObserver(this);
	}

	public void removeMeFromPublisher() {
		myPublisher.removeObserver(this);
	}
	
	/**
	 * @return the myPublisher
	 */
	public PublisherImplementation getMyPublisher() {
		return myPublisher;
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
		if(e.getEventData() % 2 == 0) {
			System.out.println(pfixLog + "Event Number:" + e.getEventNumber() + " is EVEN. Event Data Processed: " + e.getEventData() );
			result = true;
		} else {
			result = false;
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
	
}
