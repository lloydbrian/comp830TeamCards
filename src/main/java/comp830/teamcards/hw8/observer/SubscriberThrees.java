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

	private String obName;
	private static String className = "";
	private static String pfixLog  = "";
	
	public SubscriberThrees() {
		className = this.getClass().getName();
		pfixLog = "[" + className + "]: ";
		obName = className;
	}

	
	@Override
	public boolean notifyObserver(Event e) {
		// check for even
		if(e.getEventData() % 3 == 0) {
			System.out.println(pfixLog + "Event " + e.getEventNumber() + "is divisible by 3: " + e.getEventData() );
			return true;
		} else {
			System.out.println(pfixLog + "Event " + e.getEventNumber() + "is not odd: " + e.getEventData() );
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
