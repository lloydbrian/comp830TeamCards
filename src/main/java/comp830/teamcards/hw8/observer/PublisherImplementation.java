/**
 * 
 */
package comp830.teamcards.hw8.observer;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

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
	private static Set<Integer> registerOddsThrees = new HashSet<Integer>();
	
	private static String className = "";
	private static String pfixLog  = "";

	
	public PublisherImplementation() {		
		subscribers = new ArrayList<>();
		unsubscribers = new ArrayList<>();
		registerOddsThrees.add(40);
		registerOddsThrees.add(80);
		registerOddsThrees.add(120);
		registerOddsThrees.add(160);
		registerOddsThrees.add(200);

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
			
			if(registerOddsThrees.contains(i)) {
				System.out.println(pfixLog + "Iterator count matched " + i + ". Attempt to register back...executing. ");
				registerObserver();
			}
			
		}

	}	

	public void registerObserver() {
		for(int i = 0; i < unsubscribers.size(); i++) {
			registerObserver(unsubscribers.get(i));
		}		
	}
	
	@Override
	public void registerObserver(ObserverCustom o) {
		if(o != null) {
			int j = subscribers.indexOf(o);
			if (j < 0) {

				// add mechanism to restart count for the simulation
				o.resetCounter();
				subscribers.add(o);
				System.out.println(pfixLog + "Registration successful for: " + o.getObserverName());
				
				// check unsubscribe list and remove since it is already added
				int i = unsubscribers.indexOf(o);
				if (i >= 0) {
					unsubscribers.remove(i);
					System.out.println(pfixLog + o.getObserverName() + " has been REMOVED FROM THE UNSUBSCRIBED list.\n");							
				}
			} else {
				System.out.println(pfixLog + o.getObserverName() + " IS already in the SUBSCRIBER LIST.\n");							
				
			}
			
		}
	}

	@Override
	public void removeObserver(ObserverCustom o) {
		if(o != null) {
			int i = subscribers.indexOf(o);
			if (i >= 0) {
				// add mechanism to restart count for the simulation
				o.resetCounter();
				subscribers.remove(i);
				System.out.println(pfixLog + "De-Registration successful for: " + o.getObserverName());	
				
				// add observer to the unsubscribed list
				unsubscribers.add(o);
				System.out.println(pfixLog + o.getObserverName() + " has been ADDED TO THE UNSUBSCRIBED list.\n");
			} else {
				System.out.println(pfixLog + "Cannot De-Register. Observer is not in the list. " + o.getObserverName() + "\n");	
				
			}
		}
	}

	@Override
	public boolean notifyObservers(Event e) {
		boolean notifyResult = false;
		
		if(e != null) {
			for(int i = 0; i < subscribers.size(); i++) {
				subscribers.get(i).notifyObserver(e);
			}
		}
		
		return notifyResult;
	}

}
