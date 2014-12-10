package view.observer;

import model.door.Door;
import model.door.DoorStateBlocked;
import model.door.DoorStateCleared;
import model.door.DoorStateQuestion;
import view.drawer.Drawer;

import com.googlecode.blacken.terminal.TerminalInterface;

// TODO: Auto-generated Javadoc

public class ObserveDoor implements I_Observe {

	private Door _door;

	private Drawer _drawer;

	// private TerminalInterface _term;

	public ObserveDoor(Door door, Drawer drawer, TerminalInterface term) {
		_door = door;
		_drawer = drawer;
		// _term = term;
	}

	public void handleState(DoorStateBlocked blockedState) {
	}

	public void handleState(DoorStateCleared clearedState) {
	}

	public void handleState(DoorStateQuestion questionState) {
	}

	@Override
	public void update() {
		if (_door.isStateChanged()) {
			_drawer.drawDoor(_door);
			_door.isStateChanged(false);
		}
	}
}
