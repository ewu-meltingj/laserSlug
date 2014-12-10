package model.door;

import model.Point;
import model.player.Player;
import contracts.I_HaveDoorState;


public class DoorStateBlocked implements I_HaveDoorState {

	public DoorStateBlocked() {}

	@Override
	public int getSymbol() {
		return "X".codePointAt(0);
	}

	@Override
	public int getSymbolSimple() {
		return 2;
	}

	@Override
	public void interact(Player player, Point direction, Door parentDoor) {
		player.move(Point.refuse(direction));
	}
}