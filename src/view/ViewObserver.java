/**    *********************************
 *     ******* Team Lazer Slugz ********
 *     *********************************
 *              
 *  This is the current state of the third
 *  iteration for Team Lazer Slugz. The code
 *  uses some more patterns to help seperate
 *  MVC elements. The ultimate goal of this 
 *  iteration is to create a game that can
 *  switch out a GUI on the fly.
 * 	
 */
package view;

import java.util.ArrayList;
import java.util.List;

import contracts.I_Observe;

// TODO: Auto-generated Javadoc
/**
 * An asynchronous update interface for receiving notifications about View
 * information as the View is constructed.
 */
public class ViewObserver {

	/** The door observers. */
	private List<I_Observe> doorObservers = new ArrayList<I_Observe>();

	/** The player observers. */
	private List<I_Observe> playerObservers = new ArrayList<I_Observe>();

	/**
	 * This method is called when information about an View which was previously
	 * requested using an asynchronous interface becomes available.
	 */
	public ViewObserver() {
	}

	/**
	 * This method is called when information about an View which was previously
	 * requested using an asynchronous interface becomes available.
	 * 
	 * @return the doors
	 */
	public List<I_Observe> getDoors() {
		return doorObservers;
	}

	/**
	 * This method is called when information about an View which was previously
	 * requested using an asynchronous interface becomes available.
	 */
	public void notifyAllElements() {
		notifyDoors();
		notifyPlayers();
	}

	/**
	 * This method is called when information about an View which was previously
	 * requested using an asynchronous interface becomes available.
	 */
	public void notifyDoors() {
		for (I_Observe ob : doorObservers)
			ob.update();
	}

	/**
	 * This method is called when information about an View which was previously
	 * requested using an asynchronous interface becomes available.
	 */
	public void notifyPlayers() {
		for (I_Observe ob : playerObservers)
			ob.update();
	}

	/**
	 * This method is called when information about an View which was previously
	 * requested using an asynchronous interface becomes available.
	 * 
	 * @param observer
	 *            the observer
	 */
	public void registerDoor(I_Observe observer) {
		doorObservers.add(observer);
	}

	/**
	 * This method is called when information about an View which was previously
	 * requested using an asynchronous interface becomes available.
	 * 
	 * @param observer
	 *            the observer
	 */
	public void registerPlayer(I_Observe observer) {
		playerObservers.add(observer);
	}

	/**
	 * This method is called when information about an View which was previously
	 * requested using an asynchronous interface becomes available.
	 * 
	 * @param observer
	 *            the observer
	 */
	public void removeDoor(I_Observe observer) {
		doorObservers.remove(observer);
	}

	/**
	 * This method is called when information about an View which was previously
	 * requested using an asynchronous interface becomes available.
	 * 
	 * @param observer
	 *            the observer
	 */
	public void removePlayer(I_Observe observer) {
		playerObservers.remove(observer);
	}

	/**
	 * This method is called when information about an View which was previously
	 * requested using an asynchronous interface becomes available.
	 * 
	 * @param doors
	 *            the doors
	 */
	public void setDoors(List<I_Observe> doors) {
		doorObservers = doors;
	}

	/**
	 * This method is called when information about an View which was previously
	 * requested using an asynchronous interface becomes available.
	 * 
	 * @param players
	 *            the players
	 */
	public void setPlayers(List<I_Observe> players) {
		playerObservers = players;
	}

}