/**
 * 
 */
package comp830.teamcards.hw8.observer;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

/**
 * 
 * @author 
 * Lloyd Dagoc 2019
 * Surya Kranthi 2019
 * Valerie Therrien 2019
 *
 */
public class PublisherImplementation implements PublisherInterface {

	private final int ITERATIONS = 200;
	private final int MAX_RANDOMIZATION = 5000;
	private List<ObserverCustom> subscribers;
	private List<ObserverCustom> unsubscribers;
	
	private static String className = "";
	private static String pfixLog  = "";

	
	public PublisherImplementation() {		
		subscribers = new ArrayList<>();
		unsubscribers = new ArrayList<>();

		className = this.getClass().getName();
		pfixLog = "[" + className + "]: ";
		System.out.println(pfixLog + "Subsriber ArrayList initialized");			

	}
	
	private Event generateEvent(int i) {
		int eventData = ThreadLocalRandom.current().nextInt(1, MAX_RANDOMIZATION + 1);
		return new Event(i, eventData);
	}
	
	/**
	 * for 200 iterations
	 * Create an event -- event number is the iteration number, data is a random number between 1-5000
	 * Notify all registered observers of the event
	 * If the SubscriberOdds.notifyObserver returns true more than 20 times, unregister the observer
	 * If the SubscriberThrees.notifyObserver returned true more than 6 times, unregister the observer
	 * For iteration 40, 80, 120, 160, 200:
	 * register SubscriberOdds if it is unregistered
	 * register SubscriberThrees if it is unregistered
	 */
	public void runSimulation() {
		
		PublisherImplementation pub = new PublisherImplementation();
		SubscriberEvens nEvens = new SubscriberEvens();
		SubscriberOdds nOdds = new SubscriberOdds();
		SubscriberThrees nThrees = new SubscriberThrees();
		Event e;

		// register observers
		pub.registerObserver(nEvens);
		pub.registerObserver(nOdds);
		pub.registerObserver(nThrees);
		
		for (int i =0; i < ITERATIONS; i++) {
			e = generateEvent(i);
		}

	}
	
	
	@Override
	public void registerObserver(ObserverCustom o) {
		if(o != null) {
			subscribers.add(o);
			System.out.println(pfixLog + "Registration successful for: " + o.getObserverName());

			// check unsubscriber list and remove since it is already added
			int i = unsubscribers.indexOf(o);
			if (i >= 0) {
				unsubscribers.remove(i);
				System.out.println(pfixLog + o.getObserverName() + " has been removed from the unsubscribed list.");							
			}			
			
			
		}
	}

	@Override
	public void removeObserver(ObserverCustom o) {
		if(o != null) {
			int i = subscribers.indexOf(o);
			if (i >= 0) {
				subscribers.remove(i);
				System.out.println(pfixLog + "De-Registration successful for: " + o.getObserverName());	
				
				// add observer to the unsubscribed list
				unsubscribers.add(o);
				System.out.println(pfixLog + o.getObserverName() + " has been added from the unsubscribed list.");							
			}			
		}
	}

	@Override
	public void notifyObservers(ObserverCustom o) {
		if(o != null) {
			for(ObserverCustom ob : subscribers) {
				//ob.notifyObserver(e);
			}
		}

	}

}
