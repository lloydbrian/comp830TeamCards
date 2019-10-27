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

	private static List<ObserverCustom> subscribers;
	private static List<ObserverCustom> unsubscribers;

	private static final int ITERATIONS = 200;
	private static final int MAX_RANDOMIZATION = 5000;
	private static final int UNREGISTER_ODDS_REACHED_LIMIT = 20;
	private static final int UNREGISTER_THREES_REACHED_LIMIT = 6;
	private static final int[] UNREGISTER_ODDS_EVENS = {40, 80, 120, 160, 200};
	private int countTrueOdds = 0;
	private int countTrueThrees= 0 ;

	
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
		for (int i =0; i < ITERATIONS; i++) {
			Event e = generateEvent(i);
			notifyObservers(e);
		}

		cleanUp();		
	}	
	
	private void cleanUp() {
		// clean-up
		System.out.println(countTrueThrees + " " + UNREGISTER_THREES_REACHED_LIMIT);
		System.out.println(subscribers.size());
		
		for (ObserverCustom subsc : subscribers) {
			System.out.println(subsc);
			
			if ((subsc instanceof SubscriberOdds) && (countTrueOdds > UNREGISTER_ODDS_REACHED_LIMIT)) {
				System.out.println(pfixLog + "ODDS Reached Limit. D-registering: " + subsc.getObserverName());
				((SubscriberOdds)subsc).removeMeFromPublisher();
				//removeObserver(subsc);
			}
			
			
			if ((subsc instanceof SubscriberThrees) && (countTrueThrees > UNREGISTER_THREES_REACHED_LIMIT)) {
				System.out.println("REACHED");
				System.out.println(pfixLog + "THREES Reached Limit. D-registering: " + subsc.getObserverName());	
				((SubscriberThrees)subsc).removeMeFromPublisher();
				//removeObserver(subsc);
			}

			System.out.println("Size" + subscribers.size());

		}		
	}
	
	@Override
	public void registerObserver(ObserverCustom o) {
		if(o != null) {
			subscribers.add(o);
			System.out.println(pfixLog + "Registration successful for: " + o.getObserverName());

			// check unsubscriber list and remove since it is already added
			/* Commented out 1 to 1 subscribe and unsubscribe as rules may be different
			 * See RunHW8 for homework simulation rules.
			 * 
			int i = unsubscribers.indexOf(o);
			if (i >= 0) {
				unsubscribers.remove(i);
				System.out.println(pfixLog + o.getObserverName() + " has been removed from the unsubscribed list.");							
			}
			*
			*/						
			
		}
	}

	@Override
	public void removeObserver(ObserverCustom o) {
		if(o != null) {
			System.out.println(o.getClass());
			int i = subscribers.indexOf(o);
			System.out.println(o.getClass() + " " + i);
			if (i >= 0) {
				subscribers.remove(i);
				System.out.println(pfixLog + "De-Registration successful for: " + o.getObserverName());	
				
				// add observer to the unsubscribed list
				/* Commented out 1 to 1 subscribe and unsubscribe as rules may be different
				 * See RunHW8 for homework simulation rules.
				unsubscribers.add(o);
				System.out.println(pfixLog + o.getObserverName() + " has been added from the unsubscribed list.");
				*
				*/							
			}
		}
	}

	@Override
	public boolean notifyObservers(Event e) {
		boolean notifyResult = false;
		
		if(e != null) {			
			for(ObserverCustom sub : subscribers) {				
				notifyResult = sub.notifyObserver(e);
				
				if((sub instanceof SubscriberOdds) && (notifyResult)) countTrueOdds++;
				if((sub instanceof SubscriberThrees) && (notifyResult)) countTrueThrees++;
				
			}
		}
		
		return notifyResult;
	}

}
