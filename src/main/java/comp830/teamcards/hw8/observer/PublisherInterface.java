/**
 * 
 */
package comp830.teamcards.hw8.observer;

/**
 * @author 
 * Lloyd Dagoc 2019
 * Surya Kranthi 2019
 * Valerie Therrien 2019
 *
 */
public interface PublisherInterface {
	
	public void registerObserver(ObserverCustom o);
	public void removeObserver(ObserverCustom o);
	public boolean notifyObservers(Event e);
}
