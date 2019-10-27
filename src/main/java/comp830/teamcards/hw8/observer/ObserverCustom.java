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
public interface ObserverCustom {
	
	public boolean notifyObserver(Event e);
	public void setObserverName(String ob);
	public String getObserverName();
	public void resetCounter();
}
