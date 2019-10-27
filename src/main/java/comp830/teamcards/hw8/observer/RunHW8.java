/**
 * 
 */
package comp830.teamcards.hw8.observer;

import java.util.ArrayList;

/**
 * @author 
 * Lloyd Dagoc 2019
 * Surya Kranthi 2019
 * Valerie Therrien 2019
 *
 * This class will simulate the Observer pattern
 */
public class RunHW8 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PublisherImplementation publisher = new PublisherImplementation();
		
		ObserverCustom subEven = new SubscriberEvens(publisher);
		ObserverCustom subOdd = new SubscriberOdds(publisher);
		ObserverCustom subThree = new SubscriberThrees(publisher);
		
		// these lines also work as a replacement of the above
		/*
		ObserverCustom subEven = new SubscriberEvens();
		ObserverCustom subOdd = new SubscriberOdds();
		ObserverCustom subThree = new SubscriberThrees();
		publisher.registerObserver(subEven);
		publisher.registerObserver(subOdd);
		publisher.registerObserver(subThree);
		*/
		
		publisher.runSimulation();
		
		
		
	}

}
