/**
 * 
 */
package comp830.teamcards.hw8.observer;

import java.util.List;
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
