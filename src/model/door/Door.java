package model.door;

import util.maze.Interactive;
import model.passage.A_Passage;
import model.player.Player;
import model.point.Point;
import contracts.I_GetObserved;
import contracts.I_HaveDoorState;
import contracts.I_UserInteract;

public class Door implements I_GetObserved, I_UserInteract {

	private I_HaveDoorState _doorState;

	private A_Passage _passage;

	private Point _origin;

	private boolean _isStateChanged;

	public Door(I_HaveDoorState doorState) {
		_doorState = doorState;
		_isStateChanged = false;
	}

	public void blockDoor() {
		_passage.blockDoors();
	}

	public void clearDoor() {
		_passage.clearDoors();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Door door = (Door) obj;
		if (!this._origin.equals(door.getOrigin()))
			return false;

		return true;
	}

	public int getHeight() {
		return 1;
	}

	public Point getOrigin() {
		return _origin;
	}

	public A_Passage getPassage() {
		return _passage;
	}

	public int getSymbol() {
		return _doorState.getSymbol();
	}

	public int getSymbolSimple() {
		return _doorState.getSymbolSimple();
	}

	public int getWidth() {
		return 1;
	}

	@Override
	public void interact(Player player, Point direction) {
		_doorState.interact(player, direction, this);
	}

	@Override
	public boolean isStateChanged() {
		return _isStateChanged;
	}

	@Override
	public void isStateChanged(boolean isChanged) {
		_isStateChanged = isChanged;
	}

	public void setDoorState(I_HaveDoorState door) {
		_doorState = door;
		_isStateChanged = true;
	}

	public void setOrigin(Point origin) {
		_origin = origin;
	}

	public void setPassage(A_Passage passage) {
		_passage = passage;
	}
	
	@Override
	public void setBounds(Interactive active) {
		active.put(_origin, this);
	}
}
