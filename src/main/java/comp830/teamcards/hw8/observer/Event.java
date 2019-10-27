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
public interface Event {
	
	public void setEventName();
	public String getEventName();
	public int getEventData();
	public int getEventNumber();

}
