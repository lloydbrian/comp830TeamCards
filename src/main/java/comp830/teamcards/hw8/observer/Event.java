/**
 * 
 */
package comp830.teamcards.hw8.observer;

/**
 *
 * @author 
 * Lloyd Dagoc 2019
 * Surya Kranthi 2019
 * Valerie Therrien 2019
 *
 */
public class Event {
	
	private int eventNumber;
	private int eventData;
	
	public Event(int eventNumber, int eventData) {
		this.setEventNumber(eventNumber);
		this.setEventData(eventData);
	}
	
	/**
	 * @param eventNumber the eventNumber to set
	 */
	public void setEventNumber(int eventNumber) {
		this.eventNumber = eventNumber;
	}

	/**
	 * @param eventData the eventData to set
	 */
	public void setEventData(int eventData) {
		this.eventData = eventData;
	}

	public int getEventData() {
		return eventData;
	}
	public int getEventNumber() {
		return eventNumber;
	}

}
