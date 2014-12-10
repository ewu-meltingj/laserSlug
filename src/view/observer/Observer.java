package view.observer;

import java.util.ArrayList;
import java.util.List;

public class Observer {

	private List<I_Observe> doorObservers = new ArrayList<I_Observe>();

	private List<I_Observe> playerObservers = new ArrayList<I_Observe>();

	public Observer() {
	}

	public List<I_Observe> getDoors() {
		return doorObservers;
	}

	public void notifyAllElements() {
		notifyDoors();
		notifyPlayers();
	}

	public void notifyDoors() {
		for (I_Observe ob : doorObservers)
			ob.update();
	}

	public void notifyPlayers() {
		for (I_Observe ob : playerObservers)
			ob.update();
	}

	public void registerDoor(I_Observe observer) {
		doorObservers.add(observer);
	}

	public void registerPlayer(I_Observe observer) {
		playerObservers.add(observer);
	}

	public void removeDoor(I_Observe observer) {
		doorObservers.remove(observer);
	}

	public void removePlayer(I_Observe observer) {
		playerObservers.remove(observer);
	}

	public void setDoors(List<I_Observe> doors) {
		doorObservers = doors;
	}

	public void setPlayers(List<I_Observe> players) {
		playerObservers = players;
	}

}